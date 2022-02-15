package tw.com.softleader.data.stream;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.jooq.lambda.function.Function1;
import org.jooq.lambda.function.Function10;
import org.jooq.lambda.function.Function11;
import org.jooq.lambda.function.Function2;
import org.jooq.lambda.function.Function3;
import org.jooq.lambda.function.Function4;
import org.jooq.lambda.function.Function5;
import org.jooq.lambda.function.Function6;
import org.jooq.lambda.function.Function7;
import org.jooq.lambda.function.Function8;
import org.jooq.lambda.function.Function9;
import org.jooq.lambda.tuple.Tuple1;
import org.jooq.lambda.tuple.Tuple10;
import org.jooq.lambda.tuple.Tuple2;
import org.jooq.lambda.tuple.Tuple3;
import org.jooq.lambda.tuple.Tuple4;
import org.jooq.lambda.tuple.Tuple5;
import org.jooq.lambda.tuple.Tuple6;
import org.jooq.lambda.tuple.Tuple7;
import org.jooq.lambda.tuple.Tuple8;
import org.jooq.lambda.tuple.Tuple9;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Matt Ho
 */
@UtilityClass
public class PageSupport {

  // --- 0 argument

  public <R> Stream<List<R>> pagedStream(
      @NonNull Function1<Pageable, Page<R>> fetcher,
      @NonNull Pageable pageable) {
    return StreamSupport.stream(
        new PageSpliterator<>(new PageFetcher0<>(fetcher), pageable),
        false);
  }

  public <R> Stream<R> stream(
      @NonNull Function1<Pageable, Page<R>> fetcher,
      @NonNull Pageable pageable) {
    return pagedStream(fetcher, pageable).flatMap(Collection::stream);
  }

  // --- 1 argument

  public <T1, R> Stream<List<R>> pagedStream(
      @NonNull Function2<T1, Pageable, Page<R>> fetcher,
      @NonNull Tuple1<T1> args,
      @NonNull Pageable pageable) {
    return StreamSupport.stream(
        new PageSpliterator<>(new PageFetcher1<>(fetcher, args), pageable),
        false);
  }

  public <T1, R> Stream<R> stream(
      @NonNull Function2<T1, Pageable, Page<R>> fetcher,
      @NonNull Tuple1<T1> args,
      @NonNull Pageable pageable) {
    return pagedStream(fetcher, args, pageable).flatMap(Collection::stream);
  }

  public <T1, R> Stream<List<R>> pagedStream(
      @NonNull Function2<T1, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @NonNull Pageable pageable) {
    return pagedStream(fetcher, new Tuple1<>(arg1), pageable);
  }

  public <T1, R> Stream<R> stream(
      @NonNull Function2<T1, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @NonNull Pageable pageable) {
    return stream(fetcher, new Tuple1<>(arg1), pageable);
  }

  // --- 2 arguments

  public <T1, T2, R> Stream<List<R>> pagedStream(
      @NonNull Function3<T1, T2, Pageable, Page<R>> fetcher,
      @NonNull Tuple2<T1, T2> args,
      @NonNull Pageable pageable) {
    return StreamSupport.stream(
        new PageSpliterator<>(new PageFetcher2<>(fetcher, args), pageable),
        false);
  }

  public <T1, T2, R> Stream<R> stream(
      @NonNull Function3<T1, T2, Pageable, Page<R>> fetcher,
      @NonNull Tuple2<T1, T2> args,
      @NonNull Pageable pageable) {
    return pagedStream(fetcher, args, pageable).flatMap(Collection::stream);
  }

  public <T1, T2, R> Stream<List<R>> pagedStream(
      @NonNull Function3<T1, T2, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @NonNull Pageable pageable) {
    return pagedStream(fetcher, new Tuple2<>(arg1, arg2), pageable);
  }

  public <T1, T2, R> Stream<R> stream(
      @NonNull Function3<T1, T2, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @NonNull Pageable pageable) {
    return stream(fetcher, new Tuple2<>(arg1, arg2), pageable);
  }

  // --- 3 arguments

  public <T1, T2, T3, R> Stream<List<R>> pagedStream(
      @NonNull Function4<T1, T2, T3, Pageable, Page<R>> fetcher,
      @NonNull Tuple3<T1, T2, T3> args,
      @NonNull Pageable pageable) {
    return StreamSupport.stream(
        new PageSpliterator<>(new PageFetcher3<>(fetcher, args), pageable),
        false);
  }

  public <T1, T2, T3, R> Stream<R> stream(
      @NonNull Function4<T1, T2, T3, Pageable, Page<R>> fetcher,
      @NonNull Tuple3<T1, T2, T3> args,
      @NonNull Pageable pageable) {
    return pagedStream(fetcher, args, pageable).flatMap(Collection::stream);
  }

  public <T1, T2, T3, R> Stream<List<R>> pagedStream(
      @NonNull Function4<T1, T2, T3, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @NonNull Pageable pageable) {
    return pagedStream(fetcher, new Tuple3<>(arg1, arg2, arg3), pageable);
  }

