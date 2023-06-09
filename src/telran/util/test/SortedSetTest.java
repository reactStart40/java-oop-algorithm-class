package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import telran.util.Set;
import telran.util.SortedSet;

public abstract class SortedSetTest extends SetTest {

	@Override
	protected Integer[] getActual(Integer[] array, int size) {
		//for iterating in the sorted order no need an additional sorting
		return array;
	}
	@Test
	void firstTest() {
		SortedSet<Integer> sortedSet = (SortedSet<Integer>)set;
		
		assertEquals(-20, sortedSet.first());
		sortedSet.clear();
		assertThrowsExactly(NoSuchElementException.class, ()->sortedSet.first());
	}
	@Test
	void lastTest() {
		SortedSet<Integer> sortedSet = (SortedSet<Integer>)set;
		assertEquals(100, sortedSet.last());
		sortedSet.clear();
		assertThrowsExactly(NoSuchElementException.class, ()->sortedSet.first());
	}
	@Test
	void ceilingTest() {
		//{ 10, -20, 7, 50, 100, 30 };
		SortedSet<Integer> sortedSet = (SortedSet<Integer>)set;
		runTestForExisted(sortedSet, true);
		assertEquals(50, sortedSet.ceiling(35));
		assertEquals(-20, sortedSet.ceiling(-40));
		assertNull(sortedSet.ceiling(101) );
	}
	private void runTestForExisted(SortedSet<Integer> sortedSet, boolean isCeiling) {
		assertEquals(-20, isCeiling ? sortedSet.ceiling(-20) :sortedSet.floor(-20));
		assertEquals(50, isCeiling ? sortedSet.ceiling(50) :sortedSet.floor(50));
		assertEquals(100, isCeiling ? sortedSet.ceiling(100) :sortedSet.floor(100));
	}
	@Test
	void floorTest() {
		SortedSet<Integer> sortedSet = (SortedSet<Integer>)set;
		runTestForExisted(sortedSet, false);
		assertEquals(50, sortedSet.floor(55));
		assertEquals(100, sortedSet.floor(101));
		assertNull(sortedSet.floor(-40) );
	}

}
