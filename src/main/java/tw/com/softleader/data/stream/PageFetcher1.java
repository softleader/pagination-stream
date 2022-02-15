package tw.com.softleader.data.stream;

import java.util.function.Function;
import java.util.function.Supplier;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.jooq.lambda.function.Function2;
import org.jooq.lambda.tuple.Tuple1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * A function with 1 argument and 1 pageable
 *
 * @author Matt Ho
 */
@AllArgsConstructor
class PageFetcher1<T1, R> implements Supplier<Function<Pageable, Page<R>>> {

  @NonNull
  private final Function2<T1, Pageable, Page<R>> fetcher;
  @NonNull
  private final Tuple1<T1> args;

  @Override
  public Function<Pageable, Page<R>> get() {
    return fetcher.applyPartially(args)::apply;
  }
}
