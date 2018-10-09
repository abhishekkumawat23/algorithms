package objectOrientedDesign.circularArray;

import java.util.Iterator;

public class CircularArrayIterator<E> implements Iterator<E> {

	private CircularArray<E> circularArray;
	private int current  = -1;
	
	public CircularArrayIterator(CircularArray<E> circularArray) {
		this.circularArray = circularArray;
	}

	@Override
	public boolean hasNext() {
		return current < circularArray.size();
	}

	@Override
	public E next() {
		if (!hasNext()) {
			return null;
		}
		current = current++;
		return circularArray.get(current);
	}

	@Override
	public void remove() {
		// We can support this if we support dynamic array.
		throw new UnsupportedOperationException("...");
	}
}
