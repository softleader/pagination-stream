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

import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.jooq.lambda.function.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tw.com.softleader.data.stream.support.*;

@NoArgsConstructor(access = PACKAGE)
public class OfPagingSupport {

  public static <R> OfPaging0<R> of(@NonNull Function1<Pageable, Page<R>> fetcher) {
    return new OfPaging0<>(fetcher);
  }

  public static <T1, R> OfPaging1<T1, R> of(@NonNull Function2<T1, Pageable, Page<R>> fetcher) {
    return new OfPaging1<>(fetcher);
  }

  public static <T1, T2, R> OfPaging2<T1, T2, R> of(
      @NonNull Function3<T1, T2, Pageable, Page<R>> fetcher) {
    return new OfPaging2<>(fetcher);
  }

  public static <T1, T2, T3, R> OfPaging3<T1, T2, T3, R> of(
      @NonNull Function4<T1, T2, T3, Pageable, Page<R>> fetcher) {
    return new OfPaging3<>(fetcher);
  }

  public static <T1, T2, T3, T4, R> OfPaging4<T1, T2, T3, T4, R> of(
      @NonNull Function5<T1, T2, T3, T4, Pageable, Page<R>> fetcher) {
    return new OfPaging4<>(fetcher);
  }

  public static <T1, T2, T3, T4, T5, R> OfPaging5<T1, T2, T3, T4, T5, R> of(
      @NonNull Function6<T1, T2, T3, T4, T5, Pageable, Page<R>> fetcher) {
    return new OfPaging5<>(fetcher);
  }

  public static <T1, T2, T3, T4, T5, T6, R> OfPaging6<T1, T2, T3, T4, T5, T6, R> of(
      @NonNull Function7<T1, T2, T3, T4, T5, T6, Pageable, Page<R>> fetcher) {
    return new OfPaging6<>(fetcher);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, R> OfPaging7<T1, T2, T3, T4, T5, T6, T7, R> of(
      @NonNull Function8<T1, T2, T3, T4, T5, T6, T7, Pageable, Page<R>> fetcher) {
    return new OfPaging7<>(fetcher);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, R> OfPaging8<T1, T2, T3, T4, T5, T6, T7, T8, R> of(
      @NonNull Function9<T1, T2, T3, T4, T5, T6, T7, T8, Pageable, Page<R>> fetcher) {
    return new OfPaging8<>(fetcher);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R>
      OfPaging9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> of(
          @NonNull Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, Pageable, Page<R>> fetcher) {
    return new OfPaging9<>(fetcher);
  }

  public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R>
      OfPaging10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> of(
          @NonNull Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, Pageable, Page<R>> fetcher) {
    return new OfPaging10<>(fetcher);
  }
}
