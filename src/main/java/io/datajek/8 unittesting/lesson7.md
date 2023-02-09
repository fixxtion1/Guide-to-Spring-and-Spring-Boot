# Lesson 7: Unit Testing in Spring

## Overview

When writing tests, the foremost thing to remember is that __the test code should always be separate from the production code__. We will write all our tests in __src/test/java__. This will ensure that the test code is never part of the deployable __jar__ or __war__ and stays in our repository.

## Demonstrated Concepts

### Creating a test file

We begin by creating a JUnit test case in __src/test/java__. 

To create a unit test for the `RecommenderImplementation` class, we need to match the same package structure in the test file. The test is called `RecommenderImplementationUnitTest`. 

Since we want to test the `recommendMovies` method, we will rename the test method to `testRecommendMovies`.

Then, we follow the 3 [steps in writing a unit test](https://github.com/ginny100/The-Complete-Guide-to-Spring-5-and-Spring-Boot-2-Part-8-1/blob/master/lesson2.md#steps-in-writing-a-unit-test:~:text=Steps%20in%20writing%20a%20unit%20test)

```java
class RecommenderImplementationUnitTest {

    @Test
    void testRecommendMovies() {
        //1. Initialize the object
        RecommenderImplementation recommenderImpl = null;
        //2. Call method on the bean
        String[] actualResult = recommenderImpl.recommendMovies("Finding Dory");
        //3. Check if the result is as expected
    }
}
```

### Initializing the bean

We use __constructor injection__ in the test method to initialize the `RecommenderImplementation` bean.

```java
@Test
void testRecommendMovies_withCollaborativeFilter() {
    //1. Initialize the object
    RecommenderImplementation recommenderImpl = new RecommenderImplementation(new CollaborativeFilter());
    //2. Call method on the bean
    String[] actualResult = recommenderImpl.recommendMovies("Finding Dory");
    //3. Check if the result is as expected
}
```

Having a constructor in the class enables us to initialize the dependency without having to load the Spring Boot application context. When writing code, __field injection__ should be _avoided_ because then the bean can only be initialized by loading the context.

### Using `assertArrayEquals` to check the output

Inside the test method, we  call the `recommendMovies` method with an input string. The input doesn’t really matter because we have hardcoded the return values. 

Since our method returns an array, we use the `assertArrayEquals` method to compare the expected values with actual values.

```java
@Test
void testRecommendMovies_withCollaborativeFilter() {
    //1. Initialize the object
    RecommenderImplementation recommenderImpl = new RecommenderImplementation(new CollaborativeFilter());
    //2. Call method on the bean
    String[] actualResult = recommenderImpl.recommendMovies("Finding Dory");
    //3. Check if the result is as expected
    assertArrayEquals(new String[] {"Finding Nemo", "Ice Age", "Toy Story"}, actualResult);
}
```

Another unit test to check the output of the `recommendMovies` method when `ContentBasedFilter` is used:

```java
@Test
void testRecommendMovies_withContentBasedFilter() {
    //1. Initialize the object
    RecommenderImplementation recommenderImpl = new RecommenderImplementation(new ContentBasedFilter());
    //2+3. Call method on the bean & Check if the result is as expected
    assertArrayEquals(new String[] {"Happy Feet", "Ice Age", "Shark Tale"}, recommenderImpl.recommendMovies("Finding Dory"));
}
```