  public <T1, T2, T3, R> Stream<R> stream(
      @NonNull Function4<T1, T2, T3, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @NonNull Pageable pageable) {
    return stream(fetcher, new Tuple3<>(arg1, arg2, arg3), pageable);
  }

  // --- 4 arguments

  public <T1, T2, T3, T4, R> Stream<List<R>> pagedStream(
      @NonNull Function5<T1, T2, T3, T4, Pageable, Page<R>> fetcher,
      @NonNull Tuple4<T1, T2, T3, T4> args,
      @NonNull Pageable pageable) {
    return StreamSupport.stream(
        new PageSpliterator<>(new PageFetcher4<>(fetcher, args), pageable),
        false);
  }

  public <T1, T2, T3, T4, R> Stream<R> stream(
      @NonNull Function5<T1, T2, T3, T4, Pageable, Page<R>> fetcher,
      @NonNull Tuple4<T1, T2, T3, T4> args,
      @NonNull Pageable pageable) {
    return pagedStream(fetcher, args, pageable).flatMap(Collection::stream);
  }

  public <T1, T2, T3, T4, R> Stream<List<R>> pagedStream(
      @NonNull Function5<T1, T2, T3, T4, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @NonNull Pageable pageable) {
    return pagedStream(fetcher, new Tuple4<>(arg1, arg2, arg3, arg4), pageable);
  }

  public <T1, T2, T3, T4, R> Stream<R> stream(
      @NonNull Function5<T1, T2, T3, T4, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @NonNull Pageable pageable) {
    return stream(fetcher, new Tuple4<>(arg1, arg2, arg3, arg4), pageable);
  }

  // --- 5 arguments

  public <T1, T2, T3, T4, T5, R> Stream<List<R>> pagedStream(
      @NonNull Function6<T1, T2, T3, T4, T5, Pageable, Page<R>> fetcher,
      @NonNull Tuple5<T1, T2, T3, T4, T5> args,
      @NonNull Pageable pageable) {
    return StreamSupport.stream(
        new PageSpliterator<>(new PageFetcher5<>(fetcher, args), pageable),
        false);
  }

  public <T1, T2, T3, T4, T5, R> Stream<R> stream(
      @NonNull Function6<T1, T2, T3, T4, T5, Pageable, Page<R>> fetcher,
      @NonNull Tuple5<T1, T2, T3, T4, T5> args,
      @NonNull Pageable pageable) {
    return pagedStream(fetcher, args, pageable).flatMap(Collection::stream);
  }

  public <T1, T2, T3, T4, T5, R> Stream<List<R>> pagedStream(
      @NonNull Function6<T1, T2, T3, T4, T5, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @NonNull Pageable pageable) {
    return pagedStream(fetcher, new Tuple5<>(arg1, arg2, arg3, arg4, arg5), pageable);
  }

  public <T1, T2, T3, T4, T5, R> Stream<R> stream(
      @NonNull Function6<T1, T2, T3, T4, T5, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @NonNull Pageable pageable) {
    return stream(fetcher, new Tuple5<>(arg1, arg2, arg3, arg4, arg5), pageable);
  }

  // --- 6 arguments

  public <T1, T2, T3, T4, T5, T6, R> Stream<List<R>> pagedStream(
      @NonNull Function7<T1, T2, T3, T4, T5, T6, Pageable, Page<R>> fetcher,
      @NonNull Tuple6<T1, T2, T3, T4, T5, T6> args,
      @NonNull Pageable pageable) {
    return StreamSupport.stream(
        new PageSpliterator<>(new PageFetcher6<>(fetcher, args), pageable),
        false);
  }

  public <T1, T2, T3, T4, T5, T6, R> Stream<R> stream(
      @NonNull Function7<T1, T2, T3, T4, T5, T6, Pageable, Page<R>> fetcher,
      @NonNull Tuple6<T1, T2, T3, T4, T5, T6> args,
      @NonNull Pageable pageable) {
    return pagedStream(fetcher, args, pageable).flatMap(Collection::stream);
  }

  public <T1, T2, T3, T4, T5, T6, R> Stream<List<R>> pagedStream(
      @NonNull Function7<T1, T2, T3, T4, T5, T6, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @NonNull Pageable pageable) {
    return pagedStream(fetcher, new Tuple6<>(arg1, arg2, arg3, arg4, arg5, arg6), pageable);
  }

  public <T1, T2, T3, T4, T5, T6, R> Stream<R> stream(
      @NonNull Function7<T1, T2, T3, T4, T5, T6, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @NonNull Pageable pageable) {
    return stream(fetcher, new Tuple6<>(arg1, arg2, arg3, arg4, arg5, arg6), pageable);
  }

  // --- 7 arguments

  public <T1, T2, T3, T4, T5, T6, T7, R> Stream<List<R>> pagedStream(
      @NonNull Function8<T1, T2, T3, T4, T5, T6, T7, Pageable, Page<R>> fetcher,
      @NonNull Tuple7<T1, T2, T3, T4, T5, T6, T7> args,
      @NonNull Pageable pageable) {
    return StreamSupport.stream(
        new PageSpliterator<>(new PageFetcher7<>(fetcher, args), pageable),
        false);
  }

