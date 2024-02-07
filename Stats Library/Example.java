import java.util.ArrayList;

public class Example {

	// I used https://www.cuemath.com/mean-median-mode-formula/ to get mathamatical formulas
	// and to help me get a better understanding of the functions.
	
	public double findMean(int[] userInput) {
		double sum = 0;
		for(int i = 0; i < userInput.length; i++) {
			sum = userInput[i] + sum;
		}
		
		double result = sum / userInput.length;
		
		return result;
	}

	// Create median: The list must be ordered, so order the list before finding median.
	// Should be able to call collections sort or arraylist sort of some kind.
	public double findMedian(int[] userInput) {
		ArrayList<Integer> orderedList = new ArrayList<Integer>();

		for(int i = 0; i < userInput.length; i++) {
			orderedList.add(userInput[i]);
		}
		orderedList.sort(null);

		if(orderedList.size() % 2 == 0) {
			return ((orderedList.get((orderedList.size() / 2))) + orderedList.get((orderedList.size() / 2) - 1)) / 2.0;
		} else {
			return orderedList.get(orderedList.size() / 2);
		}
	}

	// Create mode
	public double findMode(int[] userInput) {

		int maxRepeatCount; // Stores the number of repeats that occurs on each index i.
		int maxRepeatIndex = 0; // Stores the index which had the highest count of repeats. 
		int maxTopRepeatCount = 0; // Stores the top repeat count of the current index with most repeats.

		for(int i = 0; i < userInput.length; i++) {
			maxRepeatCount = 0;
			for(int j = 0; j < userInput.length; j++) {
				if(userInput[i] == userInput[j]) {
					maxRepeatCount += 1;
				}
			}
			if(maxRepeatCount > maxTopRepeatCount) {
				maxRepeatIndex = i;
				maxTopRepeatCount = maxRepeatCount;
			}
		}
		return userInput[maxRepeatIndex];
	}
	
	public double findStandardDeviation(int[] userInput) {
		double listMean = findMean(userInput);
		double standardDeviation = 0;
		for(int i = 0; i < userInput.length; i++) {
			standardDeviation += Math.pow(userInput[i] - listMean, 2);
		}
		standardDeviation = Math.sqrt(standardDeviation / userInput.length);
		return standardDeviation;
	}
	
//	public int[] findUnion(int[] userList1, int[] userList2) {
//		
//	}
//	
//	public int[] findIntersection(int[] userList1, int[] userList2) {
//		
//	}
//	
//	public int[] findComplement(int[] userList1, int[] userList2) {
//		
//	}
//	
//	public double findPermutation(int n, int r) {
//		
//	}
//	
//	public double findCombination(int n, int r) {
//		
//	}
	
	public int findFactorial(int n) {
		int factorialResult = n;
		for(int i = n - 1; i > 0; i--) {
			factorialResult *= i;
		}
		return factorialResult;
	}
}
