package tw.com.softleader.data.stream;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.List;
import java.util.stream.LongStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

class PageFetcher2Test {

  static final int TOTAL_PAGES = 5;

  @Test
  void test() {
    var api = new Api();
    var pageable = Pageable.ofSize(10);

    var collect = PageSupport
        .pagedStream(api::call, 1, 2, pageable)
        .parallel() // 雖然當前不支援, 但還是可以呼叫 parallel 只是沒作用而已
        .collect(toList());
    Assertions.assertThat(collect).hasSize(TOTAL_PAGES);

    var sum = collect.stream()
        .flatMap(Collection::stream)
        .mapToLong(Long::longValue)
        .sum();
    Assertions.assertThat(sum).isEqualTo(20 * 1 * 2);
  }

  static class Api {

    Page<Long> call(int a, int b, Pageable pageable) {
      var pageAt = pageable.getPageNumber(); // start from 0

      if (pageAt >= TOTAL_PAGES) {
        return new PageImpl<>(List.of(), pageable, pageable.getPageSize() * (long) TOTAL_PAGES);
      }

      // fake data
      var data = LongStream.rangeClosed(0, pageAt)
          .boxed()
          .map(l -> l * a * b)
          .collect(toList());

      return new PageImpl<>(data, pageable, pageable.getPageSize() * (long) TOTAL_PAGES);
    }
  }

}
