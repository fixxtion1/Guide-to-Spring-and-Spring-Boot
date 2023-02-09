# Lesson 10: Unit Testing for XML Context

## Overview

The `@ContextConfiguration` annotation is used to load Java as well as XML context. 

The `appContext.xml` file in __src/main/resources__ is created for the `MovieRecommenderSystemApplication` class.

In this file, we mention the package for component scanning using the `<context:component-scan>` tag. We have also defined three beans: `filter`, `filter2`, and `recommenderImpl`.

## Demonstrated Concepts

### `@ContextConfiguration` with `locations`

When using Java context, the `@ContextConfiguration` annotation takes `classes` as an argument. 

To load the XML configuration, we provide `locations` as an argument. Since the config file is in the class path, the location can be given as follows:

```java
@ContextConfiguration(locations="/appContext.xml")
```

The rest of the test will remain the same as shown in the previous lesson.

### Creating a test configuration

Since we are reading the application context from a file, we can create a separate context for the purpose of testing. All test contexts should be placed in __src/test/resources__.

A test context is useful if we want to override something defined in the context, for the purpose of testing.

An XML file `testContext.xml` in __src/test/resources__ is our test context. We can define a test context here or import the context from another file and then over-ride it.

```xml
<import resource="classpath:appContext.xml"/>
```

After importing the context, the beans can be defined for the purpose of testing and any bean from the original context can be over-ridden.

To use the test configuration defined above, we use `testContext.xml` as an argument to the `@ContextConfiguration` annotation.

```java
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="/testContext.xml")
class RecommenderImplementationXmlConfigTest {
    //...
}
```

In this way, we can define a separate configuration for the unit test.