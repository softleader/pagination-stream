package tw.com.softleader.data.stream;

import static java.util.Optional.ofNullable;

import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Matt Ho
 */
public class PageSpliterator<T> implements Spliterator<List<T>> {

  static final int PAGED_SPLITERATOR_CHARACTERISTICS = ORDERED | IMMUTABLE | SIZED;

  final Supplier<Function<Pageable, Page<T>>> fetcher;
  Pageable pageable;
  Integer totalPages;

  public PageSpliterator(
      @NonNull Supplier<Function<Pageable, Page<T>>> fetcher, @NonNull Pageable pageable) {
    this.fetcher = fetcher;
    this.pageable = pageable;
  }

  @Override
  public boolean tryAdvance(Consumer<? super List<T>> action) {
    var fetched = fetcher.get().apply(pageable);
    if (totalPages == null) {
      totalPages = fetched.getTotalPages();
    }
    action.accept(fetched.getContent());
    if (fetched.hasNext()) {
      pageable = pageable.next();
      return true;
    }
    return false;
  }

  @Override
  public Spliterator<List<T>> trySplit() {
    return null; // Not supported split for now
  }

  @Override
  public long estimateSize() {
    return ofNullable(totalPages)
        .map(Integer::longValue)
        .orElse(Long.MAX_VALUE);
  }

  @Override
  public int characteristics() {
    return PAGED_SPLITERATOR_CHARACTERISTICS;
  }
}
