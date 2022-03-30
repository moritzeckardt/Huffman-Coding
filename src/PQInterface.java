import java.util.NoSuchElementException;

public interface PQInterface<E> {

	/**
	 * @return the number of elements in the priority queue.
	 */
	public int size();

	/**
	 * @return true if the priority queue contains no elements.
	 */
	public boolean isEmpty();

	/**
	 * Adds element with given priority to the priority queue.
	 * Should have O(log n) runtime complexity.
	 * 
	 * @param element
	 * @param priority
	 */
	public void add(E element, int priority);
	
	/**
	 * Returns the element with the highest priority, but does not remove it.
	 * Should have O(1) runtime complexity.
	 * 
	 * @return the element with the highest priority
	 * @throws NoSuchElementException if the queue is empty
	 */
	public E peek();

	/**
	 * Removes the element with the highest priority and returns it.
	 * Should have O(log n) runtime complexity.
	 * 
	 * @return the element with the highest priority
	 * @throws NoSuchElementException if the queue is empty
	 */
	public E remove();
}
