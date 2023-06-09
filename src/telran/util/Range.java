package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class Range implements Iterable<Integer> {
	private int min;
	private int max;
	private List<Integer> removedList = new LinkedList<>();
	public Range(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("min must be less than max");
		}
		this.min = min;
		this.max = max;
	}
	private class RangeIterator implements Iterator<Integer> {
		Integer current = getCurrent(min - 1);
		Integer prev = null;
		boolean flNext = false;
		@Override
		public boolean hasNext() {
			
			return current != null;
		}

		@Override
		public Integer next() {
			if(current == null) {
				throw new NoSuchElementException();
			}
			int currentNum = current;
			prev = current;
			current = getCurrent(current);
			flNext = true;
			return currentNum;
		}
		private Integer getCurrent(Integer current) {
			Integer res = null;
			current++;
			while(current < max && res == null) {
				if(!removedList.contains(current)) {
					res = current;
				}
				current++;
			}
			return res;
		}

		@Override
		public void remove() {
			if(!flNext) {
				throw new IllegalStateException();
			}
			removedList.add(prev);
			flNext = false;
			
		}
		
	}
	@Override
	public Iterator<Integer> iterator() {
		
		return new RangeIterator();
	}
	public Integer[] toArray() {
		Integer [] array = new Integer[getSize()];
		int index = 0;
		//First way
//		for(Integer num: this) {
//			array[index++] = num;
//		}
		//Second way
		Iterator<Integer> it = iterator();
		while(it.hasNext()) {
			array[index++] = it.next();
		}
		
		return array;
	}
	public boolean removeIf(Predicate<Integer> predicate) {
		int oldSize = getSize() ;
		Iterator<Integer> it = iterator();
		while(it.hasNext()) {
			int number = it.next();
			if (predicate.test(number)) {
				it.remove();
			}
		}
		return oldSize > getSize();
	}
	private int getSize() {
		return max - min - removedList.size();
	}
	

}