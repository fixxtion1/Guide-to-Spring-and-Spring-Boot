package io.datajek.unittesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ArrayMethodsTest {

    private ArrayMethods arrayMethods;

    @Test
    public void testFindIndex_numberInArray() {
        System.out.println("Test1");
        //1. Create object of the class
        ArrayMethods arrayMethods = new ArrayMethods();
        //2. Call method
        int result = arrayMethods.findIndex(new int[] {8, 4, 5}, 4);
        //3. Compare the actual results to the expected
        assertEquals(1, result);
    }

    //This test will fail. The error message will gve an insight into what the test was supposed to do.
    @Test
    public void testFindIndex_numberNotInArray() {
        System.out.println("Test2");
        //1. Create object of the class
        ArrayMethods arrayMethods = new ArrayMethods();
        //2 + 3. Call method and Compare the actual results to the expected
        assertEquals(1, arrayMethods.findIndex(new int[] {8, 4, 5}, 1),"The findIndex method finds the index of a given number");
    }

    @Test
    public void testFindIndex_emptyArray() {
        System.out.println("Test3");
        //1. Create object of the class
        ArrayMethods arrayMethods = new ArrayMethods();
        //2 + 3. Call method and Compare the actual results to the expected
        assertEquals(-1, arrayMethods.findIndex(new int[] {}, 1));
    }

    @Test
    @Disabled
    public void testSortArray() {
        System.out.println("Test4");
        fail("unimplemented method");
    }

    @BeforeEach
    void init() {
        //Initialize the object here
        System.out.println("Initializing before test");
        arrayMethods = new ArrayMethods();
    }

    @AfterEach
    void afterEachTest() {
        System.out.println("Clean up after test");
    }

    @BeforeAll
    static void beforeAllTests() {
        System.out.println("Run this code before all tests");
    }

    @AfterAll
    static void afterAllTests() {
        System.out.println("Run this code after all tests");
    }

    @Test
    public void testAssert() {
        System.out.println("Test5");
        Boolean condition = true;
        assertEquals(true,true);
        assertTrue(condition);

        String str = null;
        assertEquals(null, str);
        assertNull(str);
    }

    @Test
    public void testFindIndex_indexOutOfBound() {
        System.out.println("Test6");
        ArrayMethods arrayMethods = new ArrayMethods();
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> arrayMethods.printArray(new int[] {1, 8, 5}, 3));
    }

    @Test
    public void testFindIndex() {
        System.out.println("Test7");
        ArrayMethods arrayMethods = new ArrayMethods();
        assertAll(
                () -> assertEquals(1, arrayMethods.findIndex(new int[] {8, 4, 5}, 4)),
                () -> assertEquals(-1, arrayMethods.findIndex(new int[] {8, 4, 5}, 1)),
                () -> assertEquals(-1, arrayMethods.findIndex(new int[] {}, 1))
        );
    }
}