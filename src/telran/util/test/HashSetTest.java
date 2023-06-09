package telran.util.test;

import telran.util.*;

public class HashSetTest extends SetTest {

	@Override
	protected <T> Set<T> getSet() {
		
		return new HashSet<>(3);
	}

}