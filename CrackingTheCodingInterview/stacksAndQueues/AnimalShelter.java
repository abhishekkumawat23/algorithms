package stacksAndQueues;

import java.util.LinkedList;

/**
 * Question:
 * An animal shelter holds only dogs and cats and operates on `first in first out` basis.
 * People must adopt either the oldest of all animals or oldest of either animal type.
 * Create data structure to maintain this system and implement operations like enqueue, dequeueAny, dequeueDog, dequeueCat.
 * You may use built-in LinkedList data structure.
 */
public class AnimalShelter {

	private LinkedList<Dog> dogs = new LinkedList<Dog>();
	private LinkedList<Cat> cats = new LinkedList<Cat>();
	private int order;
	
	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * enqueue(animal)
	 *   if animal is Dog
	 *     addLast(dogs, animal)
	 *   else if animal is cat
	 *     addLast(cats, animal)
	 */
	public void enqueue(Animal animal) {
		if (animal instanceof Dog) {
			dogs.addLast((Dog)animal);
		}
		else if (animal instanceof Cat) {
			cats.addLast((Cat)animal);
		}
		else {
			throw new IllegalArgumentException();
		}
		animal.order = order++;
	}
	
	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * dequeueAny():
	 *   if size[dogs] = 0
	 *     return removeFirst[cats]
	 *   if size[cats] = 0
	 *     return removeFirst[dogs]
	 *   if order[last[dogs]] < order[last[cats]]
	 *     return removeFirst[dogs]
	 *   else
	 *     return removeFirst[cats]
	 */
	public Animal dequeueAny() {
		if (dogs.size() == 0) {
			return cats.removeFirst();
		}
		if (cats.size() == 0) {
			return dogs.removeFirst();
		}
		
		if (dogs.getLast().order < cats.getLast().order) {
			return dogs.removeFirst();
		}
		else {
			return cats.removeFirst();
		}
	}
	
	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * dequeueDog():
	 *   return removeFirst[dogs]
	 */
	public Dog dequeueDog() {
		return dogs.removeFirst();
	}
	
	/**
	 * Time: O(1); Space: O(1)
	 * 
	 * Pseudo code:
	 * dequeueCat():
	 *   return removeFirst[cats]
	 */
	public Cat dequeueCat() {
		return cats.removeFirst();
	}
	
	private class Animal{
		public int order;
	}
	
	private class Dog extends Animal {
		
	}
	
	private class Cat extends Animal{
		
	}
}
