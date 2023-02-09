/**
 * Tests written without Mockito Annotations
 */

package io.datajek.unittesting2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentServiceMockTest {

    @Test
    void testFindTotal() {
        StudentDao studentDaoMock = mock(StudentDao.class);
        when(studentDaoMock.getMarks()).thenReturn(new int[] {15, 20, 5});

        StudentService studentService = new StudentService(studentDaoMock);
        int total = studentService.findTotal();
        assertEquals(40, total);
    }

    @Test
    void testFindTotal_EmptyArray() {
        StudentDao studentDaoMock = mock(StudentDao.class);
        when(studentDaoMock.getMarks()).thenReturn(new int[] {});

        StudentService studentService = new StudentService(studentDaoMock);
        int total = studentService.findTotal();
        assertEquals(0, total);
    }
}