package telran.recursion.test;

import static org.junit.jupiter.api.Assertions.*;
import telran.recursion.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


class LineRecursionTest {

	@Test
	@Disabled
	void test() {
		f(6);
	}

	private void f(int a) {
		if (a > 0) {
			System.out.println(a);
			f(a - 1);
		}
		
	}
	@Test
	void factorialTest ()
	{
		assertEquals(6, LineRecursion.factorial(3));
		assertEquals(24, LineRecursion.factorial(4));
		assertEquals(1, LineRecursion.factorial(0));
		
	}
	@Test
	void powTest () {
		assertEquals(100, LineRecursion.pow(10, 2));
		assertEquals(100, LineRecursion.pow(-10, 2));
		assertEquals(1000, LineRecursion.pow(10, 3));
		assertEquals(-1000, LineRecursion.pow(-10, 3));
	}
	@Test
	void sumTest() {
		assertEquals(21, LineRecursion.sum(new int[] {1 , 2, 3, 4, 5, 6}));
	}
	@Test
	void reverseTest() {
		int array[] = {1 , 2, 3, 4, 5, 6};
		int array1[] = {1 , 2, 3, 4, 5, 6, 7};
		int expected[] = {6, 5, 4, 3, 2, 1};
		int expected1[] = {7, 6, 5, 4, 3, 2, 1};
		assertArrayEquals(expected, LineRecursion.reverse(array));
		assertArrayEquals(expected1, LineRecursion.reverse(array1));
	}
	void squareTest() {
	    assertEquals(0,LineRecursion.square(0));
	    assertEquals(25,LineRecursion.square(5));
	    assertEquals(1,LineRecursion.square(-1));
	    assertEquals(16,LineRecursion.square(-4));
	}
	void isSubstringTest() {
	    assertTrue(LineRecursion.isSubstring("Good morning", "Good"));
	    assertFalse(LineRecursion.isSubstring("Good morning", "foo"));
	}
}