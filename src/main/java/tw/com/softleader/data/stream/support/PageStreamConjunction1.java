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

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.jooq.lambda.function.Function2;
import org.jooq.lambda.tuple.Tuple1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import tw.com.softleader.data.stream.PageSpliterator;
import tw.com.softleader.data.stream.PageStreamBuilder;

/**
 * @author Matt Ho
 */
public class PageStreamConjunction1<T1, R> {

  private final Function2<T1, Pageable, Page<R>> fetcher;

  public PageStreamConjunction1(
      @NonNull Function2<T1, Pageable, Page<R>> fetcher) {
    this.fetcher = fetcher;
  }

  public PageStreamBuilder<R> args(
      @NonNull Tuple1<T1> args,
      @NonNull Pageable pageable) {
    return new PageStreamBuilder1<>(fetcher, args, pageable);
  }

  public PageStreamBuilder<R> args(
      @Nullable T1 arg1,
      @NonNull Pageable pageable) {
    return args(
        new Tuple1<>(arg1),
        pageable);
  }

  @AllArgsConstructor(access = AccessLevel.PACKAGE)
  static class PageStreamBuilder1<T1, R> implements PageStreamBuilder<R> {

    @NonNull
    private final Function2<T1, Pageable, Page<R>> fetcher;
    @NonNull
    private final Tuple1<T1> args;
    @NonNull
    private final Pageable pageable;

    @Override
    public Stream<List<R>> pagedStream() {
      return StreamSupport.stream(
          new PageSpliterator<>(new PageFetcher1<>(fetcher, args), pageable),
          false);
    }
  }
}
