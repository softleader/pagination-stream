/*
 * Copyright © 2022 SoftLeader
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package tw.com.softleader.data.stream;

import static java.util.Optional.ofNullable;

import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * 這是一個用來處理資料分頁的 {@link Spliterator}，它幫助我們從資料庫或網路上取得大量的資料，然後分批處理，避免一次處理太多而導致記憶體不足。
 *
 * <p>在同步（Sequential）情境中, 會依據每次取得的分頁資訊, 透過 {@link Pageable#next()} 順序逐頁取得資料, 直到最後一頁 ({@code Pn}) 為止:
 *
 * <pre>{@code
 * +-----+-----+-----+-----+-----+
 * |  P1 |  P2 |  P3 |  .. |  Pn |
 * +-----+-----+-----+-----+-----+
 * }</pre>
 *
 * <p>在併行 (Parallel) 的情境中, 會先同步的取回第一次資料 (P1) 以作為拆分基礎,假設 P1 取回的資料顯示共有 4 個分頁（P1, P2, P3, P4）, 之後的 3
 * 個分頁會被拆分成多個 Spliterator (S1, S2, S3）。每個拆分的 Spliterator 都會是一個子任務可以被獨立地執行。
 *
 * <pre>{@code
 *               +-----+
 *               |  P1 | (Base: fetched first)
 *               +-----+
 *                 |
 *   +-------------+-------------+
 *   |             |             |
 * +-----+     +-----+       +-----+
 * |  P2 |     |  P3 |       |  P4 |
 * +-----+     +-----+       +-----+
 *   |           |             |
 *   v           v             v
 * +-----+     +-----+       +-----+
 * |  S1 |     |  S2 |       |  S3 |
 * +-----+     +-----+       +-----+
 * }</pre>
 *
 * @author Matt Ho
 */
public class PageSpliterator<T> implements Spliterator<List<T>> {

  protected static final long TOO_EXPENSIVE_TO_COMPUTE_ESTIMATE_SIZE = Long.MAX_VALUE;

  private final PageFetcher<T> fetcher;
  private final int firstPageNumber; // 從 0 開始
  private final AtomicBoolean fetched = new AtomicBoolean(); // 防止同一頁被重複執行損失效能
  private Pageable pageable;
  private Integer totalPages; // 從 1 開始
  private Page<T> page;

  public PageSpliterator(@NonNull PageFetcher<T> fetcher, @NonNull Pageable pageable) {
    this.fetcher = fetcher;
    this.pageable = pageable;
    this.firstPageNumber = pageable.getPageNumber();
  }

  @Override
  public boolean tryAdvance(Consumer<? super List<T>> action) {
    prefetch();
    if (isPageEmpty()) {
      return false;
    }
    action.accept(page.getContent());
    if (isLastPage(pageable)) {
      return false;
    }
    pageable = nextPage(page.getPageable());
    fetched.set(false);
    return true;
  }

  @Override
  public Spliterator<List<T>> trySplit() {
    prefetch();
    if (isPageEmpty() || onlyOnePage()) {
      return null;
    }
    if (isLastPage(pageable)) { // 排到最後一頁就不切分了
      fetched.set(false); // 但還是要讓後續的 tryAdvance 去取資料
      return null;
    }
    if (isFirstPage(pageable)) {
      pageable = nextPage(pageable);
      return new FetchedPageSpliterator<>(page);
    }
    var split =
        new SinglePageSpliterator<>(
            fetcher,
            PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()));
    pageable = nextPage(pageable);
    return split;
  }

  private boolean onlyOnePage() {
    return isFirstPage(pageable) && isLastPage(pageable);
  }

  private boolean isPageEmpty() {
    return page == null || page.isEmpty();
  }

  protected boolean isLastPage(@NonNull Pageable pageable) {
    return totalPages != null && pageable.getPageNumber() + 1 >= totalPages;
  }

  protected boolean isFirstPage(@NonNull Pageable pageable) {
    return pageable.getPageNumber() == firstPageNumber;
  }

  protected Pageable nextPage(@NonNull Pageable pageable) {
    return pageable.next();
  }

  @Override
  public long estimateSize() {
    prefetch();
    return ofNullable(totalPages)
        .map(Integer::longValue)
        .orElse(TOO_EXPENSIVE_TO_COMPUTE_ESTIMATE_SIZE);
  }

  @Override
  public int characteristics() {
    return ORDERED | IMMUTABLE | SIZED | SUBSIZED | CONCURRENT;
  }

  private void prefetch() {
    if (fetched.get()) {
      return;
    }
    page = fetchPage();
    if (page != null) {
      pageable = page.getPageable();
      totalPages = page.getTotalPages();
    }
    fetched.set(true);
  }

  @Nullable
  protected Page<T> fetchPage() {
    return fetcher.fetch(pageable);
  }
}
