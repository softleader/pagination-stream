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

import java.util.function.Function;
import java.util.function.Supplier;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jooq.lambda.function.Function5;
import org.jooq.lambda.tuple.Tuple4;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * A function with 4 arguments and 1 pageable
 *
 * @author Matt Ho
 */
@RequiredArgsConstructor
public class PageFetcher4<T1, T2, T3, T4, R> implements Supplier<Function<Pageable, Page<R>>> {

  @NonNull
  private final Function5<T1, T2, T3, T4, Pageable, Page<R>> fetcher;
  @NonNull
  private final Tuple4<T1, T2, T3, T4> args;

  @Override
  public Function<Pageable, Page<R>> get() {
    return fetcher.applyPartially(args)::apply;
  }
}