  public <T1, T2, T3, T4, T5, T6, T7, R> Stream<R> stream(
      @NonNull Function8<T1, T2, T3, T4, T5, T6, T7, Pageable, Page<R>> fetcher,
      @NonNull Tuple7<T1, T2, T3, T4, T5, T6, T7> args,
      @NonNull Pageable pageable) {
    return pagedStream(fetcher, args, pageable).flatMap(Collection::stream);
  }

  public <T1, T2, T3, T4, T5, T6, T7, R> Stream<List<R>> pagedStream(
      @NonNull Function8<T1, T2, T3, T4, T5, T6, T7, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @Nullable T7 arg7,
      @NonNull Pageable pageable) {
    return pagedStream(fetcher, new Tuple7<>(arg1, arg2, arg3, arg4, arg5, arg6, arg7), pageable);
  }

  public <T1, T2, T3, T4, T5, T6, T7, R> Stream<R> stream(
      @NonNull Function8<T1, T2, T3, T4, T5, T6, T7, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @Nullable T7 arg7,
      @NonNull Pageable pageable) {
    return stream(fetcher, new Tuple7<>(arg1, arg2, arg3, arg4, arg5, arg6, arg7), pageable);
  }

  // --- 8 arguments

  public <T1, T2, T3, T4, T5, T6, T7, T8, R> Stream<List<R>> pagedStream(
      @NonNull Function9<T1, T2, T3, T4, T5, T6, T7, T8, Pageable, Page<R>> fetcher,
      @NonNull Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> args,
      @NonNull Pageable pageable) {
    return StreamSupport.stream(
        new PageSpliterator<>(new PageFetcher8<>(fetcher, args), pageable),
        false);
  }

  public <T1, T2, T3, T4, T5, T6, T7, T8, R> Stream<R> stream(
      @NonNull Function9<T1, T2, T3, T4, T5, T6, T7, T8, Pageable, Page<R>> fetcher,
      @NonNull Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> args,
      @NonNull Pageable pageable) {
    return pagedStream(fetcher, args, pageable).flatMap(Collection::stream);
  }

  public <T1, T2, T3, T4, T5, T6, T7, T8, R> Stream<List<R>> pagedStream(
      @NonNull Function9<T1, T2, T3, T4, T5, T6, T7, T8, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @Nullable T7 arg7,
      @Nullable T8 arg8,
      @NonNull Pageable pageable) {
    return pagedStream(fetcher, new Tuple8<>(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8),
        pageable);
  }

  public <T1, T2, T3, T4, T5, T6, T7, T8, R> Stream<R> stream(
      @NonNull Function9<T1, T2, T3, T4, T5, T6, T7, T8, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @Nullable T7 arg7,
      @Nullable T8 arg8,
      @NonNull Pageable pageable) {
    return stream(fetcher, new Tuple8<>(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8), pageable);
  }

  // --- 9 arguments

  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Stream<List<R>> pagedStream(
      @NonNull Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, Pageable, Page<R>> fetcher,
      @NonNull Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> args,
      @NonNull Pageable pageable) {
    return StreamSupport.stream(
        new PageSpliterator<>(new PageFetcher9<>(fetcher, args), pageable),
        false);
  }

  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Stream<R> stream(
      @NonNull Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, Pageable, Page<R>> fetcher,
      @NonNull Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> args,
      @NonNull Pageable pageable) {
    return pagedStream(fetcher, args, pageable).flatMap(Collection::stream);
  }

  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Stream<List<R>> pagedStream(
      @NonNull Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @Nullable T7 arg7,
      @Nullable T8 arg8,
      @Nullable T9 arg9,
      @NonNull Pageable pageable) {
    return pagedStream(fetcher, new Tuple9<>(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9),
        pageable);
  }

  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Stream<R> stream(
      @NonNull Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @Nullable T6 arg6,
      @Nullable T7 arg7,
      @Nullable T8 arg8,
      @Nullable T9 arg9,
      @NonNull Pageable pageable) {
    return stream(fetcher, new Tuple9<>(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9),
        pageable);
  }

  // --- 10 arguments

  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> Stream<List<R>> pagedStream(
      @NonNull Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, Pageable, Page<R>> fetcher,
      @NonNull Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> args,
      @NonNull Pageable pageable) {
    return StreamSupport.stream(
        new PageSpliterator<>(new PageFetcher10<>(fetcher, args), pageable),
        false);
  }

  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> Stream<R> stream(
      @NonNull Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, Pageable, Page<R>> fetcher,
      @NonNull Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> args,
      @NonNull Pageable pageable) {
    return pagedStream(fetcher, args, pageable).flatMap(Collection::stream);
  }

  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> Stream<List<R>> pagedStream(
      @NonNull Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, Pageable, Page<R>> fetcher,
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
    return pagedStream(fetcher,
        new Tuple10<>(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10),
        pageable);
  }

  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> Stream<R> stream(
      @NonNull Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, Pageable, Page<R>> fetcher,
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
    return stream(fetcher,
        new Tuple10<>(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10),
        pageable);
  }
}
