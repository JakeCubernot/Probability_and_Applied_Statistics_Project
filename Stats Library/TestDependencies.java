
public class TestDependencies {
    public static void main(String[] args) {
        double pA = 0.4;
        double pB = 0.3;
        double pAIntersectsB = 0.12; 
        double pBGivenA = 0.6; 
        double pAUnionsB = 0.58; 

        Dependencies testDependencies = new Dependencies();

        System.out.println("Events A and B are independent: " + testDependencies.isIndependentEvent(pA, pB, pAIntersectsB));
        
        System.out.println("Events A and B are dependent: " + testDependencies.isDependentEvent(pA, pAIntersectsB, pBGivenA));
        
        System.out.println("Events A and B are mutually exclusive: " + testDependencies.isMutuallyExclusive(pA, pB, pAUnionsB));
        
        System.out.println("Events A and B are mutually inclusive: " + testDependencies.isMutuallyInclusive(pA, pB, pAUnionsB, pAIntersectsB));
    }
}