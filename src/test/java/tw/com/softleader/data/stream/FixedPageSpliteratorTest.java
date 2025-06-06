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
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static tw.com.softleader.data.stream.AttemptPolicyFactory.maxAttempts;
import static tw.com.softleader.data.stream.AttemptPolicyFactory.totalPage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

class FixedPageSpliteratorTest {

  @Test
  void testMaxAttemptsExceeded() {
    var totalSize = 30;
    var counter = new AtomicInteger(0);
    PageFetcher<Integer> fetcher =
        pg -> {
          var list = new ArrayList<Integer>();
          for (int i = 0; i < pg.getPageSize(); i++) {
            list.add(counter.incrementAndGet());
          }
          return new PageImpl<>(list, pg, totalSize);
        };

    var spliterator =
        new FixedPageSpliterator<>(
            fetcher, PageRequest.of(0, 10), totalPage()); // 每頁 10 筆, 總共 30 筆, 一共分 3頁, 因此預期會跑上限跑 9 次
    var result = new ArrayList<List<Integer>>();

    // 預期會執行 3 次後拋出 AttemptExhaustedException
    assertThatThrownBy(() -> spliterator.forEachRemaining(result::add))
        .isInstanceOf(AttemptExhaustedException.class)
        .hasMessage("Attempt exhausted after 9 attempts");

    // 確保在最大嘗試次數內，有資料返回
    assertThat(result.size()).isEqualTo(9); // 最大嘗試次數為 9，所以結果應該只有 9 頁
  }

  @Test
  void testMaxAttemptsWithShrinkingData() {
    var data = new AtomicInteger(0);
    var counter = new AtomicInteger(0);
    PageFetcher<Integer> fetcher =
        pageable -> {
          if (counter.incrementAndGet() > 4) {
            return Page.empty(pageable); // 4 次後當成 db 沒資料了
          }
          var list = new ArrayList<Integer>();
          for (int i = 0; i < pageable.getPageSize(); i++) {
            list.add(data.incrementAndGet());
          }
          return new PageImpl<>(list, pageable, 100);
        };

    var spliterator =
        new FixedPageSpliterator<>(fetcher, PageRequest.of(0, 5), maxAttempts(5)); // 設定最大嘗試次數為 5
    var result = new ArrayList<List<Integer>>();

    spliterator.forEachRemaining(result::add);

    var flat = result.stream().flatMap(List::stream).collect(toList());
    assertThat(flat)
        .containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
  }

  @Test
  void testNoPages() {
    PageFetcher<Integer> fetcher = Page::empty; // 所有頁面均為空

    var spliterator =
        new FixedPageSpliterator<>(fetcher, PageRequest.of(0, 100), maxAttempts(3)); // 設定最大嘗試次數為 3
    var result = new ArrayList<List<Integer>>();

    // 所有頁面均為空，不會返回任何資料
    spliterator.forEachRemaining(result::add);

    assertThat(result).isEmpty(); // 沒有資料
  }
}
