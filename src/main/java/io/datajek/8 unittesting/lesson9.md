# Lesson 9: Unit Testing for Java Context

## Overview

Instead of using the `springboot-starter-test` dependency, we'll learn how to write a unit test using the Spring test features in this lesson.

## Demonstrated Concepts

### `spring-test` and `junit` dependency

We start by replacing the `springboot-starter-test` dependency — which is automatically included in a Spring Boot project — with the `spring-test` dependency along with the `junit-jupiter-engine` dependency.

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-test</artifactId>
    <scope>test</scope>
</dependency>
```
```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <scope>test</scope>
</dependency>
```

Since the project was built using Spring Boot, removing the `springboot-starter-test` dependency leads to __compilation errors__ due to usage of the `@SpringBootTest` annotation in __src/test/java__. To remove the errors, we can comment out the `@SpringBootTest` annotation.

### `@ContextConfiguration`

To load the context using Spring, we will use `@ContextConfiguration`. This loads only part of the configuration and is thus more suitable for unit testing.

Since our configuration is in the class `MovieRecommenderSystemApplication`, we will pass that as an argument to the `@ContextConfiguration` annotation. This class is where the context is present.

After the context has been defined, we need to run it. This is done by using the `@ExtendWith` annotation with `SpringExtension.class` as its argument. `SpringExtension` provides a bridge between Spring and JUnit.

```java
//load context and run it
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=MovieRecommenderSystemApplication.class)
class RecommenderImplementationSpringTest {

}
```

The rest of the test is the same as it was in the prior lesson.

A class which _does not_ provide a __constructor__ or __setter injection__ for its dependencies can only be tested by loading the Spring context. However, if a constructor is present (as in the case of `RecommenderImplementation` class shown above), the object can be created without loading the context, and it can also be mocked ([Unit Testing in Spring lesson](https://github.com/ginny100/The-Complete-Guide-to-Spring-5-and-Spring-Boot-2-Part-8-3/blob/master/lesson7.md).)