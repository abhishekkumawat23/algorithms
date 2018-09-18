package arraysAndStrings;

/**
 * HashSet class.
 * 
 * This class contains implementation where HashSet uses linked list for collisions. [java 7 approach]
 * Better approach is to use red black self balancing binary search tree (continue using linked list of threshold of 8 elements is not crossed.) [java 8 approach]. Check HashSet.java for the implementation.
 * 
 * If we use tree instead of linkedlist hash function can be simpy h = h ^ (h >>> 16)
 * 
 * Following are the important points when hashset uses tree.
 * 
 * Hashing:
 * We store the hashSet in array internally.
 * Array initial capacity is set to 16. Load factor is set to 0.75.
 * Whenever no. of elements are >= capacity*loadFactor, we create a new array of double the length and copy content from old array.
 * In case of arrayList, we create new array of 1.5 time length but in case of hashSet, its 2 times length. This is because in hashSet not only we create a new array we also recalculate new index for an element and this its an expensive operation in case of hashSet. So we want to minimize this operation. In arrayList its 1.5 times and not 2 times because unnecessary allocation is memory wastage.
 * Hash of key object is the index of array where that key is stored.
 * As hash of key object can be more than array length, so we bit mask the hash by array length. i.e. hashCode % array.length
 * In bit masking, there can be few problems:
 *   i. Not co-primes:
 *      a. If hashCodes of most elements are multiples of x, and x and array.length are not coprime (i.e. their gcd is not 1), then collisions will happen.
 *         A good hash function doesn't have creates multiples of x for different objects. Example: string hashCode is k1*31^0 + k2*31^1 + ... As 31 is prime, final hashCode has better chances to be co-prime with any other number.
 *      b. Non co-prime example: x = 21, length=6, then for 21, 42, 63, 68 etc the modulus will always be 3 or 0. So even though array length is 6, elements will cluster on index 0 and 3 only.
 *      c. Co-prime example: x = 21, length=5, then for 21, 42, 63, 68 etc the modulus will be 0, 1, 2, 3, 4 i.e uniform distribution.
 *      d. Ideally hashCode of key object should not have such patterns like multiples of x. But hashSet can't rely on this. So the array.length must be a large prime number so that chances of co-prime is less and thus less number of collisions.
 *   ii. Inefficiencies in large primes: Even though we will get uniform distribution when used large primes as array.length as chances of element's hasCode and length being co-prime increases, there are few inefficiencies:
 *       a. We need to maintain a list of known primes so that when new array creation is required, we can use next prime as new length.
 *       b. modulus is costly operation. hashCode % array.length is costly operation. If array.length is power of 2, then we can simply do hashCode & (array.length-1) which is very fast operation.
 *   iii. Problems with power of 2: If we use power of 2 as array.length instead of large primes, then following can happen.
 *        a. If hasCodes of most elements are multiples of 2, then we will see lot of collisions (as discussed above).
 *        b. Even if hashCodes are not multiplies of 2, higher bits of hashCode will be ignored.
 *           Example: If array.length is 32 = 2^4 = 10000, then hashCode & (array.length - 1) = hashCode & 01111 will cause all bits except last 4 bits to get ignored (as 1 & 0 = 0).
 * Thus for bit masking, its best that we use prime number as array length for better distribution of elements.
 * But seeing the inefficiencies in using prime number, java uses power of 2 as array length.
 * As power of 2 has issues like more collisions, higher bits ignored etc, we do re-hashing of the hashCode.
 * In this re-hashing, lower bits are appended with higher bits value. h = h ^ (h >>> 20) ^ (h >>> 12); h = h ^ (h >>> 7) ^ (h >>> 4);
 * With this re-hashing we almost solve the issue of power of 2. Now we can use power of 2 benefit which is instead of using %, we can use &. i.e. bit masking now is hsahCode & (arra.length-1)
 * As multiple keys can point to same index either because of bit masking(i.e. diff hashCodes but same index) or becuase of collisions(i.e same hashCodes so same index); So we need to add these elements at same index.
 * For storing these elements which point to same index, we can use Node object (i.e. linked list).
 * Whenever we get a element from its key, we calculate hash, re-hash it, bit mask it to find the index and within that index, we iterate over Node to find the matching key.
 * Thus in worst scenario, get op takes O(n) time where n is no. of elements. This is the case when all the elements are at same index.
 * So, if the hashCode is bad function, get takes o(n) time but if the hashCode is good function, get takes O(n) time.
 * Still as a hashMap should not be on mercy of hashCode function, having a Node (linked list) for storing colliding elements is bad idea as it leads to O(n) time complexity in that case.
 * So, we can use a self balanced binary search tree instead of Node object whenever no. of colliding elements crosses a threshold (in java that's 8). Thus we can get O(logn) time complexity instead of O(n) of linked list.
 * Java uses red black over AVL self balanced binary search tree because red black is fast in insertion/deletion than AVL. AVL is fast in search than red black. but in data structures, usually insertion/deletion happens lot more than searches, so it makes more sense to use red black tree. In case of databases, search is more common, so AVL tree makes sense.
 * In BST(binary search tree), we need to compare keys to decide where to put them. We compare the keys from their hash values.
 * Duplicates are allowed in red black trees. If hash values of 2 keys are same, then we need to decide whether to put it in right or left subtree,so we check if the keys extend comparable interface so that they can be compared.
 * If keys hashCode are same and doesn't extend comparable interface or compareTo returns 0, then system.identityhashcode is compared which is guaranteed to be different for 2 objects.
 * So in case BST is used, tree insertion is always O(logn).
 * While retrieval/search, if hashcode are different or if keys are comparable and return non zero values, search is O(logn).
 * While retrieval/search, if hashCode are same and keys are not comparable or compare returns 0, then we iterate over entire list to find the key. Thus search is O(logn)
 * Thus even after implementing tree, time complexity can be O(n) if keys hash are same and keys are not comparable.
 * When we are using red black tree instead of linked list, we dont need very heavy rehash function [As search, insert is O(logn)]. So rehash function can be reduced to h ^ (h >>> 16)
 * 
 * Its a possibility that when the capacity is 16 and load factor 0.75, then after putting 12 elements, only 2 index of array are filled and rest are empty (can be because of bit masking or same hash).
 * Now when adding threshold (13th > 16*0.75) element, it will still create new array with double size and recalculate hash. This is done so that elements can uniformly distribute (which was not possible earlier due to bit masking in small capacity)
 * 
 */
