
public class TestExample {
	public static void main(String[] args) {

		Example test = new Example();

		int[] sampleList = {3, 9, 1, 4, 5, 7, 4, 6, 8, 2};
		System.out.println("Sample List: " + test.getList(sampleList));
		System.out.println("The mean is: " + test.findMean(sampleList));
		System.out.println("The median is: " + test.findMedian(sampleList));
		System.out.println("The mode is: " + test.findMode(sampleList));
		System.out.println("The standard deviation: " + test.findStandardDeviation(sampleList));

		int[] sampleSetA = {1, 2, 3, 4, 5};
		int[] sampleSetB = {2, 4, 5, 6, 7};
		System.out.println("\nSample Set A: " + test.getList(sampleSetA));
		System.out.println("Sample Set B: " + test.getList(sampleSetB));
		System.out.println("A union B: " + test.findUnion(sampleSetA, sampleSetB));
		System.out.println("A interect B: " + test.findIntersection(sampleSetA, sampleSetB));
		System.out.println("A complement: " + test.findComplement(sampleSetA, 10));

		System.out.println("\nP(5,3) = " + test.findPermutation(5, 3));
		System.out.println("C(5,3) = " + test.findCombination(5, 3));
	}
}
