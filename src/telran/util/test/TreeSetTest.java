package telran.util.test;

import telran.util.TreeSet;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		int height = 20;
		int nNumbers =  (int) Math.pow(2, height);
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
		    int length = array.length;
		    int[] reorderedArray = new int[length];
		    balanceOrderRecursive(array, reorderedArray, 0, length - 1, 0);
		    System.arraycopy(reorderedArray, 0, array, 0, length);
		}

		private void balanceOrderRecursive(int[] source, int[] destination, int left, int right, int index) {
		    if (left <= right) {
		        int mid = (left + right) / 2;
		        destination[index] = source[mid];
		        balanceOrderRecursive(source, destination, left, mid - 1, 2 * index + 1);
		        balanceOrderRecursive(source, destination, mid + 1, right, 2 * index + 2);
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