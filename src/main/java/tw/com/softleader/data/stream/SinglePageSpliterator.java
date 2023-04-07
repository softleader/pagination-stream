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
import java.util.function.Consumer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 單頁的 {@link Spliterator}, 會使用 {@link #fetcher} 及 {@link #pageable} 執行一次並取得資料
 *
 * @author Matt Ho
 */
@RequiredArgsConstructor
class SinglePageSpliterator<T> implements Spliterator<List<T>> {

  @NonNull
  final PageFetcher<T> fetcher;
  @NonNull
  final Pageable pageable;

  @Override
  public boolean tryAdvance(Consumer<? super List<T>> action) {
    ofNullable(fetcher.fetch(pageable))
        .map(Page::getContent)
        .ifPresent(action);
    return false;
  }

  @Override
  public Spliterator<List<T>> trySplit() {
    return null;
  }

  @Override
  public long estimateSize() {
    return 1;
  }

  @Override
  public int characteristics() {
    return ORDERED | IMMUTABLE | SIZED;
  }
}
