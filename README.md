# pagination-stream

Pagination stream API

```xml
<dependency>
  <groupId>tw.com.softleader.data</groupId>
  <artifactId>pagination-stream</artifactId>
  <version>${pagination-stream.version}</version>
</dependency>
```

> 你可以在 [Release Page](https://github.com/softleader/pagination-stream/releases) 找到最新的版號

## Usage

首先定義要呼叫的 method, 必須符合:

1. Input 必須為任意 1 ~ 10 個 args 及最後為一個 [`Pageable`](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Pageable.html) 物件的組合
2. Return Type 必須為 [`Page<T>`](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Page.html), 例如:

```java
Page<MyObject> someMethod(int a, long b, String c, Pageable pageable) {
  ...
}  
```

接著透過 `PageSupport#pagedStream` 建立 `Stream<List<T>>` 物件, Stream 中的 List 就是每個分頁的內容, 範例如下:

```java
PageSupport
  .pagedStream(execute::someMethod, 1, 2L, "3", Pageable.ofSize(10))
  .forEach(page -> { // page will be List<T>
    // do something to every page
  })
```

如果你不是很在乎有多少頁, 只想 Streaming 的處理每頁中的資料, 你可以使用 `PageSupport#stream` 建立 `Stream<T>`, 範例如下:

```java    
PageSupport
  .stream(execute::someMethod, 1, 2L, "3", Pageable.ofSize(10))
  .forEach(data -> { // data will be MyObject
    // do something to every single data
  })
```

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
  ... // next step

// Bad
PageSupport.stream(fetch::data, pageable)
  .collect(...) // BOOM!
```
