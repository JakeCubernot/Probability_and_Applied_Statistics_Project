import java.util.ArrayList;

public class TestSetOperations {
    public static void main(String[] args) {

        SetOperations test = new SetOperations();

        int[] sampleSetA = {1, 2, 3, 4, 5};
        int[] sampleSetB = {2, 4, 5, 6, 7, 2};
        System.out.println("\nSample Set A: " + test.getList(sampleSetA));
        System.out.println("Sample Set B: " + test.getList(sampleSetB));
        System.out.println("A union B: " + test.findUnion(sampleSetA, sampleSetB));
        System.out.println("A interect B: " + test.findIntersection(sampleSetA, sampleSetB));

        ArrayList<String> userDaysOfWeekSet = new ArrayList<>() {/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
		{
			
            add("Tuesday");
            add("Thursday");
        }};
        System.out.println("A complement: " + test.findComplementOfDaysOfWeek(userDaysOfWeekSet));
        System.out.println("A complement: " + test.findComplementOfNumber(sampleSetA, -2, 10));
        
    }
}
