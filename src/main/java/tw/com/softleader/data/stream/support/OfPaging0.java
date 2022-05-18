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
import org.jooq.lambda.function.Function1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tw.com.softleader.data.stream.PageSpliterator;
import tw.com.softleader.data.stream.Paging;

/**
 * 0 個參數加 {@code Pageable} 的分頁 Stream
 *
 * @author Matt Ho
 */
@RequiredArgsConstructor
public class OfPaging0<R> {

  @NonNull
  private final Function1<Pageable, Page<R>> fetcher;

  public Paging<R> args(
      @NonNull Pageable pageable) {
    return new Paging0<>(fetcher, pageable);
  }

  @RequiredArgsConstructor(access = PACKAGE)
  static class Paging0<R> implements Paging<R> {

    @NonNull
    private final Function1<Pageable, Page<R>> fetcher;
    @NonNull
    private final Pageable pageable;

    @Override
    public Stream<List<R>> pagedStream() {
      return StreamSupport.stream(
          new PageSpliterator<>(new PageFetcher0<>(fetcher), pageable),
          false);
    }
  }
}
