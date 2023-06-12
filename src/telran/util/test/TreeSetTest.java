package telran.util.test;

import telran.util.TreeSet;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.ArrayList;
import telran.util.List;
import telran.util.Set;

public class TreeSetTest extends SortedSetTest {
TreeSet<Integer> treeSet;
@BeforeEach
@Override
void setUp() {
	super.setUp();
	treeSet = (TreeSet<Integer>) set;
}
	@Override
	protected <T> Set<T> getSet() {
		
		return new TreeSet<>();
	}
//	@Override
//	@Test
//	void clearPerformance() {
//		
//	}
	@Test
	void displayTree() {
		treeSet.setInitialLevel(5);
		treeSet.setSpacesPerLevel(3);
		treeSet.displayRotated();
	}
	@Test
	void widthTest() {
		assertEquals(3, treeSet.width());
	}
	@Test
	void heightTest() {
		assertEquals(3, treeSet.height());
	}
	@Test
	void balanceTest() {
		TreeSet<Integer> treeBalanced = new TreeSet<>();
		int [] array = getRandomArray(255);
		fillCollection(treeBalanced, array);
		treeBalanced.balance();
		assertEquals(8, treeBalanced.height());
		assertEquals(128, treeBalanced.width());
	}
	@Test
	void balanceTestFromSorted() {
		int height = 23;
		int nNumbers =  1 << height;
		int [] array = new int[nNumbers - 1];
		for(int i = 0; i < array.length; i++) {
			array[i] = i;
		}
		TreeSet<Integer> treeBalanced = new TreeSet<>();
		
		balanceOrder(array);
		fillCollection(treeBalanced, array);
		
		assertEquals(height, treeBalanced.height());
		assertEquals(nNumbers / 2, treeBalanced.width());
		
	}
	
	private void balanceOrder(int[] array) {
		List<Integer> list = new ArrayList<>();
		balanceOrder(array, 0, array.length - 1, list);
		int index = 0;
		for(int num: list) {
			array[index++] = num;
		}
		
		
	}
	private void balanceOrder(int[] array, int left, int right,
			List<Integer> list) {
		if(left <= right) {
			int middle = (left + right) / 2;
			list.add(array[middle]);
			balanceOrder(array, left, middle - 1, list);
			balanceOrder(array, middle + 1, right, list);
		}
		
	}
	@Test
	void inversionTreeTest() {
		Integer[] expected = {100, 50, 30, 10, 7, -20};
		treeSet.inversion();
	    assertArrayEquals(expected, treeSet.toArray(new Integer[0]));
	    assertTrue(treeSet.contains(100));
	}

}