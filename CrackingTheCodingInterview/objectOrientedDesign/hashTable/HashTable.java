package objectOrientedDesign.hashTable;

import java.util.ArrayList;
import java.util.LinkedList;

public class HashTable<K, V> {

	private ArrayList<LinkedListNode<K, V>> data;
	private int size;
	
	public HashTable() {
		this.data = new ArrayList<LinkedListNode<K, V>>();
	}
	
	public void put(K key, V value) {
		int index = getIndexForKey(key);
		 
		// Key already exists
		LinkedListNode<K, V> node = getNodeForKey(index, key);
		if (node != null) {
			node.setValue(value);
			return;
		}
		
		// Ensure capacity
		ensureCapacity();
		
		// Key doesn't exists
		node = data.get(index);
		LinkedListNode<K, V> parent = findParentNode(node, key);
		node = new LinkedListNode<K, V>(key, value, parent);
		addNode(index, node);
	}

	public void remove(K key) {
		int index = getIndexForKey(key);

		LinkedListNode<K, V> node = getNodeForKey(index, key);
		if (node != null) {
			removeNode(index, node);
		}
	}
	
	public V get(K key) {
		int index = getIndexForKey(key);

		LinkedListNode<K, V> node = getNodeForKey(index, key);
		return (node != null) ? node.getValue() : null;
	}
	
	public int size() {
		return size;
	}
	
	public boolean containsKey(K key) {
		int index = getIndexForKey(key);

		LinkedListNode<K, V> node = getNodeForKey(index, key);
		return node != null;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	private void ensureCapacity() {
		// Note: If capacity is increased then, all the hashing will get corrupted as data size changes. So we need to recalculate all hash and update nodes.
		// Note: Because of this problem, its wise to initiate the hashTable initial data arraylist with some nice capacity. 
		if (size + 1 > data.size()) {
			// rehashing required
			ArrayList<LinkedListNode<K, V>> oldData = data;
			data = new ArrayList<LinkedListNode<K, V>>();
			data.ensureCapacity(size+1);
			for (LinkedListNode<K, V> node: oldData) {
				LinkedListNode<K, V> current = node;
				while (current != null) {
					put(node.getKey(), node.getValue());
					current = current.getNext();
				}
			}
		}
	}
	
	private int getIndexForKey(K key) {
		// If hashCode is bad, then we will get lot of collisions. So we can do double hashing.
		return Math.abs(key.hashCode() % data.size());
	}

	private LinkedListNode<K, V> getNodeForKey(int index, K key) {
		LinkedListNode<K, V> node = data.get(index);
		while (node != null) {
			if (node.getKey().equals(key)) {
				return node;
			}
			node = node.getNext();
		}
		return node;
	}

	private LinkedListNode<K, V> findParentNode(LinkedListNode<K, V> node, K key) {
		if (node == null) {
			return null;
		}
		
		LinkedListNode<K, V> parent = null;
		while (node != null) {
			if (node.getKey().equals(key)) {
				return parent;
			}
			parent = node;
			node = node.getNext();
		}
		return parent;
	}

	private void addNode(int index, LinkedListNode<K, V> node) {
		if (node == null) {
			return;
		}
		
		if (node.getPrevious() != null) {
			node.getPrevious().setNext(node);
		}
		else {
			data.set(index, node);
		}
		
		size++;		
	}
	
	private void removeNode(int index, LinkedListNode<K, V> node) {
		if (node == null) {
			return;
		}
		
		if (node.getPrevious() != null) {
			node.getPrevious().setNext(node.getNext());
			size--;
		}
		else {
			data.set(index, node.getNext());
		}
		size--;
	}
	
}
