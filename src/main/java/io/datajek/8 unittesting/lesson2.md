# Lesson 2: Writing a Unit Test

## Overview

The simple class `ArrayMethods` has methods to find the index of a given number in the array and a method to search if a number is present in the array.

```java
public class ArrayMethods {
    int findIndex(int[] array, int number) {
        //...
        return index;
    }
 
    boolean searchForNumber(int[] array, int numberToSearchFor) {  
        boolean found = false;
        //...
        return found;
    }   
}
```

JUnit framework allows us to call the `findIndex` method with an array containing three integers, say 8, 4, and 5, and look for 4 in the array. The index where 4 is found, `1` is returned.

To test the `findIndex` method, we create a unit test. The tests should always be __separate__ from the source code. They should be in a different folder so that they are not deployed to production along with the source code.

There are two ways of creating a class for testing purposes

- Manually create a class in the __src/test/java__ folder and write tests. 
- Use the IDE support.

## Demonstrated Concepts

### Creating a test class

A JUnit test can be created in the same way that a class or interface is created.

In our example, we will call the test `ArrayMethodsTest`. 

Conventionally, the name of the test is the name of the class being tested with the word `Test` appended to it. This is for convenience, so that we know which class we are testing. It also lets the developer use the package visibility features of Java, where protected methods that are not visible to classes outside the package become visible to the test class.

#### `@Test` annotation

The `@Test` annotation is present _before_ the test method. It signifies that the method is a unit test. When JUnit encounters this annotation, it knows it has to run the test. So if we click on the project and choose __Run as Junit test__, JUnit will know which methods it has to run.

#### `fail` method

By default, the test method contains the `fail` method. If the test is run, it will fail because of the `fail` method and the text message `Not implemented yet` will be displayed.

```java
@Test
public void test() {
    fail("Not implemented yet");
}
```

If the test is run after removing the `fail` method, it will succeed. JUnit works by checking if any of the tests have failed. The test will be a success unless any checks fail. In the JUnit world, absence of failure is success. 

In IDEs, a colored bar is the visual representation of the status of the execution. A green bar shows up in case a test succeeds, and a red one indicates a failing test.

> __Note from `ginny100`:__ When I tried creating the `ArrayMethodsTest` test class with the help of IntelliJ IDE (press ⌥ ⏎ , and select __Create Test__), I noticed that the `fail` method was not created by default. I'm not sure about other Java IDE though.

### Steps in writing a unit test

Every unit test involves a few simple steps.

- Step 1: Create an object of the class under test and use the object to call the method that we want to test. 
- Step 2: Set up the inputs which constitute a test scenario, execute the method, and get results. 
- Step 3: Compare the results to the expected value.

For example, we want to test the `findIndex` method of the `ArrayMethods` class:

```java
@Test
public void testFindIndex_numberInArray() {
    //1. create object of the class
    ArrayMethods arrayMethods = new ArrayMethods();
 
    //2. call method
    int result = arrayMethods.findIndex(new int[] {8,4,5}, 4);

    //3. compare the actual results to the expected
    assertEquals(1, result);
}
```

### Assertions

JUnit provides us with a number of __assertions__, which are methods that test whether the expectation and reality are the same. JUnit’s way of reporting the status of the test is by using one of the assert methods.

The JUnit assert method `assertEquals` can be used to verify if the result of the test is equal to the expected value.

```java
assertEquals(expected, actual);
```

The test starts failing if the _actual_ value does not match the _expected_ value and we will get an assertion error: `Expected <expected value> but was <actual value>`. 

An advantage of continuously running the tests is that we can get notified of any bugs that have been introduced in our code and fix them right away. 

### Creating multiple test cases

Following the same simple steps, we can test other test cases to check when a value is not in the array and when an empty input array is sent to the method.

```java
@Test
public void testfindIndex_numberNotInArray() {
    ArrayMethods arrayMethods = new ArrayMethods();
    assertEquals(-1, arrayMethods.findIndex(new int[]{8,4,5}, 1));
}
```

```java
@Test
public void testfindIndex_emptyArray() {
    ArrayMethods arrayMethods = new ArrayMethods();
    assertEquals(-1, arrayMethods.findIndex(new int[]{}, 1));
}
```

JUnit will run all methods marked with the `@Test` annotation. There is no particular order in which the tests run. JUnit creates a new test instance every time a test runs.