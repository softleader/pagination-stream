package tw.com.softleader.data.stream.builder;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.jooq.lambda.function.Function3;
import org.jooq.lambda.tuple.Tuple2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import tw.com.softleader.data.stream.PageFetcher2;
import tw.com.softleader.data.stream.PageSpliterator;

/**
 * @author Matt Ho
 */
public class PageSupportBuilder2<T1, T2, R> {

  private final Function3<T1, T2, Pageable, Page<R>> fetcher;

  public PageSupportBuilder2(
      Function3<T1, T2, Pageable, Page<R>> fetcher) {
    this.fetcher = fetcher;
  }

  public PageSupportArgs<T1, T2, R> args(
      @NonNull Tuple2<T1, T2> args,
      @NonNull Pageable pageable) {
    return new PageSupportArgs<>(fetcher, args, pageable);
  }

  public PageSupportArgs<T1, T2, R> args(
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @NonNull Pageable pageable) {
    return args(
        new Tuple2<>(arg1, arg2),
        pageable);
  }

  @AllArgsConstructor(access = AccessLevel.PACKAGE)
  public static class PageSupportArgs<T1, T2, R> {

    @NonNull
    private final Function3<T1, T2, Pageable, Page<R>> fetcher;
    @NonNull
    private final Tuple2<T1, T2> args;
    @NonNull
    private final Pageable pageable;

    public Stream<List<R>> pagedStream() {
      return StreamSupport.stream(
          new PageSpliterator<>(new PageFetcher2<>(fetcher, args), pageable),
          false);
    }

    public Stream<R> stream() {
      return pagedStream()
          .flatMap(Collection::stream);
    }
  }
}
