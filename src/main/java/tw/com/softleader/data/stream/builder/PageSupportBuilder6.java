package tw.com.softleader.data.stream.builder;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.jooq.lambda.function.Function7;
import org.jooq.lambda.tuple.Tuple6;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import tw.com.softleader.data.stream.PageFetcher6;
import tw.com.softleader.data.stream.PageSpliterator;

/**
 * @author Matt Ho
 */
public class PageSupportBuilder6<T1, T2, T3, T4, T5, T6, R> {

  private final Function7<T1, T2, T3, T4, T5, T6, Pageable, Page<R>> fetcher;

  public PageSupportBuilder6(
      Function7<T1, T2, T3, T4, T5, T6, Pageable, Page<R>> fetcher) {
    this.fetcher = fetcher;
  }

  public PageSupportArgs<T1, T2, T3, T4, T5, T6, R> args(
      @NonNull Tuple6<T1, T2, T3, T4, T5, T6> args,
      @NonNull Pageable pageable) {
    return new PageSupportArgs<>(fetcher, args, pageable);
  }

  public PageSupportArgs<T1, T2, T3, T4, T5, T6, R> args(
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @NonNull Pageable pageable) {
    return args(
        new Tuple6<>(arg1, arg2, arg3, arg4, arg5, arg6),
        pageable);
  }

  @AllArgsConstructor(access = AccessLevel.PACKAGE)
  public static class PageSupportArgs<T1, T2, T3, T4, T5, T6, R> {

    @NonNull
    private final Function7<T1, T2, T3, T4, T5, T6, Pageable, Page<R>> fetcher;
    @NonNull
    private final Tuple6<T1, T2, T3, T4, T5, T6> args;
    @NonNull
    private final Pageable pageable;

    public Stream<List<R>> pagedStream() {
      return StreamSupport.stream(
          new PageSpliterator<>(new PageFetcher6<>(fetcher, args), pageable),
          false);
    }

    public Stream<R> stream() {
      return pagedStream()
          .flatMap(Collection::stream);
    }
  }
}
