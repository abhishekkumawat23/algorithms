package treesAndGraphs;

/**
 * Question:
 * Implement prefix tree.
 */
public class PrefixTree {
	private final static int ALPHABET_SIZE = 26;
	private Node root;
	
	/**
	 * Time: O(26*k) where k is length of word; Space: O(1)
	 * 
	 * Pseudo code:
	 * insert(word)
	 *   current = root
	 *   for i = 0 to word.length-1
	 *     index = word[i] - 'a'
	 *     node = current.children[index]
	 *     if node == null
	 *       node = new node
	 *       current.children[index] = node
	 *     current = current.children[index]
	 *   current.isEndOfWord = true
	 */
	public void insert(String word) {
		Node current = root;
		for (int i = 0; i < word.length(); i++) {
			int index = word.charAt(i) - 'a';
			Node node = current.children[index];
			if (node == null) {
				node = new Node();
				current.children[index] = node;
			}
			current = current.children[index];
		}
		current.isEndOfWord = true;
	}
	
	/**
	 * Time: O(26*k) where k is length of word; Space: O(1)
	 * 
	 * Pseudo code:
	 * isValidWord(word):
	 *   current = root
	 *   for i = 0 to word.length-1
	 *     index = word[i] - 'a'
	 *     if current.children[index] == null
	 *       return false
	 *     current = current.children[index]
	 *     
	 *   return current.isEndOfWord
	 */
	public boolean isValidWord(String word) {
		Node current = root;
		for (int i = 0; i < word.length(); i++) {
			int index = word.charAt(i) - 'a';
			if (current.children[index] == null) {
				return false;
			}
			current = current.children[index];
		}
		return current.isEndOfWord;
	}
	
	/**
	 * Time: O(26*k) where k is length of word; Space: O(1)
	 * 
	 * Pseudo code:
	 * isValidPrefix(word):
	 *   current = root
	 *   for i = 0 to word.length-1
	 *     index = word[i] - 'a'
	 *     if current.children[index] == null
	 *       return false
	 *     current = current.children[index]
	 *   return true
	 */
	public boolean isValidPrefix(String word) {
		Node current = root;
		for (int i = 0; i < word.length(); i++) {
			int index = word.charAt(i) - 'a';
			if (current.children[index] == null) {
				return false;
			}
			current = current.children[index];
		}
		return true;
	}
	
	/**
	 * Time: O(26*k) where k is length of word; Space: O(1)
	 * 
	 * Pseudo code:
	 * delete(word):
	 *   current = root
	 *   for i = 0 to word.length-1
	 *     index = word[i] - 'a'
	 *     if current.children[index] == null
	 *       return
	 *     current.children[index] = null
	 */
	public void delete(String word) {
		Node current = root;
		for (int i = 0; i < word.length(); i++) {
			int index = word.charAt(i) - 'a';
			if (current.children[index] == null) {
				return;
			}
			current.children[index] = null;
		}
	}
	
	private class Node{
		public Node[] children = new Node[ALPHABET_SIZE];
		boolean isEndOfWord;
	}
}
