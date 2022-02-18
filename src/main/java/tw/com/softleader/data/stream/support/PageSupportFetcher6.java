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

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.jooq.lambda.function.Function7;
import org.jooq.lambda.tuple.Tuple6;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import tw.com.softleader.data.stream.PageFetcher6;
import tw.com.softleader.data.stream.PageSpliterator;

/**
 * @author Matt Ho
 */
public class PageSupportFetcher6<T1, T2, T3, T4, T5, T6, R> {

  private final Function7<T1, T2, T3, T4, T5, T6, Pageable, Page<R>> fetcher;

  public PageSupportFetcher6(
      Function7<T1, T2, T3, T4, T5, T6, Pageable, Page<R>> fetcher) {
    this.fetcher = fetcher;
  }

  public PageSupportArgs<T1, T2, T3, T4, T5, T6, R> args(
      @NonNull Tuple6<T1, T2, T3, T4, T5, T6> args,
      @NonNull Pageable pageable) {
    return new PageSupportArgs<>(fetcher, args, pageable);
  }

  public PageSupportArgs<T1, T2, T3, T4, T5, T6, R> args(
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @NonNull Pageable pageable) {
    return args(
        new Tuple6<>(arg1, arg2, arg3, arg4, arg5, arg6),
        pageable);
  }

  @AllArgsConstructor(access = AccessLevel.PACKAGE)
  public static class PageSupportArgs<T1, T2, T3, T4, T5, T6, R> {

    @NonNull
    private final Function7<T1, T2, T3, T4, T5, T6, Pageable, Page<R>> fetcher;
    @NonNull
    private final Tuple6<T1, T2, T3, T4, T5, T6> args;
    @NonNull
    private final Pageable pageable;

    public Stream<List<R>> pagedStream() {
      return StreamSupport.stream(
          new PageSpliterator<>(new PageFetcher6<>(fetcher, args), pageable),
          false);
    }

    public Stream<R> stream() {
      return pagedStream()
          .flatMap(Collection::stream);
    }
  }
}
