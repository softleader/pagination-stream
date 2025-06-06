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
public class FixedPageSupport extends PageBuilderSupport {

  // --- 0 argument

  public static <R> Stream<List<R>> fixedPagedStream(
      @NonNull Function1<Pageable, Page<R>> fetcher, @NonNull Pageable pageable) {
    return of(fetcher).args(pageable).fixedPagedStream();
  }

  public static <R> Stream<R> fixedStream(
      @NonNull Function1<Pageable, Page<R>> fetcher, @NonNull Pageable pageable) {
    return of(fetcher).args(pageable).fixedStream();
  }

  // --- 1 argument

  public static <T1, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function2<T1, Pageable, Page<R>> fetcher,
      @NonNull Tuple1<T1> args,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher).args(args, pageable).fixedPagedStream(attemptPolicyFactory);
  }

  public static <T1, R> Stream<R> fixedStream(
      @NonNull Function2<T1, Pageable, Page<R>> fetcher,
      @NonNull Tuple1<T1> args,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher).args(args, pageable).fixedStream(attemptPolicyFactory);
  }

  public static <T1, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function2<T1, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher).args(arg1, pageable).fixedPagedStream(attemptPolicyFactory);
  }

  public static <T1, R> Stream<R> fixedStream(
      @NonNull Function2<T1, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher).args(arg1, pageable).fixedStream(attemptPolicyFactory);
  }

  public static <T1, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function2<T1, Pageable, Page<R>> fetcher,
      @NonNull Tuple1<T1> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).fixedPagedStream();
  }

  public static <T1, R> Stream<R> fixedStream(
      @NonNull Function2<T1, Pageable, Page<R>> fetcher,
      @NonNull Tuple1<T1> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).fixedStream();
  }

  public static <T1, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function2<T1, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, pageable).fixedPagedStream();
  }

  public static <T1, R> Stream<R> fixedStream(
      @NonNull Function2<T1, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, pageable).fixedStream();
  }

  // --- 2 arguments

  public static <T1, T2, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function3<T1, T2, Pageable, Page<R>> fetcher,
      @NonNull Tuple2<T1, T2> args,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher).args(args, pageable).fixedPagedStream(attemptPolicyFactory);
  }

  public static <T1, T2, R> Stream<R> fixedStream(
      @NonNull Function3<T1, T2, Pageable, Page<R>> fetcher,
      @NonNull Tuple2<T1, T2> args,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher).args(args, pageable).fixedStream(attemptPolicyFactory);
  }

  public static <T1, T2, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function3<T1, T2, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher).args(arg1, arg2, pageable).fixedPagedStream(attemptPolicyFactory);
  }

  public static <T1, T2, R> Stream<R> fixedStream(
      @NonNull Function3<T1, T2, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher).args(arg1, arg2, pageable).fixedStream(attemptPolicyFactory);
  }

  public static <T1, T2, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function3<T1, T2, Pageable, Page<R>> fetcher,
      @NonNull Tuple2<T1, T2> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).fixedPagedStream();
  }

  public static <T1, T2, R> Stream<R> fixedStream(
      @NonNull Function3<T1, T2, Pageable, Page<R>> fetcher,
      @NonNull Tuple2<T1, T2> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).fixedStream();
  }

  public static <T1, T2, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function3<T1, T2, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, pageable).fixedPagedStream();
  }

  public static <T1, T2, R> Stream<R> fixedStream(
      @NonNull Function3<T1, T2, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, pageable).fixedStream();
  }

  // --- 3 arguments

  public static <T1, T2, T3, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function4<T1, T2, T3, Pageable, Page<R>> fetcher,
      @NonNull Tuple3<T1, T2, T3> args,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher).args(args, pageable).fixedPagedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, R> Stream<R> fixedStream(
      @NonNull Function4<T1, T2, T3, Pageable, Page<R>> fetcher,
      @NonNull Tuple3<T1, T2, T3> args,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher).args(args, pageable).fixedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function4<T1, T2, T3, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher).args(arg1, arg2, arg3, pageable).fixedPagedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, R> Stream<R> fixedStream(
      @NonNull Function4<T1, T2, T3, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher).args(arg1, arg2, arg3, pageable).fixedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function4<T1, T2, T3, Pageable, Page<R>> fetcher,
      @NonNull Tuple3<T1, T2, T3> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).fixedPagedStream();
  }

  public static <T1, T2, T3, R> Stream<R> fixedStream(
      @NonNull Function4<T1, T2, T3, Pageable, Page<R>> fetcher,
      @NonNull Tuple3<T1, T2, T3> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).fixedStream();
  }

  public static <T1, T2, T3, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function4<T1, T2, T3, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, arg3, pageable).fixedPagedStream();
  }

  public static <T1, T2, T3, R> Stream<R> fixedStream(
      @NonNull Function4<T1, T2, T3, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, arg3, pageable).fixedStream();
  }

  // --- 4 arguments

  public static <T1, T2, T3, T4, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function5<T1, T2, T3, T4, Pageable, Page<R>> fetcher,
      @NonNull Tuple4<T1, T2, T3, T4> args,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher).args(args, pageable).fixedPagedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, T4, R> Stream<R> fixedStream(
      @NonNull Function5<T1, T2, T3, T4, Pageable, Page<R>> fetcher,
      @NonNull Tuple4<T1, T2, T3, T4> args,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher).args(args, pageable).fixedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, T4, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function5<T1, T2, T3, T4, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher)
        .args(arg1, arg2, arg3, arg4, pageable)
        .fixedPagedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, T4, R> Stream<R> fixedStream(
      @NonNull Function5<T1, T2, T3, T4, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher).args(arg1, arg2, arg3, arg4, pageable).fixedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, T4, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function5<T1, T2, T3, T4, Pageable, Page<R>> fetcher,
      @NonNull Tuple4<T1, T2, T3, T4> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).fixedPagedStream();
  }

  public static <T1, T2, T3, T4, R> Stream<R> fixedStream(
      @NonNull Function5<T1, T2, T3, T4, Pageable, Page<R>> fetcher,
      @NonNull Tuple4<T1, T2, T3, T4> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).fixedStream();
  }

  public static <T1, T2, T3, T4, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function5<T1, T2, T3, T4, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, arg3, arg4, pageable).fixedPagedStream();
  }

  public static <T1, T2, T3, T4, R> Stream<R> fixedStream(
      @NonNull Function5<T1, T2, T3, T4, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, arg3, arg4, pageable).fixedStream();
  }

  // --- 5 arguments

  public static <T1, T2, T3, T4, T5, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function6<T1, T2, T3, T4, T5, Pageable, Page<R>> fetcher,
      @NonNull Tuple5<T1, T2, T3, T4, T5> args,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher).args(args, pageable).fixedPagedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, T4, T5, R> Stream<R> fixedStream(
      @NonNull Function6<T1, T2, T3, T4, T5, Pageable, Page<R>> fetcher,
      @NonNull Tuple5<T1, T2, T3, T4, T5> args,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher).args(args, pageable).fixedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, T4, T5, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function6<T1, T2, T3, T4, T5, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher)
        .args(arg1, arg2, arg3, arg4, arg5, pageable)
        .fixedPagedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, T4, T5, R> Stream<R> fixedStream(
      @NonNull Function6<T1, T2, T3, T4, T5, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher)
        .args(arg1, arg2, arg3, arg4, arg5, pageable)
        .fixedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, T4, T5, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function6<T1, T2, T3, T4, T5, Pageable, Page<R>> fetcher,
      @NonNull Tuple5<T1, T2, T3, T4, T5> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).fixedPagedStream();
  }

  public static <T1, T2, T3, T4, T5, R> Stream<R> fixedStream(
      @NonNull Function6<T1, T2, T3, T4, T5, Pageable, Page<R>> fetcher,
      @NonNull Tuple5<T1, T2, T3, T4, T5> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).fixedStream();
  }

  public static <T1, T2, T3, T4, T5, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function6<T1, T2, T3, T4, T5, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, arg3, arg4, arg5, pageable).fixedPagedStream();
  }

  public static <T1, T2, T3, T4, T5, R> Stream<R> fixedStream(
      @NonNull Function6<T1, T2, T3, T4, T5, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, arg3, arg4, arg5, pageable).fixedStream();
  }

  // --- 6 arguments

  public static <T1, T2, T3, T4, T5, T6, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function7<T1, T2, T3, T4, T5, T6, Pageable, Page<R>> fetcher,
      @NonNull Tuple6<T1, T2, T3, T4, T5, T6> args,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher).args(args, pageable).fixedPagedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, T4, T5, T6, R> Stream<R> fixedStream(
      @NonNull Function7<T1, T2, T3, T4, T5, T6, Pageable, Page<R>> fetcher,
      @NonNull Tuple6<T1, T2, T3, T4, T5, T6> args,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher).args(args, pageable).fixedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, T4, T5, T6, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function7<T1, T2, T3, T4, T5, T6, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher)
        .args(arg1, arg2, arg3, arg4, arg5, arg6, pageable)
        .fixedPagedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, T4, T5, T6, R> Stream<R> fixedStream(
      @NonNull Function7<T1, T2, T3, T4, T5, T6, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher)
        .args(arg1, arg2, arg3, arg4, arg5, arg6, pageable)
        .fixedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, T4, T5, T6, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function7<T1, T2, T3, T4, T5, T6, Pageable, Page<R>> fetcher,
      @NonNull Tuple6<T1, T2, T3, T4, T5, T6> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).fixedPagedStream();
  }

  public static <T1, T2, T3, T4, T5, T6, R> Stream<R> fixedStream(
      @NonNull Function7<T1, T2, T3, T4, T5, T6, Pageable, Page<R>> fetcher,
      @NonNull Tuple6<T1, T2, T3, T4, T5, T6> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).fixedStream();
  }

  public static <T1, T2, T3, T4, T5, T6, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function7<T1, T2, T3, T4, T5, T6, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, arg3, arg4, arg5, arg6, pageable).fixedPagedStream();
  }

  public static <T1, T2, T3, T4, T5, T6, R> Stream<R> fixedStream(
      @NonNull Function7<T1, T2, T3, T4, T5, T6, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, arg3, arg4, arg5, arg6, pageable).fixedStream();
  }

  // --- 7 arguments

  public static <T1, T2, T3, T4, T5, T6, T7, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function8<T1, T2, T3, T4, T5, T6, T7, Pageable, Page<R>> fetcher,
      @NonNull Tuple7<T1, T2, T3, T4, T5, T6, T7> args,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher).args(args, pageable).fixedPagedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, R> Stream<R> fixedStream(
      @NonNull Function8<T1, T2, T3, T4, T5, T6, T7, Pageable, Page<R>> fetcher,
      @NonNull Tuple7<T1, T2, T3, T4, T5, T6, T7> args,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher).args(args, pageable).fixedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function8<T1, T2, T3, T4, T5, T6, T7, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @Nullable T7 arg7,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher)
        .args(arg1, arg2, arg3, arg4, arg5, arg6, arg7, pageable)
        .fixedPagedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, R> Stream<R> fixedStream(
      @NonNull Function8<T1, T2, T3, T4, T5, T6, T7, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @Nullable T7 arg7,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher)
        .args(arg1, arg2, arg3, arg4, arg5, arg6, arg7, pageable)
        .fixedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function8<T1, T2, T3, T4, T5, T6, T7, Pageable, Page<R>> fetcher,
      @NonNull Tuple7<T1, T2, T3, T4, T5, T6, T7> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).fixedPagedStream();
  }

  public static <T1, T2, T3, T4, T5, T6, T7, R> Stream<R> fixedStream(
      @NonNull Function8<T1, T2, T3, T4, T5, T6, T7, Pageable, Page<R>> fetcher,
      @NonNull Tuple7<T1, T2, T3, T4, T5, T6, T7> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).fixedStream();
  }

  public static <T1, T2, T3, T4, T5, T6, T7, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function8<T1, T2, T3, T4, T5, T6, T7, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @Nullable T7 arg7,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, arg3, arg4, arg5, arg6, arg7, pageable).fixedPagedStream();
  }

  public static <T1, T2, T3, T4, T5, T6, T7, R> Stream<R> fixedStream(
      @NonNull Function8<T1, T2, T3, T4, T5, T6, T7, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @Nullable T7 arg7,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, arg3, arg4, arg5, arg6, arg7, pageable).fixedStream();
  }

  // --- 8 arguments

  public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function9<T1, T2, T3, T4, T5, T6, T7, T8, Pageable, Page<R>> fetcher,
      @NonNull Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> args,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher).args(args, pageable).fixedPagedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Stream<R> fixedStream(
      @NonNull Function9<T1, T2, T3, T4, T5, T6, T7, T8, Pageable, Page<R>> fetcher,
      @NonNull Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> args,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher).args(args, pageable).fixedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function9<T1, T2, T3, T4, T5, T6, T7, T8, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @Nullable T7 arg7,
      @Nullable T8 arg8,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher)
        .args(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, pageable)
        .fixedPagedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Stream<R> fixedStream(
      @NonNull Function9<T1, T2, T3, T4, T5, T6, T7, T8, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @Nullable T7 arg7,
      @Nullable T8 arg8,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher)
        .args(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, pageable)
        .fixedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function9<T1, T2, T3, T4, T5, T6, T7, T8, Pageable, Page<R>> fetcher,
      @NonNull Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).fixedPagedStream();
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Stream<R> fixedStream(
      @NonNull Function9<T1, T2, T3, T4, T5, T6, T7, T8, Pageable, Page<R>> fetcher,
      @NonNull Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).fixedStream();
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Stream<List<R>> fixedPagedStream(
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
    return of(fetcher)
        .args(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, pageable)
        .fixedPagedStream();
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Stream<R> fixedStream(
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
    return of(fetcher).args(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, pageable).fixedStream();
  }

  // --- 9 arguments

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, Pageable, Page<R>> fetcher,
      @NonNull Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> args,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher).args(args, pageable).fixedPagedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Stream<R> fixedStream(
      @NonNull Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, Pageable, Page<R>> fetcher,
      @NonNull Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> args,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher).args(args, pageable).fixedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Stream<List<R>> fixedPagedStream(
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
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher)
        .args(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, pageable)
        .fixedPagedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Stream<R> fixedStream(
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
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher)
        .args(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, pageable)
        .fixedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, Pageable, Page<R>> fetcher,
      @NonNull Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).fixedPagedStream();
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Stream<R> fixedStream(
      @NonNull Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, Pageable, Page<R>> fetcher,
      @NonNull Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).fixedStream();
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Stream<List<R>> fixedPagedStream(
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
        .fixedPagedStream();
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Stream<R> fixedStream(
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
        .fixedStream();
  }

  // --- 10 arguments

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, Pageable, Page<R>> fetcher,
      @NonNull Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> args,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher).args(args, pageable).fixedPagedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> Stream<R> fixedStream(
      @NonNull Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, Pageable, Page<R>> fetcher,
      @NonNull Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> args,
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher).args(args, pageable).fixedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> Stream<List<R>> fixedPagedStream(
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
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher)
        .args(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, pageable)
        .fixedPagedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> Stream<R> fixedStream(
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
      @NonNull Pageable pageable,
      @NonNull AttemptPolicyFactory attemptPolicyFactory) {
    return of(fetcher)
        .args(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, pageable)
        .fixedStream(attemptPolicyFactory);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> Stream<List<R>> fixedPagedStream(
      @NonNull Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, Pageable, Page<R>> fetcher,
      @NonNull Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).fixedPagedStream();
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> Stream<R> fixedStream(
      @NonNull Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, Pageable, Page<R>> fetcher,
      @NonNull Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).fixedStream();
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> Stream<List<R>> fixedPagedStream(
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
        .fixedPagedStream();
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> Stream<R> fixedStream(
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
        .fixedStream();
  }
}
