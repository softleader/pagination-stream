package tw.com.softleader.data.stream.builder;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.jooq.lambda.function.Function1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tw.com.softleader.data.stream.PageFetcher0;
import tw.com.softleader.data.stream.PageSpliterator;

/**
 * @author Matt Ho
 */
public class PageSupportBuilder0<R> {

  private final Function1<Pageable, Page<R>> fetcher;

  public PageSupportBuilder0(
      Function1<Pageable, Page<R>> fetcher) {
    this.fetcher = fetcher;
  }

  public PageSupportArgs<R> args(
      @NonNull Pageable pageable) {
    return new PageSupportArgs<>(fetcher, pageable);
  }

  @AllArgsConstructor(access = AccessLevel.PACKAGE)
  public static class PageSupportArgs<R> {

    @NonNull
    private final Function1<Pageable, Page<R>> fetcher;
    @NonNull
    private final Pageable pageable;

    public Stream<List<R>> pagedStream() {
      return StreamSupport.stream(
          new PageSpliterator<>(new PageFetcher0<>(fetcher), pageable),
          false);
    }

    public Stream<R> stream() {
      return pagedStream()
          .flatMap(Collection::stream);
    }
  }
}
