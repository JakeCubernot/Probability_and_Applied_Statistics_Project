import java.util.ArrayList;

public class Example {

	private int findFactorial(int n) {
		int factorialResult = n;
		for(int i = n - 1; i > 0; i--) {
			factorialResult *= i;
		}
		return factorialResult;
	}

	private ArrayList<Integer> listToArrayList(int[] userList) {
		ArrayList<Integer> newArrayList = new ArrayList<Integer>();
		for(int i = 0; i < userList.length; i++) {
			newArrayList.add(userList[i]);
		}
		return newArrayList;
	}

	private int[] arrayListToList(ArrayList<Integer> userArrayList) {
		int[] newList = new int[userArrayList.size()];
		for (int i = 0; i < userArrayList.size(); i++) {
			newList[i] = userArrayList.get(i);
		}
		return newList;
	}

	public String getList(int[] userInput) {
		String userListToString = "{";
		for (int i = 0; i < userInput.length; i++) {
			if (i == userInput.length - 1) {
				userListToString += (String.valueOf(userInput[i]) + "}");
			} else {
				userListToString += (String.valueOf(userInput[i]) + ", ");
			}
		}
		return userListToString;
	}

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
		ArrayList<Integer> orderedList = listToArrayList(userInput);
		orderedList.sort(null);

		if(orderedList.size() % 2 == 0) {
			return ((orderedList.get((orderedList.size() / 2))) + 
			orderedList.get((orderedList.size() / 2) - 1)) / 2.0;
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
	
	public String findUnion(int[] userSet1, int[] userSet2) {
		ArrayList<Integer> userCombinedSet = listToArrayList(userSet1);
		ArrayList<Integer> userArrayListTemp = listToArrayList(userSet2);
		ArrayList<Integer> unionResult = new ArrayList<Integer>();
		userCombinedSet.addAll(userArrayListTemp);
		userCombinedSet.sort(null);
		unionResult.add(userCombinedSet.get(0));
		for (int i = 1; i < userCombinedSet.size(); i++) {
			if (userCombinedSet.get(i) != userCombinedSet.get(i - 1)) {
				unionResult.add(userCombinedSet.get(i));
			}
		}
		return getList(arrayListToList(unionResult));
	}
	
	public String findIntersection(int[] userSet1, int[] userSet2) {
		ArrayList<Integer> intersectionResult = new ArrayList<Integer>();
		for (int i = 0; i < userSet1.length; i++) {
			for (int j = 0; j < userSet2.length; j++) {
				if (userSet1[i] == userSet2[j]) {
					intersectionResult.add(userSet1[i]);
				}
			}
		}
		return getList(arrayListToList(intersectionResult));
	}
	
	public String findComplement(int[] userSet, int minCount, int maxCount) {
		ArrayList<Integer> userArrayList = listToArrayList(userSet);
		ArrayList<Integer> complementResult = new ArrayList<Integer>();
		for (int i = minCount; i <= maxCount; i++) {
			boolean valueInSet = false;
			for (int j = 0; j < userArrayList.size(); j++) {
				if (i == userArrayList.get(j)) {
					valueInSet = true;
					break;
				}
			}
			if (valueInSet == false) {
				complementResult.add(i);
			}
		}
		return getList(arrayListToList(complementResult));
	}
	
	public int findPermutation(int n, int r) {
		int permutationResult = findFactorial(n)/(findFactorial(n - r));
		return permutationResult;
	}
	
	public int findCombination(int n, int r) {
		int combinationResult = findFactorial(n)/(findFactorial(r) * findFactorial(n - r));
		return combinationResult;
	}
	
}
