package sortingAndSearching;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Imagine you have a 20 GB file with one string per line.
 * Explain how you would sort the file.
 * 
 * Important points:
 * 1. This is an example of external sort.
 * 2. We bring chunk of data in memory, sort it and save in some file.
 * 3. We repeat the process until all chunks are sorted.
 * 4. Now we do n-way merge to merge the files 
 */
public class SortBigFile {

	/**
	 * Important points:
	 * 1. Assumptions:
	 *    a. Lines is multiple of maxLinesInMemory. This way we can sort the override the buffer without emptying it every time.
	 *    b. Lines is greater than maxLinesInMemory
	 *    
	 * sortBigFile(file, lines, maxLinesInMemory):
	 *  // Sort chunks of files and store in disk
	 *   fileReader = new fileReader(file)
	 *   bufferedReader = new bufferedReader(fileReader)
	 *   buffer = new string[maxLinesInMemory]
	 *   slices = lines/maxLinesInMemory
	 *   for i = 0 to slices-1
	 *     for j = 0 to maxLinesInMemory-1
	 *       buffer[j] = bufferedReader.readLine()
	 *     Arrays.sort(buffer)
	 *     fileWriter = new fileWriter("temp" + i + ".txt)
	 *     printWriter = new printWriter(fileWriter)
	 *     for str in buffer
	 *       printWriter.println(str)
	 *     printWriter.close()
	 *     fileWriter.close()
	 *   bufferedReader.close()
	 *   fileReader.close()
	 *   // N-way merge for files
	 *   brs = new bufferedReader[slices]
	 *   topLines = new string[slices]
	 *   fileWriter = new fileWriter("sorted-file.txt")
	 *   printWriter = new printWriter(fileWriter)
	 *   for i = 0 to slices-1
	 *     brs[i] = new bufferedReader(new fileReader("temp" + i + ".txt")
	 *   for i = 0 to slices-1
	 *     topLines[i] = brs[i].readLine();
	 *   for i = 0 to lines - 1
	 *     minIndex = 0
	 *     min = topLines[0]
	 *     for j = 0 to slices-1
	 *       if topLines[j] != null && (min == null || topLines[j].compareTo(min) < 0)
	 *         min = topLines[j]
	 *         minIndex = j
	 *     printWriter.println(min)
	 *     topLines[minIndex] = brs[minIndex].readLine()
	 *   // Close writers
	 *   for i = 0 to slices-1
	 *     brs[i].close()
	 *   printWriter.close()
	 *   fileWriter.close()
	 */
	public void sortBigFile(String file, int lines, int maxLinesInMemory) {
		// Assumptions:
		//   a. Lines is multiple of maxLinesInMemory. This way we can sort the override the buffer without emptying it every time.
		//   b. Lines is greater than maxLinesInMemory
		
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String[] buffer = new String[maxLinesInMemory];
			int slices = lines/maxLinesInMemory;
			
			// Sort chunks of files and store in disk
			for (int i = 0; i < slices; i++) {
				for (int j = 0; j < maxLinesInMemory; j++) {
					buffer[j] = bufferedReader.readLine();
				}
				Arrays.sort(buffer);
				FileWriter fileWriter = new FileWriter("temp" + i + ".txt");
				PrintWriter printWriter = new PrintWriter(fileWriter);
				for (String str: buffer) {
					printWriter.print(str);
				}
				printWriter.close();
				fileWriter.close();
			}
			bufferedReader.close();
			fileReader.close();
			
			// N-way merge for files
			BufferedReader[] brs = new BufferedReader[slices];
			String[] topLines = new String[slices];
			FileWriter fileWriter = new FileWriter("sorted-file.txt");
			PrintWriter printWriter = new PrintWriter(fileWriter);
			for (int i = 0; i < slices; i++) {
				brs[i] = new BufferedReader(new FileReader("temp" + "i" + ".txt"));
			}
			for (int i = 0; i < slices; i++) {
				topLines[i] = brs[i].readLine();
			}
			for (int i = 0; i < lines; i++) {
				int minIndex = 0;
				String min = topLines[0];
				for (int j = 0; j < slices; j++) {
					if (topLines[j] != null && (min == null || topLines[j].compareTo(min) < 0)) {
						min = topLines[j];
						minIndex = j;
					}
				}
				printWriter.println(min);
				topLines[minIndex] = brs[minIndex].readLine();
			}
			
			// Close writers
			for (int i = 0; i < slices; i++) {
				brs[i].close();
			}
			printWriter.close();
			fileWriter.close();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
