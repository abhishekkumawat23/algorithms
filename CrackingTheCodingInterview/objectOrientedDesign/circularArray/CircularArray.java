package objectOrientedDesign.circularArray;

import java.util.Iterator;

public class CircularArray<E> implements Iterable<E> {

	private E[] data;
	private int head = 0;
	
	public CircularArray(int length) {
		// If we want we can make dynamic array as well.
		data = (E[])(new Object[length]);
	}
	
	public E get(int i) {
		if (i < 0 || i >= data.length) {
			throw new IndexOutOfBoundsException("...");
		}
		return data[convert(i)];
	}
	
	public void set(int i, E e) {
		if (i < 0 || i >= data.length) {
			throw new IndexOutOfBoundsException("...");
		}
		data[convert(i)] = e;
	}
	
	public void rotate(int by) {
		head = convert(by);
	}
	
	public int convert(int i) {
		int index = (i+head) % data.length;
		return index;
	}
	
	public int size() {
		return data.length;
	}

	@Override
	public Iterator<E> iterator() {
		return new CircularArrayIterator<E>(this);
	}
}
