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

import static org.springframework.util.Assert.isTrue;

import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;

/**
 * {@link AttemptPolicy} 工廠
 *
 * @author Matt Ho
 */
public interface AttemptPolicyFactory {

  int DEFAULT_TOTAL_PAGE_BUFFER_MULTIPLIER = 3;

  AttemptPolicy create(@NonNull Page<?> firstPage);

  /** 不限制嘗試次數 */
  static AttemptPolicyFactory unlimited() {
    return firstPage -> currentAttempt -> true;
  }

  /** 指定最大嘗試次數 */
  static AttemptPolicyFactory maxAttempts(long maxAttempts) {
    return firstPage -> currentAttempt -> currentAttempt < maxAttempts;
  }

  /** 以 {@link #DEFAULT_TOTAL_PAGE_BUFFER_MULTIPLIER} 計算 {@link #totalPage(int)} */
  static AttemptPolicyFactory totalPage() {
    return totalPage(DEFAULT_TOTAL_PAGE_BUFFER_MULTIPLIER);
  }

  /**
   * 以第一頁的 {@code totalPages * bufferMultiplier} 做為最大嘗試次數
   *
   * @param bufferMultiplier 用來計算最大嘗試次數所預留的緩衝倍數, 以避免太容易達到上限
   * @throws IllegalArgumentException if bufferMultiplier <= 0
   */
  static AttemptPolicyFactory totalPage(int bufferMultiplier) {
    isTrue(bufferMultiplier > 0, "bufferMultiplier must > 0");
    return firstPage -> {
      long maxAttempts = (long) firstPage.getTotalPages() * bufferMultiplier;
      return maxAttempts(maxAttempts).create(firstPage);
    };
  }
}
