public class Dependencies {
    /**
	 * Determines if the probabililties an event are independent events.
	 * (Formulas used from https://www.onlinemathlearning.com/compound-probability-7sp8a.html)
	 * 
	 * @param pA Probability of A
	 * @param pB Probability of B
	 * @param pAIntersectsB Probability of A intersects B
	 * @return Boolean of if is an independent event
	 */
	public boolean isIndependentEvent(double pA, double pB, double pAIntersectsB) {
		if (pAIntersectsB == pA * pB) {
			return true;
		} else {
			return false;
		}
	}


	/**
	 * Determines if the probabililties an event are dependent events.
	 * 
	 * @param pA Probability of A
	 * @param pAIntersectsB Probability of A intersects B
	 * @param pBGivenA Probability of A given B
	 * @return Boolean of if is a dependent event
	 */
	public boolean isDependentEvent(double pA, double pAIntersectsB, double pBGivenA) {
		if (pAIntersectsB == pA * pBGivenA) {
			return true;
		} else {
			return false;
		}
	}


	/**
	 * Determines if the probabililties an event are mutually exclusive.
	 * 
	 * @param pA Probability of A
	 * @param pB Probability of B
	 * @param pAUnionsB Probability of A unions B
	 * @return Boolean of if is mutually exclusive
	 */
	public boolean isMutuallyExclusive(double pA, double pB, double pAUnionsB) {
		if (pAUnionsB == pA + pB) {
			return true;
		} else {
			return false;
		}
	}


	/**
	 * Determines if the probabililties an event are mutually inclusive.
	 * 
	 * @param pA Probability of A
	 * @param pB Probability of B
	 * @param pAUnionsB Probability of A unions B
	 * @param pAIntersectsB Probability of A intersects B
	 * @return Boolean of if is mutually inclusive
	 */
	public boolean isMutuallyInclusive(double pA, double pB, double pAUnionsB, double pAIntersectsB) {
		if (pAUnionsB == pA + pB - pAIntersectsB) {
			return true;
		} else {
			return false;
		}
	}
}
