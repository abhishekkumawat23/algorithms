package treesAndGraphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Question:
 * A binary search tree was created by traversing through an array from left to right and inserting each element.
 * Given a binary search tree with distinct elements, print all possible arrays that could have led to this tree.
 * Input: tree is 1 2 3 (in-order traversal)
 * Output: {2, 1, 3}, {2, 3, 1}
 */
public class BSTSequences {

	/** 
	 * Skipping time and space complexity here.
	 * 
	 * Important points:
	 * 1. These kind of problems are very tricky to think.
	 * 2. To start thinking first break the problem into sub problems
	 *    a. Here we know that sequences of node n = prepend(n.data) to (sequences of n.left + sequences of n.right)
	 *    b. To combine sequences of n.left and sequences of n.right, we need to do weaving.
	 *    c. For weaving of two sequences of size n and m => weave(n, m) = prepend(n.data to weave(n-1, m)) + prepend(m.data to weave(n, m-1))
	 * 3. Once we are able to break the problem in subproblem, convert the problem to subproblem relation in pseudo code.
	 * 4. After that work out corner cases.
	 * 
	 * bstSequences(node):
	 *   if node == null
	 *     return empty sequences 
	 *   leftSequences = bstSequences(node.left)
	 *   rightSequences = bstSequences(node.right)
	 *   if leftSequences.size == 0
	 *     prepend(node.data, rightSequences)
	 *     return rightSequences
	 *   else if rightSequences.size == 0
	 *     prepend(node.data, leftSequences)
	 *     return leftSequences
	 *   for leftSequence in leftSequences
	 *     for rightSequence in rightSequences
	 *       sequences.addAll(weave(leftSequence, 0, rightSequence, 0))
	 *   sequences = prepend(node.data, sequences)
	 *   return sequences
	 * weave(sequence1, seq1Start, sequence2, seq2Start):
	 *   if seq1Start >= sequence1.size
	 *     sequence = sequence2.subList(seq2Start, sequence2.size-1) 
	 *     return sequences.add(sequence)
	 *   if seq2Start >= sequences2.size
	 *     sequence = sequence1.subList(seq1Start, sequence1.size-1)
	 *     return sequences.add(sequence)
	 *   sequencesPart1 = weave(sequence1, seq1Start + 1, sequence2, seq2Start)
	 *   sequencesPart1 = prepend(sequence1[seq1Start].data, sequencesPart1)
	 *   sequencesPart2 = weave(sequence1, seq1Start, sequence2, seq2Start + 1)
	 *   sequencesPart2 = prepend(sequence2[seq2Start].data, sequencesPart2)
	 *   sequences = sequencesPart1
	 *   sequences.add(sequencesPart2)
	 *   return sequences
	 * prepend(data, sequences)
	 *   if sequences.size == 0
	 *     sequences.add(sequence(data))
	 *   else
	 *     for sequence in sequences
	 *       sequence.addFirst(sequence(data))
	 */
	public List<LinkedList<Integer>> bstSequences(Node node){
		if (node == null) {
			List<LinkedList<Integer>> sequences = new ArrayList<LinkedList<Integer>>();
			return sequences;
		}
		
		List<LinkedList<Integer>> leftSequences = bstSequences(node.left);
		List<LinkedList<Integer>> rightSequences = bstSequences(node.right);
		if (leftSequences.size() == 0) {
			prepend(node.data, rightSequences);
			return rightSequences;
		}
		if (rightSequences.size() == 0) {
			prepend(node.data, leftSequences);
			return leftSequences;
		}
		List<LinkedList<Integer>> sequences = new ArrayList<LinkedList<Integer>>();
		for (LinkedList<Integer> leftSequence: leftSequences) {
			for (LinkedList<Integer> rightSequence: rightSequences) {
				sequences.addAll(weave(leftSequence, 0, rightSequence, 0));
			}
		}
		prepend(node.data, sequences);
		return sequences;
	}
	
	private void prepend(int data, List<LinkedList<Integer>> sequences){
		if (sequences.size() == 0) {
			LinkedList<Integer> sequence = new LinkedList<Integer>();
			sequence.addFirst(data);
			sequences.add(sequence);
		}
		else {
			for (LinkedList<Integer> sequence: sequences) {
				sequence.addFirst(data);
			}	
		}
	}
	
	private ArrayList<LinkedList<Integer>> weave(LinkedList<Integer> sequence1, int seq1Start, LinkedList<Integer> sequence2, int seq2Start){
		ArrayList<LinkedList<Integer>> sequences = new ArrayList<LinkedList<Integer>>();
		
		if (seq1Start >= sequence1.size()) {
			sequences.add(new LinkedList<Integer>(sequence2.subList(seq2Start, sequence2.size())));
			return sequences;
		}
		
		if (seq2Start >= sequence2.size()) {
			sequences.add(new LinkedList<Integer>(sequence1.subList(seq1Start, sequence1.size())));
			return sequences;
		}
		
		ArrayList<LinkedList<Integer>> sequencesPart1 = weave(sequence1, seq1Start + 1, sequence2, seq2Start);
		prepend(sequence1.get(seq1Start), sequencesPart1);
		ArrayList<LinkedList<Integer>> sequencesPart2 = weave(sequence1, seq1Start, sequence2, seq2Start + 1);
		prepend(sequence2.get(seq2Start), sequencesPart2);
		
		sequences = sequencesPart1;
		sequences.addAll(sequencesPart2);
		return sequences;
	}
	
	public class Node{
		public int data;
		public Node left;
		public Node right;
	}
}
