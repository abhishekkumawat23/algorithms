package sortingAndSearching;

/**
 * Implement radix sort
 * 
 * Important points:
 * 1. We sort the elements by least significant digit.
 * 2. Then we sort the elements by 2nd least significant digit and so on.
 * 3. When range is very small in compare to number of elements, we use only count sort.
 * 4. But when range of elements is large in compare to number of elements, we use radix sort.
 * 5. Counting sort for reference type:
 *    a. If we simply store the count corresponding the a hash, our sorting will not happen as multiple objects with same hash can be present.
 *    b. So, first we store the count as we do for primitive types. Example: [2, 4, 2, 1, 6] which means first smallest element came 2 times, second smallest element came 4 times etc.
 *    c. Now we update the count sort to contain actual position of elements. Example [2, 2+4, 2+4+2, 2+4+2+1, 2+4+2+1+6] => Example[2, 6, 8, 9, 15]
 *    d. From this array value, we can get index = value-1. We will store element of a hash at that index and then decrement the value.
 *    e. We can't do inplace sorting using this. So we need additional array of space n. So Space complexity will be O(n+k) where k is range
 */
public class RadixSort {

	/**
	 * Time: O(d*(n+b)) where d is number of digits in max value and b is base.; Space: O(k+n)
	 * If k is the max value, d will be logb(k). So Time: O((n+b) * logb(k))
	 * 
	 * Important points:
	 * 1. Counting sort for primitive type doesn't need extra space for output array. but for primitive type, we need it.
	 * 2. Main funda in reference type counting is that we still have a count of values and then we add them to get the index of element.
	 * 
	 * sort(array, base):
	 *   max = findMax(array)
	 *   for exp = 1 to some value until max/exp > 0 with increment of exp *= base
	 *     array = countSort(array, exp, base)
	 *   return array
	 * countSort(array, exp, base):
	 *   count = new List<Integer>[base]
	 *   output = new int[arra.length]
	 *   // Store count
	 *   for i = 0 to array.length-1
	 *     count[(array[i]/exp)%base]++
	 *   // Store actual index
	 *   for i = 1 to count.length-1
	 *     count[i] += count[i-1]
	 *   // Sort in output array
	 *   for i = 0 to array.length-1
	 *     output[count[(array[i]/exp)%base]-1] = array[i]
	 *     count[(array[i]/exp)%base]--
	 *   return output
	 * findMax(array):
	 *   max = array[0]
	 *   for i = 1 to array.length-1
	 *     if array[i] > max
	 *       max = array[i]
	 *   return max
	 */
	public int[] sort(int[] array, int base) {
		int max = findMax(array);
		for (int exp = 1; max/exp > 0; exp *= base) {
			array = countSort(array, exp, base);
		}
		return array;
	}
	
	public int findMax(int[] array) {
		int max = array[0];
		for (int i = 1; i < array.length; i++) {
			max = array[i];
		}
		return max;
	}
	
	public int[] countSort(int[] array, int exp, int base) {
		int[] output = new int[array.length];
		int[] count = new int[base];
		// Store count
		for (int i = 0; i < array.length; i++) {
			count[(array[i]/exp)%base]++;
		}
		// Store actual index
		for (int i = 0; i < count.length; i++) {
			count[i] = count[i+1];
		}
		// Sort in output array
		for (int i = 0; i < array.length; i++) {
			output[count[(array[i]/exp)%base]-1] = array[i];
		}
		return output;
	}
}
