# Lesson 5: Stubbing and Mocking

## Overview

In unit testing, we should be able to test every application layer _independently_.

We use __stubbing__ and __mocking__ to test a part of our application by mocking an external service or an application layer. There are different mocking frameworks available like `Mockito`, `JMock`, and `EasyMock`.

If our application connects to a cloud service, we can mock the service instead of actually connecting to it every time.

## Demonstrated Concepts

### Creating a stub

To test the `findTotal` method of the `StudentService` class, we create a stub class that extends the `StudentDao` class. 

We can write an implementation of the `getMarks` method in the `StudentDao` class and return some dummy data back so that the `findTotal` method can process that data.

```java
class StudentDaoStub extends StudentDao{
    @Override
    public int[] getMarks() {
        return new int[] {15, 20, 5};
    }
}
```

Since `StudentDao` is a dependency of the `StudentService` class, we will pass the stub class to it. For this, we need a constructor in the `StudentService` class to initialize `StudentDao`.

```java
public StudentService(StudentDao studentDao) {
    super();
    this.studentDao = studentDao;
}
```

Now we can pass the stub class as a constructor argument when creating the `StudentService` object inside our test method. 

Since the result returned by the stub is already known, we can now check if the expected and actual values are the same using the `assertEquals` method.

```java
@Test
void testFindTotal() {
    StudentService studentService = new StudentService(new StudentDaoStub());
    int total = studentService.findTotal();
    assertEquals(40, total);
}
```

Using stubs, we can _imitate_ the `StudentDao` class and test our method. In the same way, stubs can also be created for an __interface__. 

This approach has some disadvantages. 

1. To make other test cases, we need to create another stub or another implementation of the `StudentDao` class. Creating multiple versions of the same class is cumbersome. 
2. If the stub is an implementation of an interface and new methods are added to the interface, we will need to update the stub and provide an implementation for the unimplemented methods.

These problems are tackled by mocks.

### Mockito dependency

If you create your project using Spring Initializr, JUnit and Mockito are automatically added as dependencies in the Maven dependencies folder and are available under the scope of test.

If this is not the case, the mockito dependency can be added as follows:

```java
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>2.10.0</version>
    <scope>test</scope>
</dependency>
```

> __Note from `ginny100`:__ This project is a Maven project created with Spring Initializr so JUnit and Mockito are already available.

### Creating a mock

To create a test for the `findTotal` method using Mockito, we first create a new JUnit test called `StudentServiceMockTest`.

The `mock` method of `org.mockito.Mockito` creates a mock of a class if you pass it as an argument.

```java
StudentDao studentDaoMock = mock(StudentDao.class);
```

Two methods used on the mock are `when` and `thenReturn`, which help create a test scenario. When a method is called on `studentDaoMock`, return an array of numbers as follows:

```java
when(studentDaoMock.getMarks()).thenReturn(new int[] {15, 20, 5});
```

Now we can pass the mock as a constructor argument to the `StudentService` object.

```java
StudentService studentService = new StudentService(studentDaoMock);
```

The complete test method is shown below:

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

A mock can easily be _repeated_ for other test case scenarios as well. We can mock the data just by using the `when` and `thenReturn` methods.

Here is another test when an empty array is returned by the `studentDaoMock`:

```java
@Test
void testFindTotal_EmptyArray() {
    StudentDao studentDaoMock = mock(StudentDao.class);
    when(studentDaoMock.getMarks()).thenReturn(new int[] {});
        
    StudentService studentService = new StudentService(studentDaoMock);
    int total = studentService.findTotal();
    assertEquals(0, total);
}
```

In this manner, we can mock the class and create tests for other scenarios as well. Here, we have used constructor injection for injecting `studentDaoMock` in `StudentService`. Setter injection can also be used. We created a mock for a class but mocks can also be created for interfaces.