package tw.com.softleader.data.stream;

import java.util.function.Function;
import java.util.function.Supplier;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jooq.lambda.function.Function5;
import org.jooq.lambda.tuple.Tuple4;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * A function with 4 arguments and 1 pageable
 *
 * @author Matt Ho
 */
@RequiredArgsConstructor
public class PageFetcher4<T1, T2, T3, T4, R> implements Supplier<Function<Pageable, Page<R>>> {

  @NonNull
  private final Function5<T1, T2, T3, T4, Pageable, Page<R>> fetcher;
  @NonNull
  private final Tuple4<T1, T2, T3, T4> args;

  @Override
  public Function<Pageable, Page<R>> get() {
    return fetcher.applyPartially(args)::apply;
  }
}
