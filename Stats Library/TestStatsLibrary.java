import java.math.BigInteger;

public class TestStatsLibrary {
    public static void main(String[] args) {
        StatsLibrary test = new StatsLibrary();

		int[] sampleList = {3, 9, 1, 4, 5, 7, 4, 6, 8, 2};
		System.out.println("Sample List: " + test.getList(sampleList));
		System.out.println("\nThe mean is: " + test.findMean(sampleList));
		System.out.println("The median is: " + test.findMedian(sampleList));
		System.out.println("The mode is: " + test.findMode(sampleList));
		
		System.out.println("\nThe standard deviation is: " + test.findStandardDeviation(sampleList));
		System.out.println("The variance is: " + test.findVariance(sampleList));

		System.out.println("\nP(5,3) = " + test.findPermutation(5, 3));
		System.out.println("C(5,3) = " + test.findCombination(5, 3));

		System.out.println("\nBinomial Distribution of n=6, x=2, p=80%: " + test.findBinomialDistribution(6, 2, .80));
        System.out.println("Conditional Probability of A∩B=50% and B=20%: " + test.findConditionalProbability(0.5, 0.2));
        System.out.println("Geometric Distribution of k=3 and p=20%: " + test.findGeometricDistribution(3, 0.2));
        System.out.println("Hypergeometric Distribution of N=10, n=4, k=3, x=2: " + test.findHypergeometricDistribution(10, 4, 3, 2));
    }
}
