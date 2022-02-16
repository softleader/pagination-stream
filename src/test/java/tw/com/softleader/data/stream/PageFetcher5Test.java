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

class PageFetcher5Test {

  static final int TOTAL_PAGES = 5;

  @Test
  void test() {
    var api = spy(Api.class);
    var pageable = Pageable.ofSize(10);

    var sum = PageSupport
        .stream(api::call, 10, 2, 3, 4, 5, pageable)
        .parallel() // 雖然當前不支援, 但還是可以呼叫 parallel 只是沒作用而已
        .mapToLong(Long::longValue)
        .sum();

    Assertions.assertThat(sum).isEqualTo(
        ((1) + (1 + 2) + (1 + 2 + 3) + (1 + 2 + 3 + 4) + (1 + 2 + 3 + 4 + 5))
            * 10 * 2 * 3 * 4 * 5);

    verify(api, times(1)).call(10, 2, 3, 4, 5, pageable); // 第一次的分頁應該只 fetch 一次
    verify(api, times(TOTAL_PAGES)).call(
        eq(10), eq(2), eq(3), eq(4), eq(5), any(Pageable.class));
  }

  static class Api {

    Page<Long> call(int a, int b, int c, int d, int e, Pageable pageable) {
      var pageAt = pageable.getPageNumber(); // start from 0

      if (pageAt >= TOTAL_PAGES) {
        return new PageImpl<>(List.of(), pageable, pageable.getPageSize() * (long) TOTAL_PAGES);
      }

      // fake data
      var data = LongStream.rangeClosed(0, pageAt + 1)
          .boxed()
          .map(l -> l * a * b * c * d * e)
          .collect(toList());

      return new PageImpl<>(data, pageable, pageable.getPageSize() * (long) TOTAL_PAGES);
    }
  }

}
