# Lesson 8: Unit Testing Using Spring Boot

## Overview

When we create an application using Spring Boot, we get the `springboot-starter-test` dependency that comes with `Mockito` and `AssertJ` as testing libraries. We also automatically get a test file with the `@SpringBootTest` annotation.

Unit testing should not be done using Spring Boot. The main purpose of unit testing is to test a method or class. However, `@SpringBootTest` loads the entire context, which makes the test lengthy and _defeats_ the purpose of unit testing. This feature of Spring Boot should be used in __integration testing__ where we test across multiple layers.

Let's learn how a unit test can be written using the `@SpringBootTest` annotation.

## Demonstrated Concepts

### `spring-boot-starter-test` dependency

The `pom.xml` file of a Spring Boot project has the following dependency on `spring-boot-starter-test`:

```xml
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-test</artifactId>
   <scope>test</scope>
</dependency>
```

The JUnit test named `RecommenderImplementationSpringBootTest` in __src/test/java__ is for testing the `RecommenderImplementation` class.

```java
package io.datajek.spring.basics.movierecommendersystem.lesson4;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//1. Launch context
@SpringBootTest
class RecommenderImplementationSpringBootTest {

    //2. Load bean from context
    private RecommenderImplementation recommenderImpl;

    @Test
    void testRecommendMovies() {
        //3. Call method on the bean
        //4. Check if the result is as expected
    }
}
```

### Launching application context and autowiring bean

`@SpringBootTest` launches the entire application context, which means that _all_ the beans get loaded. Thus, we can simply use the `@Autowired` annotation to get the `RecommenderImplementation` bean.

```java
@Autowired
private RecommenderImplementation recommenderImpl;
```

The `@Autowired` will automatically find and inject the dependency rather than us having to manually do it.

Inside the test method, we call the `recommendMovies` method with an input string and then use the `assertArrayEquals` method to compare the expected and actual values.

```java
@Test
void testRecommendMovies() {
    assertArrayEquals(new String[] {"Finding Nemo", "Ice Age", "Toy Story"}, recommenderImpl.recommendMovies("Finding Dory"));
}
```

### When to use `@SpringBootTest`

The value of `@SpringBootTest` is in __testing the whole application__ because it loads the _complete context_ like in the production environment. 

For a unit test, there is no need to load the complete context. In fact, loading the entire context will have an impact on performance since unit tests should only take a few milliseconds to run.