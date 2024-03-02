public class TestStatsLibrary {
    public static void main(String[] args) {
        StatsLibrary test = new StatsLibrary();

		int[] sampleList = {3, 9, 1, 4, 5, 7, 4, 6, 8, 2};
		System.out.println("Sample List: " + test.getList(sampleList));
		System.out.println("The mean is: " + test.findMean(sampleList));
		System.out.println("The median is: " + test.findMedian(sampleList));
		System.out.println("The mode is: " + test.findMode(sampleList));
		
		System.out.println("The standard deviation is: " + test.findStandardDeviation(sampleList));
		System.out.println("The variance is: " + test.findVariance(sampleList));

		System.out.println("\nP(5,3) = " + test.findPermutation(5, 3));
		System.out.println("C(5,3) = " + test.findCombination(5, 3));
		System.out.println("Binomial Distribution of n=6, x=2, p=.80: " + test.findBinomialDistribution(6, 2, .80));
    }
}
