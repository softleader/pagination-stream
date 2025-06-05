package tw.com.softleader.data.stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    var counter = new AtomicInteger(0);
    PageFetcher<Integer> fetcher =
        pg -> {
          int pageNum = pg.getPageNumber();
          if (pageNum > 4) return Page.empty(pg); // 超過 4 頁返回空頁
          int total = 10 - pageNum * 2;
          if (total <= 0) return Page.empty(pg);
          List<Integer> list = new ArrayList<>();
          for (int i = 0; i < total; i++) {
            list.add(counter.incrementAndGet());
          }
          return new PageImpl<>(list, pg, 100);
        };

    var spliterator = new FixedPageSpliterator<>(fetcher, PageRequest.of(0, 100), 3); // 設定最大嘗試次數為 3
    var result = new ArrayList<List<Integer>>();

    // 預期會執行 3 次後拋出 AttemptExhaustedException
    assertThatThrownBy(() -> spliterator.forEachRemaining(result::add))
        .isInstanceOf(AttemptExhaustedException.class)
        .hasMessage("Attempt exhausted after 3 attempts");

    // 確保在最大嘗試次數內，有資料返回
    assertThat(result.size()).isEqualTo(3); // 最大嘗試次數為 3，所以結果應該只有 3 頁
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

    var spliterator = new FixedPageSpliterator<>(fetcher, PageRequest.of(0, 5), 5); // 設定最大嘗試次數為 5
    var result = new ArrayList<List<Integer>>();

    spliterator.forEachRemaining(result::add);

    var flat = result.stream().flatMap(List::stream).collect(toList());
    assertThat(flat)
        .containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
  }

  @Test
  void testNoPages() {
    var counter = new AtomicInteger(0);
    PageFetcher<Integer> fetcher = pg -> Page.empty(pg); // 所有頁面均為空

    var spliterator = new FixedPageSpliterator<>(fetcher, PageRequest.of(0, 100), 3); // 設定最大嘗試次數為 3
    var result = new ArrayList<List<Integer>>();

    // 所有頁面均為空，不會返回任何資料
    spliterator.forEachRemaining(result::add);

    assertThat(result).isEmpty(); // 沒有資料
  }
}
