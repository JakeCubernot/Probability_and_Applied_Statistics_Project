import java.util.ArrayList;

public class SetOperations {

    private ArrayList<String> daysOfWeekSet = new ArrayList<>() {{
		add("Sunday");
		add("Monday");
		add("Tuesday");
		add("Wednesday");
		add("Thursday");
		add("Friday");
		add("Saturday");
	}};

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
