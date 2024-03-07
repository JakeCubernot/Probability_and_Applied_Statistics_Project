import java.util.ArrayList;

/**
 * The SetOperations class contains some basic set operations for mainly 
 * integer lists.
 * 
 * @author Jake Cubernot
 */
public class SetOperations {

    private ArrayList<String> daysOfWeekSet = new ArrayList<>() {/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	{
		add("Sunday");
		add("Monday");
		add("Tuesday");
		add("Wednesday");
		add("Thursday");
		add("Friday");
		add("Saturday");
	}};

	
	/** 
	 * Converts an int[] to ArrayList<Integer>.
	 * 
	 * @param userList The list that the user wants to convert to an Arraylist<Integer>.
	 * @return The converted int[] to ArrayList<Integer>.
	 */
	private ArrayList<Integer> listToArrayList(int[] userList) {
		ArrayList<Integer> newArrayList = new ArrayList<Integer>();
		for(int i = 0; i < userList.length; i++) {
			newArrayList.add(userList[i]);
		}
		return newArrayList;
	}

	
	/** 
	 * Converts an ArrayList<Integer> to an int[].
	 * 
	 * @param userArrayList The ArrayList<Integer> that the user wants to convert to an int[].
	 * @return The converted ArrayList<Integer> to int[].
	 */
	private int[] arrayListToList(ArrayList<Integer> userArrayList) {
		int[] newList = new int[userArrayList.size()];
		for (int i = 0; i < userArrayList.size(); i++) {
			newList[i] = userArrayList.get(i);
		}
		return newList;
	}

	
	/** 
	 * Converts an int[] to a String that shows a visual representation of the list, int[].
	 * 
	 * @param userList The int[] that the user wants to convert to a String.
	 * @return The String that shows a visual representation of the list, int[].
	 */
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
	
	
	/** 
	 * Finds the the union set of A union B where the both sets are int[].
	 * 
	 * @param userSet1 User's Set A as an int[]. 
	 * @param userSet2 User's Set B as an int[]. 
	 * @return String displaying the result of A union B.
	 */
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
	
	/** 
	 * Finds the the intersection set of A intersects B where the both sets are int[].
	 * 
	 * @param userSet1 User's Set A as an int[]. 
	 * @param userSet2 User's Set B as an int[]. 
	 * @return String displaying the result of A intersects B.
	 */
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
	
	/** 
	 * Finds the complement set of a set where the universal set is the days of the week.
	 * 
	 * @param userSet User's set as an ArrayList<String>. 
	 * @return String displaying the result of the complement of the user's set 
	 * where the universal set is the days of the week.
	 */
	public String findComplementOfDaysOfWeek(ArrayList<String> userSet) {
		ArrayList<String> complementResult = new ArrayList<>();
		for(int i = 0; i < daysOfWeekSet.size(); i++) {
			boolean valueInSet = false;
			for (int j = 0; j < userSet.size(); j++) {
				if (daysOfWeekSet.get(i).equals(userSet.get(j))) {
					valueInSet = true;
					break;
				}
			}
			if (!valueInSet) {
				complementResult.add(daysOfWeekSet.get(i));
			}
		}
		return complementResult.toString();
	}

	/** 
	 * Finds the the complement set of a set where the universal set is a specified set of integer values.
	 * 
	 * @param userSet User's set as an int[]. 
	 * @param minCount The minimum value of the universal set. 
	 * @param maxCount The maximum value of the universal set. 
	 * @return String displaying the result of the complement of the user's set 
	 * where the universal set is a specified set of integer values.
	 */
	public String findComplementOfNumber(int[] userSet, int minCount, int maxCount) {
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
}
