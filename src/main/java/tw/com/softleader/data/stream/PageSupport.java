package tw.com.softleader.data.stream;

import java.util.List;
import java.util.stream.Stream;
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
import tw.com.softleader.data.stream.builder.PageSupportBuilder0;
import tw.com.softleader.data.stream.builder.PageSupportBuilder1;
import tw.com.softleader.data.stream.builder.PageSupportBuilder10;
import tw.com.softleader.data.stream.builder.PageSupportBuilder2;
import tw.com.softleader.data.stream.builder.PageSupportBuilder3;
import tw.com.softleader.data.stream.builder.PageSupportBuilder4;
import tw.com.softleader.data.stream.builder.PageSupportBuilder5;
import tw.com.softleader.data.stream.builder.PageSupportBuilder6;
import tw.com.softleader.data.stream.builder.PageSupportBuilder7;
import tw.com.softleader.data.stream.builder.PageSupportBuilder8;
import tw.com.softleader.data.stream.builder.PageSupportBuilder9;

/**
 * @author Matt Ho
 */
@UtilityClass
public class PageSupport {

  // --- builder pattern

  public <R> PageSupportBuilder0<R> of(
      @NonNull Function1<Pageable, Page<R>> fetcher) {
    return new PageSupportBuilder0<>(fetcher);
  }

  public <T1, R> PageSupportBuilder1<T1, R> of(
      @NonNull Function2<T1, Pageable, Page<R>> fetcher) {
    return new PageSupportBuilder1<>(fetcher);
  }

  public <T1, T2, R> PageSupportBuilder2<T1, T2, R> of(
      @NonNull Function3<T1, T2, Pageable, Page<R>> fetcher) {
    return new PageSupportBuilder2<>(fetcher);
  }

  public <T1, T2, T3, R> PageSupportBuilder3<T1, T2, T3, R> of(
      @NonNull Function4<T1, T2, T3, Pageable, Page<R>> fetcher) {
    return new PageSupportBuilder3<>(fetcher);
  }

  public <T1, T2, T3, T4, R> PageSupportBuilder4<T1, T2, T3, T4, R> of(
      @NonNull Function5<T1, T2, T3, T4, Pageable, Page<R>> fetcher) {
    return new PageSupportBuilder4<>(fetcher);
  }

  public <T1, T2, T3, T4, T5, R> PageSupportBuilder5<T1, T2, T3, T4, T5, R> of(
      @NonNull Function6<T1, T2, T3, T4, T5, Pageable, Page<R>> fetcher) {
    return new PageSupportBuilder5<>(fetcher);
  }

  public <T1, T2, T3, T4, T5, T6, R> PageSupportBuilder6<T1, T2, T3, T4, T5, T6, R> of(
      @NonNull Function7<T1, T2, T3, T4, T5, T6, Pageable, Page<R>> fetcher) {
    return new PageSupportBuilder6<>(fetcher);
  }

  public <T1, T2, T3, T4, T5, T6, T7, R> PageSupportBuilder7<T1, T2, T3, T4, T5, T6, T7, R> of(
      @NonNull Function8<T1, T2, T3, T4, T5, T6, T7, Pageable, Page<R>> fetcher) {
    return new PageSupportBuilder7<>(fetcher);
  }

  public <T1, T2, T3, T4, T5, T6, T7, T8, R> PageSupportBuilder8<T1, T2, T3, T4, T5, T6, T7, T8, R> of(
      @NonNull Function9<T1, T2, T3, T4, T5, T6, T7, T8, Pageable, Page<R>> fetcher) {
    return new PageSupportBuilder8<>(fetcher);
  }

  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> PageSupportBuilder9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> of(
      @NonNull Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, Pageable, Page<R>> fetcher) {
    return new PageSupportBuilder9<>(fetcher);
  }

  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> PageSupportBuilder10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> of(
      @NonNull Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, Pageable, Page<R>> fetcher) {
    return new PageSupportBuilder10<>(fetcher);
  }

  // --- 0 argument

  public <R> Stream<List<R>> pagedStream(
      @NonNull Function1<Pageable, Page<R>> fetcher,
      @NonNull Pageable pageable) {
    return of(fetcher).args(pageable).pagedStream();
  }

