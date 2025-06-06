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

import static lombok.AccessLevel.PACKAGE;

import java.util.List;
import java.util.stream.Stream;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.jooq.lambda.function.*;
import org.jooq.lambda.tuple.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Matt Ho
 */
@NoArgsConstructor(access = PACKAGE)
public class PageSupport extends FixedPageSupport {

  // --- 0 argument

  public static <R> Stream<List<R>> pagedStream(
      @NonNull Function1<Pageable, Page<R>> fetcher, @NonNull Pageable pageable) {
    return of(fetcher).args(pageable).pagedStream();
  }

  public static <R> Stream<R> stream(
      @NonNull Function1<Pageable, Page<R>> fetcher, @NonNull Pageable pageable) {
    return of(fetcher).args(pageable).stream();
  }

  // --- 1 argument

  public static <T1, R> Stream<List<R>> pagedStream(
      @NonNull Function2<T1, Pageable, Page<R>> fetcher,
      @NonNull Tuple1<T1> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).pagedStream();
  }

  public static <T1, R> Stream<R> stream(
      @NonNull Function2<T1, Pageable, Page<R>> fetcher,
      @NonNull Tuple1<T1> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).stream();
  }

  public static <T1, R> Stream<List<R>> pagedStream(
      @NonNull Function2<T1, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, pageable).pagedStream();
  }

  public static <T1, R> Stream<R> stream(
      @NonNull Function2<T1, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, pageable).stream();
  }

  // --- 2 arguments

  public static <T1, T2, R> Stream<List<R>> pagedStream(
      @NonNull Function3<T1, T2, Pageable, Page<R>> fetcher,
      @NonNull Tuple2<T1, T2> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).pagedStream();
  }

  public static <T1, T2, R> Stream<R> stream(
      @NonNull Function3<T1, T2, Pageable, Page<R>> fetcher,
      @NonNull Tuple2<T1, T2> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).stream();
  }

  public static <T1, T2, R> Stream<List<R>> pagedStream(
      @NonNull Function3<T1, T2, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, pageable).pagedStream();
  }

  public static <T1, T2, R> Stream<R> stream(
      @NonNull Function3<T1, T2, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, pageable).stream();
  }

  // --- 3 arguments

  public static <T1, T2, T3, R> Stream<List<R>> pagedStream(
      @NonNull Function4<T1, T2, T3, Pageable, Page<R>> fetcher,
      @NonNull Tuple3<T1, T2, T3> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).pagedStream();
  }

  public static <T1, T2, T3, R> Stream<R> stream(
      @NonNull Function4<T1, T2, T3, Pageable, Page<R>> fetcher,
      @NonNull Tuple3<T1, T2, T3> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).stream();
  }

  public static <T1, T2, T3, R> Stream<List<R>> pagedStream(
      @NonNull Function4<T1, T2, T3, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, arg3, pageable).pagedStream();
  }

  public static <T1, T2, T3, R> Stream<R> stream(
      @NonNull Function4<T1, T2, T3, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, arg3, pageable).stream();
  }

  // --- 4 arguments

  public static <T1, T2, T3, T4, R> Stream<List<R>> pagedStream(
      @NonNull Function5<T1, T2, T3, T4, Pageable, Page<R>> fetcher,
      @NonNull Tuple4<T1, T2, T3, T4> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).pagedStream();
  }

  public static <T1, T2, T3, T4, R> Stream<R> stream(
      @NonNull Function5<T1, T2, T3, T4, Pageable, Page<R>> fetcher,
      @NonNull Tuple4<T1, T2, T3, T4> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).stream();
  }

  public static <T1, T2, T3, T4, R> Stream<List<R>> pagedStream(
      @NonNull Function5<T1, T2, T3, T4, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, arg3, arg4, pageable).pagedStream();
  }

  public static <T1, T2, T3, T4, R> Stream<R> stream(
      @NonNull Function5<T1, T2, T3, T4, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, arg3, arg4, pageable).stream();
  }

  // --- 5 arguments

  public static <T1, T2, T3, T4, T5, R> Stream<List<R>> pagedStream(
      @NonNull Function6<T1, T2, T3, T4, T5, Pageable, Page<R>> fetcher,
      @NonNull Tuple5<T1, T2, T3, T4, T5> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).pagedStream();
  }

  public static <T1, T2, T3, T4, T5, R> Stream<R> stream(
      @NonNull Function6<T1, T2, T3, T4, T5, Pageable, Page<R>> fetcher,
      @NonNull Tuple5<T1, T2, T3, T4, T5> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).stream();
  }

  public static <T1, T2, T3, T4, T5, R> Stream<List<R>> pagedStream(
      @NonNull Function6<T1, T2, T3, T4, T5, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, arg3, arg4, arg5, pageable).pagedStream();
  }

  public static <T1, T2, T3, T4, T5, R> Stream<R> stream(
      @NonNull Function6<T1, T2, T3, T4, T5, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, arg3, arg4, arg5, pageable).stream();
  }

  // --- 6 arguments

  public static <T1, T2, T3, T4, T5, T6, R> Stream<List<R>> pagedStream(
      @NonNull Function7<T1, T2, T3, T4, T5, T6, Pageable, Page<R>> fetcher,
      @NonNull Tuple6<T1, T2, T3, T4, T5, T6> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).pagedStream();
  }

  public static <T1, T2, T3, T4, T5, T6, R> Stream<R> stream(
      @NonNull Function7<T1, T2, T3, T4, T5, T6, Pageable, Page<R>> fetcher,
      @NonNull Tuple6<T1, T2, T3, T4, T5, T6> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).stream();
  }

  public static <T1, T2, T3, T4, T5, T6, R> Stream<List<R>> pagedStream(
      @NonNull Function7<T1, T2, T3, T4, T5, T6, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, arg3, arg4, arg5, arg6, pageable).pagedStream();
  }

  public static <T1, T2, T3, T4, T5, T6, R> Stream<R> stream(
      @NonNull Function7<T1, T2, T3, T4, T5, T6, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, arg3, arg4, arg5, arg6, pageable).stream();
  }

  // --- 7 arguments

  public static <T1, T2, T3, T4, T5, T6, T7, R> Stream<List<R>> pagedStream(
      @NonNull Function8<T1, T2, T3, T4, T5, T6, T7, Pageable, Page<R>> fetcher,
      @NonNull Tuple7<T1, T2, T3, T4, T5, T6, T7> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).pagedStream();
  }

  public static <T1, T2, T3, T4, T5, T6, T7, R> Stream<R> stream(
      @NonNull Function8<T1, T2, T3, T4, T5, T6, T7, Pageable, Page<R>> fetcher,
      @NonNull Tuple7<T1, T2, T3, T4, T5, T6, T7> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).stream();
  }

  public static <T1, T2, T3, T4, T5, T6, T7, R> Stream<List<R>> pagedStream(
      @NonNull Function8<T1, T2, T3, T4, T5, T6, T7, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @Nullable T7 arg7,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, arg3, arg4, arg5, arg6, arg7, pageable).pagedStream();
  }

  public static <T1, T2, T3, T4, T5, T6, T7, R> Stream<R> stream(
      @NonNull Function8<T1, T2, T3, T4, T5, T6, T7, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @Nullable T7 arg7,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, arg3, arg4, arg5, arg6, arg7, pageable).stream();
  }

  // --- 8 arguments

  public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Stream<List<R>> pagedStream(
      @NonNull Function9<T1, T2, T3, T4, T5, T6, T7, T8, Pageable, Page<R>> fetcher,
      @NonNull Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).pagedStream();
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Stream<R> stream(
      @NonNull Function9<T1, T2, T3, T4, T5, T6, T7, T8, Pageable, Page<R>> fetcher,
      @NonNull Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).stream();
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Stream<List<R>> pagedStream(
      @NonNull Function9<T1, T2, T3, T4, T5, T6, T7, T8, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @Nullable T7 arg7,
      @Nullable T8 arg8,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, pageable).pagedStream();
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Stream<R> stream(
      @NonNull Function9<T1, T2, T3, T4, T5, T6, T7, T8, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @Nullable T7 arg7,
      @Nullable T8 arg8,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, pageable).stream();
  }

  // --- 9 arguments

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Stream<List<R>> pagedStream(
      @NonNull Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, Pageable, Page<R>> fetcher,
      @NonNull Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).pagedStream();
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Stream<R> stream(
      @NonNull Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, Pageable, Page<R>> fetcher,
      @NonNull Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).stream();
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Stream<List<R>> pagedStream(
      @NonNull Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @Nullable T7 arg7,
      @Nullable T8 arg8,
      @Nullable T9 arg9,
      @NonNull Pageable pageable) {
    return of(fetcher)
        .args(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, pageable)
        .pagedStream();
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Stream<R> stream(
      @NonNull Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @Nullable T7 arg7,
      @Nullable T8 arg8,
      @Nullable T9 arg9,
      @NonNull Pageable pageable) {
    return of(fetcher)
        .args(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, pageable)
        .stream();
  }

  // --- 10 arguments

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> Stream<List<R>> pagedStream(
      @NonNull Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, Pageable, Page<R>> fetcher,
      @NonNull Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).pagedStream();
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> Stream<R> stream(
      @NonNull Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, Pageable, Page<R>> fetcher,
      @NonNull Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).stream();
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> Stream<List<R>> pagedStream(
      @NonNull Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @Nullable T7 arg7,
      @Nullable T8 arg8,
      @Nullable T9 arg9,
      @Nullable T10 arg10,
      @NonNull Pageable pageable) {
    return of(fetcher)
        .args(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, pageable)
        .pagedStream();
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> Stream<R> stream(
      @NonNull Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @Nullable T7 arg7,
      @Nullable T8 arg8,
      @Nullable T9 arg9,
      @Nullable T10 arg10,
      @NonNull Pageable pageable) {
    return of(fetcher)
        .args(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, pageable)
        .stream();
  }
}
