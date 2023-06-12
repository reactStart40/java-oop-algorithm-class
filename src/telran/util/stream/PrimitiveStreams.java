package telran.util.stream;

import java.util.Random;

public class PrimitiveStreams {
	static public int[] randomUnique(int nNumbers, int minInclusive,
			int maxExclusive) {
		if (maxExclusive - minInclusive < nNumbers) {
			throw new IllegalArgumentException("impossible to get the given amount of unique random numbers");
		}
		return new Random().ints(minInclusive, maxExclusive)
				.distinct().limit(nNumbers).toArray();
	}

}