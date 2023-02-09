# Lesson 3: Assert Methods

## Overview

A number of assert methods are available with the import of the `Assertions.*` package.

## Demonstrated Concepts

### `assertEquals`

The `assertEquals` method compares the _expected_ value and the _actual_ value to determine whether both are the same.

Example:

```java
@Test
public void testfindIndex_numberNotInArray() {
    ArrayMethods arrayMethods = new ArrayMethods();
    assertEquals(-1, arrayMethods.findIndex(new int[] {8, 4, 5}, 1));
}
```

An optional third String argument can be passed to the `assertEquals` method to show a message about what the test is for. 

Example:

```java
assertEquals(1, arrayMethods.findIndex(new int[] {8, 4, 5}, 1), "The findIndex method finds the index of a given number");
```

### `assertArrayEquals`

The `assertArrayEquals` method takes two arrays and compares them element by element and finds out if both are the same or not.

Example:

```java
assertArrayEquals(new int[] {0, 1, 3, 7}, arrayMethods.sortArray(new int[] {3, 1, 7, 0});
```

### `assertTrue` and `assertFalse`

The `assertTrue` method takes a condition as argument and checks if it is __true__. In the same manner, `assertFalse` checks for a condition to be __false__. 

Also, the same result can easily be achieved through the `assertEquals` method as well. 

Example:

```java
@Test
void testAssert() {
    Boolean condition = true;
    assertEquals(true,true);
    assertTrue(condition);
}
```

### `assertNull` and `assertNotNull`

`assertNull` and `assertNotNull` are used to check for __null__ values.

Again, the same result can be achieved using `assertEquals` and passing __null__ as the expected value.

Example:

```java
@Test
void testAssert() {
    String str = null;
    assertEquals(null, str);
    assertNull(str);
}
```

### `fail`

The `fail` method fails the test, _no matter what_. It can be used to mark an unfinished test, so that the developer is notified that it needs to be completed.

```java
@Test
public void testSortArray() {
    fail("unimplemented method");
}
```

### `assertThrows`

Sometimes we may want to assert __exceptions__. The `assertThrows` method is used to check if an exception is thrown. It takes the executable, which causes the exception along with the exception that we are expecting.

For example, we add a method printArray to our class as follows:

```java
void printArray(int[] array, int index) {       
    System.out.println(array[index]);
}
```

We can test if the `ArrayIndexOutOfBound` exception is thrown by writing the following test:

```java
@Test
public void testfindIndex_indexOutOfBound() {
    ArrayMethods arrayMethods = new ArrayMethods();
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> arrayMethods.printArray(new int[] {1, 8, 5}, 3));
}
```

The test contains the type of the exception that we are expecting (`ArrayIndexOutOfBoundsException.class`) and a lambda expression that will evaluate to the exception.

### `assertAll`

We can combine several simple tests that just assert some basic stuff in one method rather than having a separate method for each test case. To achieve this, we can use the `assertAll` method to combine all the assertions using lambda expressions

Example:

```java
@Test
public void testfindIndex() {
    ArrayMethods arrayMethods = new ArrayMethods();
    assertAll(
        () -> assertEquals(1, arrayMethods.findIndex(new int[] {8, 4, 5}, 4)),
        () -> assertEquals(-1, arrayMethods.findIndex(new int[] {8, 4, 5}, 1)),
        () -> assertEquals(-1, arrayMethods.findIndex(new int[] {}, 1))
    );
}
```