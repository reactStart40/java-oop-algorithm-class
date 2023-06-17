package telran.util.stream;

import java.util.Random;

import telran.util.ArrayList;
import telran.util.List;

public class PrimitiveStreams {
	static public int[] randomUnique(int nNumbers, int minInclusive,
			int maxExclusive) {
		if (maxExclusive - minInclusive < nNumbers) {
			throw new IllegalArgumentException("impossible to get the given amount of unique random numbers");
		}
		return new Random().ints(minInclusive, maxExclusive)
				.distinct().limit(nNumbers).toArray();
	}
	static public int[] shuffle(int[] array) {
		 
		//returns new array with shuffled numbers
		//Implementation hints: two stream pipes
		//first stream pipe fills telran.util.ArrayList<Integer> with array's numbers in the
		// random order ( apply the same approach
		// as in the randomUnique method with forEach instead of toArray for
		// adding numbers to the ArrayList
		// 
		//second stream pipe creates array of int's from telran.util.ArrayList as we have done at class
		List<Integer> list = new ArrayList<>();
		new Random().ints(0, array.length).distinct().limit(array.length)
		.forEach(i -> list.add(array[i]));
		
		return list.stream().mapToInt(n -> n).toArray();
	}

}