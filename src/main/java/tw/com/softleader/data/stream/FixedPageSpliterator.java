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

import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicLong;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;

/**
 * 這是一個使用固定 {@link Pageable} 的 {@link PageSpliterator}, 不斷的重複直到查不到任何資料為止
 *
 * <p>預期在使用上, 每次資料查到後, 對資料來源改變資料狀態, 因此每一圈所查回符合條件的資料數也預期逐漸減少, 當符合條件的資料數量變為零或達到最大嘗試次數時就會停止
 *
 * <p>為了避免無窮循環, 本 {@code Spliterator} 支援設定最大重試次數來限制資料的取回次數, 若超過最大重試次數, 則會拋出 {@link
 * AttemptExhaustedException}
 *
 * <pre>{@code
 * +-----+-----+-----+-----+
 * |  P1 |  P1 |  P1 |  .. | (no more data) => End
 * +-----+-----+-----+-----+
 * }</pre>
 *
 * <p>本 {@code Spliterator} 不支援 Parallel 處理
 *
 * @author Matt Ho
 */
public class FixedPageSpliterator<T> extends PageSpliterator<T> {

  /** 不限制最大嘗試次數 */
  public static final long UNLIMITED_MAX_ATTEMPTS = 0;

  private final AtomicLong count = new AtomicLong(0);
  private final long maxAttempts;

  public FixedPageSpliterator(
      @NonNull PageFetcher<T> fetcher, @NonNull Pageable pageable, long maxAttempts) {
    super(fetcher, pageable);
    this.maxAttempts = maxAttempts;
  }

  public FixedPageSpliterator(@NonNull PageFetcher<T> fetcher, @NonNull Pageable pageable) {
    this(fetcher, pageable, UNLIMITED_MAX_ATTEMPTS);
  }

  @Override
  protected boolean isLastPage(@NonNull Pageable pageable) {
    return false;
  }

  @Override
  protected Pageable fetchNextPage(@NonNull Pageable pageable) {
    var attempts = this.count.incrementAndGet();
    if (maxAttempts > UNLIMITED_MAX_ATTEMPTS && attempts >= maxAttempts) {
      throw new AttemptExhaustedException(
          String.format("Attempt exhausted after %s attempts", attempts));
    }
    return pageable;
  }

  @Override
  public Spliterator<List<T>> trySplit() {
    return null; // 不支援 parallel
  }

  @Override
  public int characteristics() {
    return ORDERED | IMMUTABLE;
  }
}