  public <R> Stream<R> stream(
      @NonNull Function1<Pageable, Page<R>> fetcher,
      @NonNull Pageable pageable) {
    return of(fetcher).args(pageable).stream();
  }

  // --- 1 argument

  public <T1, R> Stream<List<R>> pagedStream(
      @NonNull Function2<T1, Pageable, Page<R>> fetcher,
      @NonNull Tuple1<T1> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).pagedStream();
  }

  public <T1, R> Stream<R> stream(
      @NonNull Function2<T1, Pageable, Page<R>> fetcher,
      @NonNull Tuple1<T1> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).stream();
  }

  public <T1, R> Stream<List<R>> pagedStream(
      @NonNull Function2<T1, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, pageable).pagedStream();
  }

  public <T1, R> Stream<R> stream(
      @NonNull Function2<T1, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, pageable).stream();
  }

  // --- 2 arguments

  public <T1, T2, R> Stream<List<R>> pagedStream(
      @NonNull Function3<T1, T2, Pageable, Page<R>> fetcher,
      @NonNull Tuple2<T1, T2> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).pagedStream();
  }

  public <T1, T2, R> Stream<R> stream(
      @NonNull Function3<T1, T2, Pageable, Page<R>> fetcher,
      @NonNull Tuple2<T1, T2> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).stream();
  }

  public <T1, T2, R> Stream<List<R>> pagedStream(
      @NonNull Function3<T1, T2, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, pageable).pagedStream();
  }

  public <T1, T2, R> Stream<R> stream(
      @NonNull Function3<T1, T2, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, pageable).stream();
  }

  // --- 3 arguments

  public <T1, T2, T3, R> Stream<List<R>> pagedStream(
      @NonNull Function4<T1, T2, T3, Pageable, Page<R>> fetcher,
      @NonNull Tuple3<T1, T2, T3> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).pagedStream();
  }

  public <T1, T2, T3, R> Stream<R> stream(
      @NonNull Function4<T1, T2, T3, Pageable, Page<R>> fetcher,
      @NonNull Tuple3<T1, T2, T3> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).stream();
  }

  public <T1, T2, T3, R> Stream<List<R>> pagedStream(
      @NonNull Function4<T1, T2, T3, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, arg3, pageable).pagedStream();
  }

  public <T1, T2, T3, R> Stream<R> stream(
      @NonNull Function4<T1, T2, T3, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, arg3, pageable).stream();
  }

  // --- 4 arguments

  public <T1, T2, T3, T4, R> Stream<List<R>> pagedStream(
      @NonNull Function5<T1, T2, T3, T4, Pageable, Page<R>> fetcher,
      @NonNull Tuple4<T1, T2, T3, T4> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).pagedStream();
  }

  public <T1, T2, T3, T4, R> Stream<R> stream(
      @NonNull Function5<T1, T2, T3, T4, Pageable, Page<R>> fetcher,
      @NonNull Tuple4<T1, T2, T3, T4> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).stream();
  }

  public <T1, T2, T3, T4, R> Stream<List<R>> pagedStream(
      @NonNull Function5<T1, T2, T3, T4, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, arg3, arg4, pageable).pagedStream();
  }

  public <T1, T2, T3, T4, R> Stream<R> stream(
      @NonNull Function5<T1, T2, T3, T4, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, arg3, arg4, pageable).stream();
  }

  // --- 5 arguments

  public <T1, T2, T3, T4, T5, R> Stream<List<R>> pagedStream(
      @NonNull Function6<T1, T2, T3, T4, T5, Pageable, Page<R>> fetcher,
      @NonNull Tuple5<T1, T2, T3, T4, T5> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).pagedStream();
  }

  public <T1, T2, T3, T4, T5, R> Stream<R> stream(
      @NonNull Function6<T1, T2, T3, T4, T5, Pageable, Page<R>> fetcher,
      @NonNull Tuple5<T1, T2, T3, T4, T5> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).stream();
  }

  public <T1, T2, T3, T4, T5, R> Stream<List<R>> pagedStream(
      @NonNull Function6<T1, T2, T3, T4, T5, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, arg3, arg4, arg5, pageable).pagedStream();
  }

  public <T1, T2, T3, T4, T5, R> Stream<R> stream(
      @NonNull Function6<T1, T2, T3, T4, T5, Pageable, Page<R>> fetcher,
      @Nullable T1 arg1,
      @Nullable T2 arg2,
      @Nullable T3 arg3,
      @Nullable T4 arg4,
      @Nullable T5 arg5,
      @NonNull Pageable pageable) {
    return of(fetcher).args(arg1, arg2, arg3, arg4, arg5, pageable).stream();
  }

  // --- 6 arguments

  public <T1, T2, T3, T4, T5, T6, R> Stream<List<R>> pagedStream(
      @NonNull Function7<T1, T2, T3, T4, T5, T6, Pageable, Page<R>> fetcher,
      @NonNull Tuple6<T1, T2, T3, T4, T5, T6> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).pagedStream();
  }

  public <T1, T2, T3, T4, T5, T6, R> Stream<R> stream(
      @NonNull Function7<T1, T2, T3, T4, T5, T6, Pageable, Page<R>> fetcher,
      @NonNull Tuple6<T1, T2, T3, T4, T5, T6> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).stream();
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
    return of(fetcher).args(arg1, arg2, arg3, arg4, arg5, arg6, pageable).pagedStream();
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
    return of(fetcher).args(arg1, arg2, arg3, arg4, arg5, arg6, pageable).stream();
  }

  // --- 7 arguments

  public <T1, T2, T3, T4, T5, T6, T7, R> Stream<List<R>> pagedStream(
      @NonNull Function8<T1, T2, T3, T4, T5, T6, T7, Pageable, Page<R>> fetcher,
      @NonNull Tuple7<T1, T2, T3, T4, T5, T6, T7> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).pagedStream();
  }

  public <T1, T2, T3, T4, T5, T6, T7, R> Stream<R> stream(
      @NonNull Function8<T1, T2, T3, T4, T5, T6, T7, Pageable, Page<R>> fetcher,
      @NonNull Tuple7<T1, T2, T3, T4, T5, T6, T7> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).stream();
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
    return of(fetcher).args(arg1, arg2, arg3, arg4, arg5, arg6, arg7, pageable).pagedStream();
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
    return of(fetcher).args(arg1, arg2, arg3, arg4, arg5, arg6, arg7, pageable).stream();
  }

  // --- 8 arguments

  public <T1, T2, T3, T4, T5, T6, T7, T8, R> Stream<List<R>> pagedStream(
      @NonNull Function9<T1, T2, T3, T4, T5, T6, T7, T8, Pageable, Page<R>> fetcher,
      @NonNull Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).pagedStream();
  }

  public <T1, T2, T3, T4, T5, T6, T7, T8, R> Stream<R> stream(
      @NonNull Function9<T1, T2, T3, T4, T5, T6, T7, T8, Pageable, Page<R>> fetcher,
      @NonNull Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).stream();
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
    return of(fetcher).args(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, pageable).pagedStream();
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
    return of(fetcher).args(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, pageable).stream();
  }

  // --- 9 arguments

  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Stream<List<R>> pagedStream(
      @NonNull Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, Pageable, Page<R>> fetcher,
      @NonNull Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).pagedStream();
  }

  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Stream<R> stream(
      @NonNull Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, Pageable, Page<R>> fetcher,
      @NonNull Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).stream();
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
    return of(fetcher).args(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, pageable)
        .pagedStream();
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
    return of(fetcher).args(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, pageable)
        .stream();
  }

  // --- 10 arguments

  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> Stream<List<R>> pagedStream(
      @NonNull Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, Pageable, Page<R>> fetcher,
      @NonNull Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).pagedStream();
  }

  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> Stream<R> stream(
      @NonNull Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, Pageable, Page<R>> fetcher,
      @NonNull Tuple10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> args,
      @NonNull Pageable pageable) {
    return of(fetcher).args(args, pageable).stream();
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
    return of(fetcher).args(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, pageable)
        .pagedStream();
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
    return of(fetcher).args(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9, arg10, pageable)
        .stream();
  }
}
