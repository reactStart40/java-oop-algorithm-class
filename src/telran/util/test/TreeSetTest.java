package telran.util.test;

import telran.util.TreeSet;

import org.junit.jupiter.api.Test;

import telran.util.Set;

public class TreeSetTest extends SortedSetTest {

	@Override
	protected <T> Set<T> getSet() {
		
		return new TreeSet<>();
	}
}
