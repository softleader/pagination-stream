package tw.com.softleader.data.stream.builder;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.jooq.lambda.function.Function8;
import org.jooq.lambda.tuple.Tuple7;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import tw.com.softleader.data.stream.PageFetcher7;
import tw.com.softleader.data.stream.PageSpliterator;

/**
 * @author Matt Ho
 */
public class PageSupportBuilder7<T1, T2, T3, T4, T5, T6, T7, R> {

  private final Function8<T1, T2, T3, T4, T5, T6, T7, Pageable, Page<R>> fetcher;

  public PageSupportBuilder7(
      Function8<T1, T2, T3, T4, T5, T6, T7, Pageable, Page<R>> fetcher) {
    this.fetcher = fetcher;
  }

  public PageSupportArgs<T1, T2, T3, T4, T5, T6, T7, R> args(
      @NonNull Tuple7<T1, T2, T3, T4, T5, T6, T7> args,
      @NonNull Pageable pageable) {
    return new PageSupportArgs<>(fetcher, args, pageable);
  }

  public PageSupportArgs<T1, T2, T3, T4, T5, T6, T7, R> args(
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @Nullable T7 arg7,
      @NonNull Pageable pageable) {
    return args(
        new Tuple7<>(arg1, arg2, arg3, arg4, arg5, arg6, arg7),
        pageable);
  }

  @AllArgsConstructor(access = AccessLevel.PACKAGE)
  public static class PageSupportArgs<T1, T2, T3, T4, T5, T6, T7, R> {

    @NonNull
    private final Function8<T1, T2, T3, T4, T5, T6, T7, Pageable, Page<R>> fetcher;
    @NonNull
    private final Tuple7<T1, T2, T3, T4, T5, T6, T7> args;
    @NonNull
    private final Pageable pageable;

    public Stream<List<R>> pagedStream() {
      return StreamSupport.stream(
          new PageSpliterator<>(new PageFetcher7<>(fetcher, args), pageable),
          false);
    }

    public Stream<R> stream() {
      return pagedStream()
          .flatMap(Collection::stream);
    }
  }
}
