package mathAndLogicPuzzles;

/**
 * Generate list of primes till value n.
 */
public class ListOfPrimes {

	/**
	 * Important points:
	 * 1. Go till sqrt(n) but check only odd numbers: iterate from 3 to sqrt(n) but check only odd numbers.
	 * 2. Returned list reprsents 2,3,5,7,9,11... i.e. first even and then all odd numbers.
	 * 
	 * listOfPrimes(n):
	 *   if n == 1
	 *     return null
	 *   size = n % 2 == 0 ? n/2 : (n+1)/2 // We want to store only odd values in addition to 2.
	 *   prime = boolean[size]
	 *   init(prime)
	 *   for i = 3 to sqrt(n) where i = i + 2
	 *     if !prime[i-1/2]
	 *       continue
	 *     for j = i*i to n where j += 2*i
	 *       if j-1/2 < size
	 *         prime[j-1/2] = false
	 *   return prime
	 * init(prime):
	 *   for i = 0 to prime.length-1
	 *     prime[i] = true
	 */
	public boolean[] listOfPrimes(int n) {
		if (n == 1) {
			return null;
		}
		int size = n % 2 == 0 ? n/2 : (n+1)/2; // We want to store only odd values in addition to 2.
		boolean[] prime = new boolean[size];
		init(prime);
		for (int i = 3; i <= (int)Math.sqrt(n); i += 2) {
			if (!prime[(i-1)/2]) {
				continue;
			}
			for (int j = i*i; j <= n; j += 2*i) {
				if ((j-1)/2 < size) {
					prime[(j-1)/2] = false;
				}
			}
		}
		return prime;
	}
	
	private void init(boolean[] prime) {
		for (int i = 0; i < prime.length; i++) {
			prime[i] = true;
		}
	}
	
	public static void main(String[] args) {
		ListOfPrimes l = new ListOfPrimes();
		boolean[] prime = l.listOfPrimes(1000);
		if (prime != null) {
			for (int i = 0; i < prime.length; i++) {
				if (i == 0) {
					System.out.print(2 + ",");
					continue;
				}
				if (prime[i]) {
					System.out.print(2*i+1 + ",");
				}
				
			}
		}
	}
}
