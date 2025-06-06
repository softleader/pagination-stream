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

import static tw.com.softleader.data.stream.AttemptPolicyFactory.totalPage;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

/**
 * 分頁介面
 *
 * @author Matt Ho
 */
public interface Paging<R> {

  /**
   * Creates a new sequential {@link Stream} from {@link PageSpliterator} of every page
   *
   * @see #parallelPagedStream()
   */
  @NonNull
  Stream<List<R>> pagedStream();

  /**
   * Creates a new sequential {@link Stream} from {@link PageSpliterator} of the results of each
   * element of {@link #pagedStream()}
   *
   * @see #parallelStream()
   */
  @NonNull
  default Stream<R> stream() {
    return pagedStream().flatMap(Collection::stream);
  }

  /**
   * Creates a new parallel sequential {@link Stream} from {@link PageSpliterator} of the results of
   * each element of {@link #pagedStream()}
   *
   * @see #stream()
   */
  @NonNull
  default Stream<R> parallelStream() {
    return stream().parallel();
  }

  /**
   * Creates a new parallel sequential {@link Stream} from {@link PageSpliterator} of every page
   *
   * @see #pagedStream()
   */
  @NonNull
  default Stream<List<R>> parallelPagedStream() {
    return pagedStream().parallel();
  }

  /**
   * Creates a new sequential {@link Stream} from {@link FixedPageSpliterator} of every page
   *
   * <p>此模式會使用固定的 {@link Pageable} 作為分頁條件, 每次都從資料源撈取「當下還符合條件的資料」來處理, 重複這個過程直到查不到任何資料為止
   *
   * <p>此模式不支援 Parallel 處理
   *
   * @see FixedPageSpliterator
   */
  @NonNull
  default Stream<List<R>> fixedPagedStream() {
    return fixedPagedStream(totalPage());
  }

  /**
   * Creates a new sequential {@link Stream} from {@link FixedPageSpliterator} of the results of
   * each element of {@link #fixedPagedStream()}
   *
   * <p>此模式會使用固定的 {@link Pageable} 作為分頁條件, 每次都從資料源撈取「當下還符合條件的資料」來處理, 重複這個過程直到查不到任何資料為止
   *
   * <p>此模式不支援 Parallel 處理
   *
   * @see FixedPageSpliterator
   */
  @NonNull
  default Stream<R> fixedStream() {
    return fixedPagedStream().flatMap(Collection::stream);
  }

  /**
   * Creates a new sequential {@link Stream} from {@link FixedPageSpliterator} of every page
   *
   * <p>此模式會使用固定的 {@link Pageable} 作為分頁條件, 每次都從資料源撈取「當下還符合條件的資料」來處理, 重複這個過程直到查不到任何資料為止
   *
   * <p>此模式不支援 Parallel 處理
   *
   * @param attemptPolicyFactory 最多嘗試幾次的邏輯, 作為避免無限迴圈的保險
   * @throws AttemptExhaustedException if reached max attempts
   * @see FixedPageSpliterator
   */
  @NonNull
  Stream<List<R>> fixedPagedStream(@NonNull AttemptPolicyFactory attemptPolicyFactory)
      throws AttemptExhaustedException;

  /**
   * Creates a new sequential {@link Stream} from {@link FixedPageSpliterator} of the results of
   * each element of {@link #fixedPagedStream()}
   *
   * <p>此模式會使用固定的 {@link Pageable} 作為分頁條件, 每次都從資料源撈取「當下還符合條件的資料」來處理, 重複這個過程直到查不到任何資料為止
   *
   * <p>此模式不支援 Parallel 處理
   *
   * @param attemptPolicyFactory 最多嘗試幾次的邏輯, 作為避免無限迴圈的保險
   * @throws AttemptExhaustedException if reached max attempts
   * @see FixedPageSpliterator
   */
  @NonNull
  default Stream<R> fixedStream(@NonNull AttemptPolicyFactory attemptPolicyFactory)
      throws AttemptExhaustedException {
    return fixedPagedStream(attemptPolicyFactory).flatMap(Collection::stream);
  }
}
