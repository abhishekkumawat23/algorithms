package objectOrientedDesign.hashTable;

public class LinkedListNode<K, V> {

	private K key;
	private V value;
	private LinkedListNode<K,V> next;
	private LinkedListNode<K, V> previous;
	
	public LinkedListNode(K key, V value, LinkedListNode<K, V> previous) {
		this.key = key;
		this.value = value;
		this.previous = previous;
	}
	
	public LinkedListNode<K, V> getNext() {
		return next;
	}
	
	public void setNext(LinkedListNode<K, V> next) {
		this.next = next;
	}
	
	public LinkedListNode<K, V> getPrevious(){
		return previous;
	}
	
	public void setPrevious(LinkedListNode<K, V> previous) {
		this.previous = previous;
	}
	
	public K getKey() {
		return key;
	}
	
	public V getValue() {
		return value;
	}
	
	public void setValue(V value) {
		this.value = value;
	}
}
