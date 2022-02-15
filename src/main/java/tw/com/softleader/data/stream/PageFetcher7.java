package tw.com.softleader.data.stream;

import java.util.function.Function;
import java.util.function.Supplier;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jooq.lambda.function.Function8;
import org.jooq.lambda.tuple.Tuple7;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * A function with 7 arguments and 1 pageable
 *
 * @author Matt Ho
 */
@RequiredArgsConstructor
public class PageFetcher7<T1, T2, T3, T4, T5, T6, T7, R> implements
    Supplier<Function<Pageable, Page<R>>> {

  @NonNull
  private final Function8<T1, T2, T3, T4, T5, T6, T7, Pageable, Page<R>> fetcher;
  @NonNull
  private final Tuple7<T1, T2, T3, T4, T5, T6, T7> args;

  @Override
  public Function<Pageable, Page<R>> get() {
    return fetcher.applyPartially(args)::apply;
  }
}
