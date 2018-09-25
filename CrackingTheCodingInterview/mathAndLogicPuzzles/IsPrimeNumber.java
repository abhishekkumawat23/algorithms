package mathAndLogicPuzzles;

/**
 * Check if given number is prime or not.
 */
public class IsPrimeNumber {

	/**
	 * isPrimeNumber(n):
	 *   if n < 2
	 *     return false
	 *   if n == 2
	 *     return true
	 *   if n % 2 == 0 // If n is even, return false
	 *     return false
	 *   // Check prime by dividing from odd numbers.
	 *   for i = 3 to sqrt(n) where i = i + 2
	 *     if n % i == 0
	 *       return false
	 *   return true 
	 */
	public boolean isPrimeNumber(int n) {
		if (n < 2) {
			return false;
		}
		if (n == 2) {
			return true;
		}
		// If n is even, return false (Only 2 even number is prime)
		if (n % 2 == 0) {
			return false;
		}
		// Check prime by dividing from odd numbers.
		for (int i = 3; i < (int)Math.sqrt(n); i += 2){
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
}
