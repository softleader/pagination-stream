package tw.com.softleader.data.stream;

import java.util.function.Function;
import java.util.function.Supplier;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.jooq.lambda.function.Function1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * A function with 0 arguments and 1 pageable
 *
 * @author Matt Ho
 */
@AllArgsConstructor
class PageFetcher0<R> implements Supplier<Function<Pageable, Page<R>>> {

  @NonNull
  private final Function1<Pageable, Page<R>> fetcher;

  @Override
  public Function<Pageable, Page<R>> get() {
    return fetcher::apply;
  }
}
