[![](https://img.shields.io/badge/docs-中文版-blue?style=for-the-badge)](./README.zh-tw.md)
[![version](https://img.shields.io/github/v/release/softleader/pagination-stream?style=for-the-badge&color=brightgreen&sort=semver)](https://github.com/softleader/pagination-stream/releases)
[![Maven Central](https://img.shields.io/maven-central/v/tw.com.softleader.data/pagination-stream?style=for-the-badge&color=orange)](https://central.sonatype.com/artifact/tw.com.softleader.data/pagination-stream)

# pagination-stream

Pagination stream API

```xml
<dependency>
  <groupId>tw.com.softleader.data</groupId>
  <artifactId>pagination-stream</artifactId>
  <version>last-release-version</version>
</dependency>
```

> You can find the latest version on the [Release Page](https://github.com/softleader/pagination-stream/releases/latest).

Java Module:

```java
requires pagination.stream;
requires spring.data.commons;
```

## Usage

First, define the method to be called. It must meet the following criteria:

1. Input must be any combination of 0 to 10 args, ending with a [`Pageable`](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Pageable.html) object.
2. Return type must be a [`Page<T>`](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/domain/Page.html), for example:

```java
Page<MyData> data(int a, long b, String c, Pageable pageable) {
  ...
}  
```

Then, create a `Stream<List<T>>` object using `PageSupport#pagedStream`. Each list in the stream represents the content of a page. For example:

```java
PageSupport
  .pagedStream(fetch::data, 1, 2L, "3", Pageable.ofSize(10))
  .forEach(page -> { // page will be List<T>
    // do something to every page
  })
```

If you don't care about the number of pages and just want to stream the data from each page, you can create a `Stream<T>` using `PageSupport#stream`. For example:

```java    
PageSupport
  .stream(fetch::data, 1, 2L, "3", Pageable.ofSize(10))
  .forEach(data -> { // data will be MyData
    // do something to every single data
  })
```

The `fixedStream` or `fixedPagedStream` methods are intended for scenarios using a fixed `Pageable` as the paging condition. Each iteration fetches the currently available matching data from the source and processes it, repeating this process until no more matching data is found. 

```
 +-----+-----+-----+-----+
 |  P1 |  P1 |  P1 |  .. | (no more data) => End
 +-----+-----+-----+-----+
```

Example:

```java
var maxAttempts = 100; // Maximum number of attempts, as a safeguard against infinite loops
PageSupport
  .fixedStream(fetch::data, 1, 2L, "3", Pageable.ofSize(10), maxAttempts)
  .forEach(data -> { // data will be MyData
    // Each iteration queries using the originally provided Pageable
    // It is expected that each round of processing modifies the data source state
    // Thus, the amount of matching data should gradually decrease
    // It stops when no matching data remains or the max attempt limit is reached
  });
```

> If the maximum number of attempts is reached, an `AttemptExhaustedException` will be thrown. It’s recommended to catch this exception and handle it accordingly.

### Builder Pattern

`PageSupport` also provides a builder pattern. You can start building with `PageSupport#of`:

```java
PageSupport
  .of(fetch::data)
  .args(1, 2L, "3", Pageable.ofSize(10))
  .stream()
  . ...
```

Using the builder pattern allows you to define the page stream configuration in advance and create multiple stream objects. For example:

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

`PageSupport` also supports parallel streams, providing better performance in scenarios where page order is not important. For example:

```java
PageSupport
  .stream(fetch::data, 1, 2L, "3", Pageable.ofSize(10))
  .parallel()
  ...
```

In parallel scenarios, the first page of data (*P1*) is fetched sequentially as a basis for splitting. Assuming *P1* shows that there are four pages (*P1, P2, P3, P4*), the remaining three pages will be split into multiple [Spliterators](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Spliterator.html) (*S1, S2, S3*). Each spliterator is an independent subtask.

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

In summary, the key points of parallel processing are:

1. The first page is fetched sequentially to serve as the basis for splitting spliterators (subtasks).
2. The minimum unit of processing for a subtask is each page.
3. Each subtask may not necessarily run on an independent thread, but will be handled by Java's [parallelism mechanisms](https://docs.oracle.com/javase/tutorial/collections/streams/parallelism.html).

> APIs starting with `fixed*` do not support parallel processing.

#### Performance Impact

Please note, using parallel streams does not always guarantee better performance. Factors such as the cost of thread creation and management can sometimes result in worse performance. Be sure to evaluate the specifics of your scenario before using parallel streams.

Before using, read more on the topic:

- [When to Use a Parallel Stream in Java](https://www.baeldung.com/java-when-to-use-parallel-stream)
- [Parallel streams in Java: Benchmarking and performance considerations](https://blogs.oracle.com/javamagazine/post/java-parallel-streams-performance-benchmark)
- [Seasoned Java Developers Should Know These 5 Practices to Improve Parallel Stream Execution](https://blog.devgenius.io/seasoned-java-developers-should-know-these-5-practices-to-improve-parallel-stream-execution-602cc50c9aca)

## Example

### Page from Repository

Suppose you have a complex computation, and the data comes from a database. The code example is as follows:

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

### Page from Remote API

Suppose the data comes from a remote API call using [OpenFeign](https://spring.io/projects/spring-cloud-openfeign), the code example is as follows:

```java
@FeignClient(name = "person-api", url = "http://somewhere/out/there")
interface PersonClient {
 
  @GetMapping("/people")
  Page<Person> findAll(PersonCriteria criteria, Pageable pageable);
}

class DifficultCalculationService {
  
  PersonClient client;
  
  long calculate(PersonCriteria criteria, Pageable pageable) {
    return PageSupport.stream(client::findAll, criteria, pageable)
      .mapToLong(person -> {
        ...
      })
      .sum();
  }
}
```

## Caution

**When dealing with situations where the number of records cannot be controlled, it is recommended to use streaming logic as much as possible to avoid impacting your app, such as memory OOM!**

If you need to use `.collect(Collectors.toList())` immediately after creating the *Page Stream*, this is a warning sign that you need to reconsider if there is a better way to handle it!

```java
// Good
PageSupport.stream(fetch::data, pageable)
  .map(/* process each data in each page */)
  . .... // next step

// Bad
PageSupport.stream(fetch::data, pageable)
  .collect(...) // BOOM!
```
