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
package tw.com.softleader.data.stream.support;

import static lombok.AccessLevel.PACKAGE;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jooq.lambda.function.Function3;
import org.jooq.lambda.tuple.Tuple2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import tw.com.softleader.data.stream.AttemptPolicyFactory;
import tw.com.softleader.data.stream.FixedPageSpliterator;
import tw.com.softleader.data.stream.PageSpliterator;
import tw.com.softleader.data.stream.Paging;

/**
 * 2 個參數及 1 個 {@code Pageable} 的分頁
 *
 * @author Matt Ho
 */
@RequiredArgsConstructor
public class OfPaging2<T1, T2, R> {

  @NonNull private final Function3<T1, T2, Pageable, Page<R>> fetcher;

  public Paging<R> args(@NonNull Tuple2<T1, T2> args, @NonNull Pageable pageable) {
    return new Paging2<>(fetcher, args, pageable);
  }

  public Paging<R> args(@Nullable T1 arg1, @Nullable T2 arg2, @NonNull Pageable pageable) {
    return args(new Tuple2<>(arg1, arg2), pageable);
  }

  @RequiredArgsConstructor(access = PACKAGE)
  static class Paging2<T1, T2, R> implements Paging<R> {

    @NonNull private final Function3<T1, T2, Pageable, Page<R>> fetcher;
    @NonNull private final Tuple2<T1, T2> args;
    @NonNull private final Pageable pageable;

    @Override
    public Stream<List<R>> pagedStream() {
      return StreamSupport.stream(
          new PageSpliterator<>(new PageFetcher2<>(fetcher, args), pageable), false);
    }

    @Override
    public Stream<List<R>> fixedPagedStream(@NonNull AttemptPolicyFactory attemptPolicyFactory) {
      return StreamSupport.stream(
          new FixedPageSpliterator<>(
              new PageFetcher2<>(fetcher, args), pageable, attemptPolicyFactory),
          false);
    }
  }
}
