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

import static java.util.stream.Collectors.toList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.stream.LongStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

class PageFetcher1Test {

  static final int TOTAL_PAGES = 5;

  @Test
  void test() {
    var api = spy(Api.class);
    var pageable = Pageable.ofSize(10);

    var sum = PageSupport
        .stream(api::call, 10, pageable)
        .parallel() // 雖然當前不支援, 但還是可以呼叫 parallel 只是沒作用而已
        .mapToLong(Long::longValue)
        .sum();

    Assertions.assertThat(sum).isEqualTo(
        ((1) + (1 + 2) + (1 + 2 + 3) + (1 + 2 + 3 + 4) + (1 + 2 + 3 + 4 + 5))
            * 10);

    verify(api, times(1)).call(10, pageable); // 第一次的分頁應該只 fetch 一次
    verify(api, times(TOTAL_PAGES)).call(eq(10), any(Pageable.class));
  }

  static class Api {

    Page<Long> call(int a, Pageable pageable) {
      var pageAt = pageable.getPageNumber(); // start from 0

      if (pageAt >= TOTAL_PAGES) {
        return new PageImpl<>(List.of(), pageable, pageable.getPageSize() * (long) TOTAL_PAGES);
      }

      // fake data
      var data = LongStream.rangeClosed(0, pageAt + 1)
          .boxed()
          .map(l -> l * a)
          .collect(toList());

      return new PageImpl<>(data, pageable, pageable.getPageSize() * (long) TOTAL_PAGES);
    }
  }

}
