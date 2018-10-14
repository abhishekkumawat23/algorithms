package sortingAndSearching;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Given an input file with four billion non-negative integers, provide an algorithm to generate an integer that is not contained in file.
 * Assume you have 1 GB of memory available for this task.
 * Follow up:
 * What if you have only 10 MB of memory?
 * Assume that all the values are distinct and we now have no more than one billion non-negative integers. 
 */
public class MissingInt {

	/**
	 * Important points:
	 * 1. 1 GB has 8 billion bits.
	 * 2. In Java, 2^32 i.e. 4 billion bits i.e. 4 billion integers possible. Out of 4 billion, 2 billion are non-negative
	 * 3. As input file has 4 billion non-negative integers, there will be duplicates.
	 * 4. We can store all 4 billion integers in 1 GB if we store bit wise. But if we store integer as java integer, then we can't as java integer has 32 bits.
	 * 5. We can store bits in boolean array but problem is that array takes int only. And thus boolean array can take Integer.Max size only. But we want to pass Integer.Max+1 size. So we will use byte array
	 * 6. So, 1st bit will store values from 0 to 7, 2nd bit will store values from 8 to 15 and so on.
	 * 
	 * missingInt(file):
	 *   // Set bits
	 *   numberOfBits = Integer.Max + 1 
	 *   bits = new byte[numberOfBits/8]
	 *   fileReader = new fileReader(file)
	 *   scanner = new scanner(fileReader)
	 *   while scanner.hasNextInt()
	 *     value = scanner.nextInt() 
	 *     bits[value/8] |= (1 << value % 8) 
	 *   // Get first empty bit
	 *   for i = 0 to bits.length-1
	 *     for j = 0 to 7
	 *       if bits[i] & (1 << j) == 0
	 *         return i*8 + j
	 *   return -1
	 */
	public int missingInt_oneGB(String file){
		try {
			// Set bits
			long numberOfBits = Integer.MAX_VALUE + 1;
			byte[] bits = new byte[(int)numberOfBits/8];
			FileReader fileReader = new FileReader(file);
			Scanner scanner = new Scanner(fileReader);
			while (scanner.hasNextInt()) {
				int value = scanner.nextInt();
				bits[value/8] |= (1 << value % 8);
			}
			// Get first empty bit
			for (int i = 0; i < bits.length; i++) {
				for (int j = 0; j < 8; j++) {
					if ((bits[i] & (1 << j)) == 0) {
						return i*8 + j;
					}
				}
			}
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * Important points:
	 * 1. 10 MB can contain 8 million bits.Input size in this case is 1 billion
	 * 2. To solve this problem, we will store list of integers containing count of numbers belonging to the range of index.
	 * 3. First index will store count for range from 1 to x, second index will store occurance count for range x+1 to 2x and so on.
	 * 4. 10 MB ~= 2^23 bytes. Possible non negative integers are ~= 2^31. As we are sorting integers for count, space left for array length is 2^23/2^2 = 2^21
	 * 5. Min range should be 2^31/2^21 = 2^10 bits as we need to store 2^31 integers in range.
	 * 6. Max range should be such that is can be stored in memory i.e. in 10 MB i.e. 2^23 bytes i.e 2^26 bits.
	 * 
	 * missingInt(file):
	 *   range = 1 << 20 // Taking 2^20 bits as range size which is well between 2^10 and 2^26
	 *   blocks = getCountForBlocks(file, range)
	 *   blockIndex = findBlockWithMissingCount(blocks, range)
	 *   if blockIndex < 0
	 *     return -1
	 *   bits = createBitsArray(file, blockIndex, range)
	 *   return findMissingInt(bits, blockIndex, range)
	 * getCountForBlocks(file, range)
	 *   blocks = new int[Integer.Max/range + 1]
	 *   scanner = new Scanner(new FileReader(file))
	 *   while scanner.hasNextInt()
	 *     value = scanner.nextInt()
	 *     blocks[value/range]++
	 *   scanner.close()
	 *   return blocks
	 * findBlockWithMissingCount(blocks, range)
	 *   for i = 0 to blocks.length-1
	 *     if blocks[i] < range
	 *       return i
	 *   return -1
	 * createBitsArray(file, blockIndex, range)
	 *   bits = new boolean[range]
	 *   scanner = new Scanner(new FileReader(file))
	 *   while scanner.hasNextInt()
	 *     value = scanner.nextInt()
	 *     if value/range == blockIndex
	 *       bits[value%range] = true
	 *   scanner.close()
	 *   return bits
	 * findMissingInt(bits, blockIndex, range):
	 *   for i = 0 to bits.length
	 *     if !bits[i]
	 *       return blockIndex*range + i
	 *   return -1
	 */
	public int missingInt_tenMB(String file) {
		int range = 1 << 20; // Taking 2^20 bits as range size which is well between 2^10 and 2^26
		int[] blocks = getCountForBlocks(file, range);
		int blockIndex = findBlockWithMissingCount(blocks, range);
		if (blockIndex < 0) {
			return -1;
		}
		boolean[] bits = createBitsArray(file, blockIndex, range);
		return findMissingInt(bits, blockIndex, range);
	}
	
	public int[] getCountForBlocks(String file, int range) {
		int[] blocks = new int[Integer.MAX_VALUE/range + 1];
		try {
			Scanner scanner = new Scanner(new FileReader(file));
			while (scanner.hasNextInt()) {
				int value = scanner.nextInt();
				blocks[value/range]++;
			}
			scanner.close();	
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return blocks;
	}
	
	public int findBlockWithMissingCount(int[] blocks, int range) {
		for (int i = 0; i < blocks.length; i++) {
			if (blocks[i] < range) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean[] createBitsArray(String file, int blockIndex, int range) {
		boolean[] bits = new boolean[range];
		try {
			Scanner scanner = new Scanner(new FileReader(file));
			while (scanner.hasNextInt()) {
				int value = scanner.nextInt();
				if (value/range == blockIndex) {
					bits[value%range] = true;
				}
			}
			scanner.close();	
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return bits;
	}
	
	public int findMissingInt(boolean[] bits, int blockIndex, int range) {
		for (int i = 0; i < bits.length; i++) {
			if (!bits[i]) {
				return blockIndex*range + i;
			}
		}
		return -1;
	}
}
