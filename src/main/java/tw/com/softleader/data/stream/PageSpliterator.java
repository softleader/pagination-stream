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

import static java.util.Optional.ofNullable;

import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Matt Ho
 */
public class PageSpliterator<T> implements Spliterator<List<T>> {

  static final int PAGED_SPLITERATOR_CHARACTERISTICS = ORDERED | IMMUTABLE | SIZED;

  final Supplier<Function<Pageable, Page<T>>> fetcher;
  Pageable pageable;
  Integer totalPages;

  public PageSpliterator(
      @NonNull Supplier<Function<Pageable, Page<T>>> fetcher, @NonNull Pageable pageable) {
    this.fetcher = fetcher;
    this.pageable = pageable;
  }

  @Override
  public boolean tryAdvance(Consumer<? super List<T>> action) {
    var fetched = fetcher.get().apply(pageable);
    if (totalPages == null) {
      totalPages = fetched.getTotalPages();
    }
    action.accept(fetched.getContent());
    if (fetched.hasNext()) {
      pageable = pageable.next();
      return true;
    }
    return false;
  }

  @Override
  public Spliterator<List<T>> trySplit() {
    return null; // Not supported split for now
  }

  @Override
  public long estimateSize() {
    return ofNullable(totalPages)
        .map(Integer::longValue)
        .orElse(Long.MAX_VALUE);
  }

  @Override
  public int characteristics() {
    return PAGED_SPLITERATOR_CHARACTERISTICS;
  }
}
