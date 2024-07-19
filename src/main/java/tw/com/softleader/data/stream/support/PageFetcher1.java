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

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.jooq.lambda.function.Function2;
import org.jooq.lambda.tuple.Tuple1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tw.com.softleader.data.stream.PageFetcher;

/**
 * A function with 1 argument and 1 pageable
 *
 * @author Matt Ho
 */
@AllArgsConstructor
public class PageFetcher1<T1, R> implements PageFetcher<R> {

  @NonNull private final Function2<T1, Pageable, Page<R>> fetcher;
  @NonNull private final Tuple1<T1> args;

  @Override
  public Page<R> fetch(@NonNull Pageable pageable) {
    return fetcher.applyPartially(args).apply(pageable);
  }
}
