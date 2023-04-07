[![version](https://img.shields.io/github/v/release/softleader/pagination-stream?color=brightgreen&sort=semver)](https://github.com/softleader/pagination-stream/releases)

# pagination-stream

Pagination stream API

```xml
<dependency>
  <groupId>tw.com.softleader.data</groupId>
  <artifactId>pagination-stream</artifactId>
  <version>last-release-version</version>
</dependency>
```

> 你可以在 [Release Page](https://github.com/softleader/pagination-stream/releases/latest) 找到最新的版號

## Usage

首先定義要呼叫的 method, 必須符合:

1. Input 必須為任意 0 ~ 10 個 args 及最後為一個 [`Pageable`](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Pageable.html) 物件的組合
2. Return Type 必須為 [`Page<T>`](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Page.html), 例如:

```java
Page<MyData> data(int a, long b, String c, Pageable pageable) {
  ...
}  
```

接著透過 `PageSupport#pagedStream` 建立 `Stream<List<T>>` 物件, Stream 中的 List 就是每個分頁的內容, 範例如下:

```java
PageSupport
  .pagedStream(fetch::data, 1, 2L, "3", Pageable.ofSize(10))
  .forEach(page -> { // page will be List<T>
    // do something to every page
  })
```

如果你不是很在乎有多少頁, 只想 Streaming 的處理每頁中的資料, 你可以使用 `PageSupport#stream` 建立 `Stream<T>`, 範例如下:

```java    
PageSupport
  .stream(fetch::data, 1, 2L, "3", Pageable.ofSize(10))
  .forEach(data -> { // data will be MyData
    // do something to every single data
  })
```

### Builder Pattern

`PageSupport` 也有提供 Builder 模式的建構方式, 你可以透過 `PageSupport#of` 開始一個 Builder 的建構:

```java
PageSupport
  .of(fetch::data)
  .args(1, 2L, "3", Pageable.ofSize(10))
  .stream()
  . ...
```

使用 Builder 模式就可以事先定義 *Page Stream* 的建構方式, 且重複的開出多個 *stream* 物件, 如:

```java
var fetcher = PageSupport.of(fetch::data);

fetcher.args(1, 2L, "3", Pageable.ofSize(10))
  .stream()
  . ...
  
fetcher.args(10, 11L, "12", Pageable.ofSize(10))
  .pagedStream()
  . ...
```

### Parallel

`PageSupport` 也支援 Parallel Stream 的情境, 讓不需要分頁順序的情境中, 可以有更佳效能表現的可能性, 例如:

```java
PageSupport
  .stream(fetch::data, 1, 2L, "3", Pageable.ofSize(10))
  .parallel()
  ...
```

 在 Parallel 情境中,  會先同步 (Sequential) 的取回第一次資料 (*P1*) (含全部頁數) 作為拆分基礎, 假設 *P1* 取回的資料顯示共有 4 個分頁（*P1, P2, P3, P4*）, 之後的 3 個分頁會被拆分成多個 Spliterator (*S1, S2, S3*）。每個拆分的 Spliterator 都會是一個子任務可以被獨立地執行。

```
+-----+-----+-----+-----+
 |  P1 |  P2 |  P3 |  P4 |
 +-----+-----+-----+-----+
           |     |     |
           |     |     |
           v     v     v
        +-----+-----+-----+
        |  S1 |  S2 |  S3 |
        +-----+-----+-----+
```

總結以上, Parallel 的重點摘錄如下:

1. 會先同步 (Sequential) 的取回第一次分頁資料, 以作為拆分子任務的基礎
2. Parallel 的子任務, 處理的最小單位是每一個分頁 (Page)

## Example

### Page Form Repository

假設我有個複雜的運算, 資料來自於資料庫, 程式碼範例如下:

```java
interface PersonRepository extends JpaRepository<Person, Long> {
 
  Page<Person> findAll(Specification<Person> spec, Pageable pageable);
}

class DifficultCalculationService {
  
  PersonRepository repository;
  
  long calculate(Specification<Person> spec, Pageable pageable) {
    return PageSupport.stream(repository::findAll, spec, pageable)
      .mapToLong(person -> {
        ...
      })
      .sum();
  }
}
```

### Page Form Remote API

承 [Page Form Repository](#page-form-repository), 假設資料來自於一個遠端的 API 呼叫, 且使用 [OpenFeign](https://spring.io/projects/spring-cloud-openfeign) 來執行, 則程式碼範例如下:

```java
@FeignClient(name = "person-api", url = "http://somewhere/out/there")
interface PersonClient {
 
  @GetMapping("/people")
  Page<Person> findAll(Personcriteria criteria, Pageable pageable);
}

class DifficultCalculationService {
  
  PersonClient client;
  
  long calculate(Personcriteria criteria, Pageable pageable) {
    return PageSupport.stream(client::findAll, criteria, pageable)
      .mapToLong(person -> {
        ...
      })
      .sum();
  }
}
```

## Caution

**無論如何都強烈建議要 Streaming 的處理每個分頁中的每筆資料, 以避免記憶體 OOM!**

當你需要在建立 *Page Stream* 後馬上使用到  `.collect(Collectors.toList())` 等收集的 stream api 時, 這就是一個警訊了, 一定要再思考是否有更好的方式處理!

```java
// Good
PageSupport.stream(fetch::data, pageable)
  .map(/* 處理每頁中的每筆資料 */)
  . .... // next step

// Bad
PageSupport.stream(fetch::data, pageable)
  .collect(...) // BOOM!
```
