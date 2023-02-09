# Lesson 11: Spring Unit Testing with Mockito

## Overview

Ideally, when writing unit tests, we should only focus on the class under test and mock the dependencies. We will now write a test for the same class using Mockito.

## Demonstrated Concepts

### `mockito-core` dependency

To test using the Mockito framework, we need the `mockito-core` dependency, which can be added to `pom.xml` as follows:

```xml
<dependency>
  <groupId>org.mockito</groupId>
  <artifactId>mockito-core</artifactId>
  <scope>test</scope>
</dependency>
```

Note that this dependency is automatically available if `springboot-starter-test` is included in the `pom.xml` file.

### Mocking an interface

Mockito can be used when we want to test the interface with different kinds of data. We will mock the interface using the `@Mock` annotation as follows:

```java
@Mock
private Filter mockFilter;
```

This `mockFilter` can be injected in the bean using the `@InjectMocks` annotation.

```java
@InjectMocks
private RecommenderImplementation recommenderImpl;
```

We need the `@ExtendWith` annotation with `MockitoExtension.class` to enable the use of the annotations mentioned above.

The test for a case when no recommendation is returned is given below:

```java
@Test
void testRecommendMovies_noRecommendationsFound() {
    when(mockFilter.getRecommendations("Finding Dory")).thenReturn(new String[] {});
    assertArrayEquals(new String[] {}, recommenderImpl.recommendMovies("Findng Dory"));
}
```

Here, we are intercepting the call to `getRecommendations` and returning a hard coded result without calling the interface method.

Using Mockito, the tests take less time as there is no need to load the application context. Whenever possible, try to _avoid_ using Spring in unit testing as it makes the turnaround time longer.

Spring also provides annotations for testing a particular layer like `@WebMvcTest` and `@DataJpaTest` that load only a part of the context and mock the rest of the dependencies.