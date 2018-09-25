package bitManipulation;

/**
 * Question:
 * Write a program to swap odd and even bits in an integer with as few instructions as possible
 * E.g. bit 0 and 1 are swapped, bit 2 and 3 are swapped, and so on.
 */
public class PairwiseSwap {

	/**
	 * Important points:
	 * 1. We can first clear all even bits and then right shift odd bits by 1. Shift will be logical shift to make sign bit 0.
	 * 2. Then we can clear all odd bits and then left shift even bits by 1.
	 * 3. To clear even bits, we will & with 1010..1010 i.e. for 32 bits in hexadecimal its 0xaaaaaaaa
	 * 4. Similarly to clear odd bits, we will & with 0101..0101 i.e. for 32 bits in hexadecimal its 0x55555555
	 * 
	 * swapEvenOddBits(n)
	 *   return ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1)
	 */
	public int swapOddEvenBits(int n) {
		return ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);
	}
}
