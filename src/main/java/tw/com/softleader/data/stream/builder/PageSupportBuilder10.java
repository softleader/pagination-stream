package tw.com.softleader.data.stream.builder;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.jooq.lambda.function.Function11;
import org.jooq.lambda.tuple.Tuple10;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import tw.com.softleader.data.stream.PageFetcher10;
import tw.com.softleader.data.stream.PageSpliterator;

/**
 * @author Matt Ho
 */
public class PageSupportBuilder10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> {

  private final Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, Pageable, Page<R>> fetcher;

  public PageSupportBuilder10(
      Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, Pageable, Page<R>> fetcher) {
    this.fetcher = fetcher;
  }

  public PageSupportArgs<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> args(
      @NonNull Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> args,
      @NonNull Pageable pageable) {
    return new PageSupportArgs<>(fetcher, args, pageable);
  }

  public PageSupportArgs<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> args(
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @Nullable T7 arg7,
      @Nullable T8 arg8,
      @Nullable T9 arg9,
      @Nullable T10 arg10,
      @NonNull Pageable pageable) {
    return args(
        new Tuple10<>(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10),
        pageable);
  }

  @AllArgsConstructor(access = AccessLevel.PACKAGE)
  public static class PageSupportArgs<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> {

    @NonNull
    private final Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, Pageable, Page<R>> fetcher;
    @NonNull
    private final Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> args;
    @NonNull
    private final Pageable pageable;

    public Stream<List<R>> pagedStream() {
      return StreamSupport.stream(
          new PageSpliterator<>(new PageFetcher10<>(fetcher, args), pageable),
          false);
    }

    public Stream<R> stream() {
      return pagedStream()
          .flatMap(Collection::stream);
    }
  }
}
