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

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Matt Ho
 */
public interface PageStreamBuilder<R> {

  /**
   * Creates a new sequential {@code Stream} from {@code PageSpliterator} of every page
   */
  Stream<List<R>> pagedStream();

  /**
   * Creates a new sequential {@code Stream} from {@code PageSpliterator} of the results of each
   * element of {@link #pagedStream()}
   */
  default Stream<R> stream() {
    return pagedStream().flatMap(Collection::stream);
  }
}