public class HashSet {
	
	private static final int DEFAULT_INITIAL_CAPACITY = 16;
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;
	
	Node[] data;
	int size = 0;
	float loadFactor;
	
	public HashSet() {
		this.data = new Node[DEFAULT_INITIAL_CAPACITY];
		this.loadFactor = DEFAULT_LOAD_FACTOR;
	}

	// O(1) if good hash function. O(n) if bad hash function.
	public boolean contains(Character ch) {
		int hashCode = hash(ch.hashCode());
		int index = indexFor(hashCode, data.length);
		
		return getEntry(ch, index, data) != null;
	}
	
	// O(1) if good hash function. O(n) if bad hash function.
	private Node getEntry(Character ch, int index, Node[] data) {
		// As index can be same for multiple keys(because of same hashCode or because of bit masking. Find the exact key.)
		for (Node entry = data[index]; entry != null; entry = entry.next) {
			if (entry.key == ch || entry.key.equals(ch)) {
				return entry;
			}
		}
		
		return null;
	}

	// amortized O(1) if good hash function. O(n) if bad hash function.
	// amortized because of resize if required.
	public boolean add(Character ch) {
		resizeIfRequired();
		
		int hashCode = hash(ch.hashCode());
		int index = indexFor(hashCode, data.length);
		
		return add(ch, hashCode, index, data);
	}

	private boolean add(Character ch, int hashCode, int index, Node[] data) {
		Node entry = getEntry(ch, index, data);
		
		if (entry != null) {
			return false;
		}
		
		entry = new Node(ch, hashCode, data[index]);
		data[index] = entry;
		size++;
		
		return true;
	}

	// Here we are rehashing the hash. Higher bits weightage is given to lower bits so that on bit masking, when higher bits are masked, we still get less collisions.
	private int hash(int h) {
		// >>> is logical shift while >> is arithmetic shift. logical shift doesn't care about sign while shifting.
		h = h ^ (h >>> 20) ^ (h >>> 12);
		h = h ^ (h >>> 7) ^ (h >>> 4);
		return h;
	}

	// As we are using capacity as power of 2, we can use `& (capcity-1)` instead of `% capacity`
	private int indexFor(int hashCode, int length) {
		return hashCode & data.length - 1;
	}

	private void resizeIfRequired() {
		if (size >= data.length*loadFactor) {
			// Here load factor is 2. Not making it 1.5 as in arrayList as its an expensive operation in compare to arrayList.
			int newLength = Math.min(2*data.length, Integer.MAX_VALUE);
			Node[] newData = new Node[newLength];
			transfer(newData);
			data = newData;
		}
	}
	
	private void transfer(Node[] newData) {
		for (Node entry : data){
			while(entry != null) {
				int index = indexFor(entry.hash, newData.length);
				add(entry.key, entry.hash, index, newData);
				entry = entry.next;
			}
		}
	}

	private static class Node {
		int hash; // stored so that no need to calculate hash again while copying in new array.
		final Character key;
		final Node next;
		
		public Node(Character key, int hash, Node next) {
			this.key = key;
			this.hash = hash;
			this.next = next;
		}
	}

}
