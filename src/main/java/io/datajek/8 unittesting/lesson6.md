# Lesson 6: Mockito Annotations

## Overview

We will use annotations provided by Mockito to get rid of the redundant code and make our tests more readable.

## Demonstrated Concepts

### `@Mock`

The `@Mock` annotation is used to create a mock without calling the `Mockito.mock` method.

We can create the mocks outside the test method using the `@Mock` annotation as follows:

```java
@Mock
StudentDao studentDaoMock;
```

By using the `@Mock` annotation, we will create the mock once instead of creating it in every test.

### `@InjectMocks`

The `@InjectMocks` annotation automatically injects a mock object into the object being tested.

We can do the dependency injection outside the test method and use the `@InjectMocks` annotation as follows:

```java
@InjectMocks
StudentService studentService;
```

The tests become shorter when we remove the _create and inject_ mock steps from the test method. We can further shorten the method by making the total variable inline. For comparison, a test written with and without annotations is shown below:

- Test written without annotations
    ```java
    @Test
    void testFindTotal() {
        StudentDao studentDaoMock = mock(StudentDao.class);
        when(studentDaoMock.getMarks()).thenReturn(new int[] {15, 20, 5});
        
        StudentService studentService = new StudentService(studentDaoMock);
        int total = studentService.findTotal();
        assertEquals(40, total);
    }
    ```

- Test written with annotations
    ```java
    @Mock
    StudentDao studentDaoMock;
    
    @InjectMocks
    StudentService studentService;
 
    @Test
    void testFindTotal() {
        when(studentDaoMock.getMarks()).thenReturn(new int[] {15, 20, 5});
        assertEquals(40, studentService.findTotal());
    }
    ```

### `@ExtendWith`

When the `@Mock` annotation is used, by default, JUnit does not evaluate it. We need to use the `@ExtendWith` annotation and pass `MockitoExtension.class` as an argument to integrate Mockito with the JUnit 5 extension model. 

`MockitoExtension` enables the evaluation of the `@Mock` annotations to initialize mocks, which was previously done using the `Mockito.mock` method.

Using the Mockito extension, mocks are initialized _before_ each test method and validation is performed after each test method to check if the mock was used in the method.

```java
@ExtendWith(MockitoExtension.class)
```