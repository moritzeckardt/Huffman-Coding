import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PriorityQueue<E> implements PQInterface<E> {
	// Inner class
	protected class Entry {
		E value;
		int prio;
	}

	// Properties
	protected ArrayList<Entry> elements;

	// Constructor
	public PriorityQueue() {
		elements = new ArrayList<>();
	}

	// Methods
	// TODO 1d)
	@Override
	public int size() {
		return elements.size();
	}

	@Override
	public boolean isEmpty() {
		return elements.isEmpty();
	}

	@Override
	public void add(E element, int priority) {
		// Create new entry
		Entry e = new Entry();
		e.value = element;
		e.prio = priority;

		// Add element to end and let it bubble up
		elements.add(e);
		bubbleUp(elements.size() - 1);
	}

	@Override
	public E peek() throws NoSuchElementException {
		// Check if max heap is empty
		if (isEmpty()) {
			throw new NoSuchElementException("Max heap is empty.");
		}

		// Return value of entry with highest prio
		Entry entryWithHighestPrio = elements.get(0);
		return entryWithHighestPrio.value;
	}

	@Override
	public E remove() throws NoSuchElementException {
		// Check if max heap is empty
		if (isEmpty()) {
			throw new NoSuchElementException("Max heap is empty.");
		}

		// Get entry with highest pro and last entry
		Entry entryWithHighestPrio = elements.get(0);
		Entry lastEntry = elements.get(elements.size() - 1);

		// Remove last entry and set it at index 0, restructure max heap
		elements.remove(lastEntry);
		if (elements.size() > 0) {
			elements.set(0, lastEntry);
			trickleDown(0);
		}

		// Return value of the entry with highest prio
		return entryWithHighestPrio.value;
	}

	private void bubbleUp(int idx) {
		// TODO 1b)
		// Get current and parent entry
		if (idx == 0) return;

		int parentIdx = parent(idx);
		Entry parent = elements.get(parentIdx);
		Entry current = elements.get(idx);

		if (current.prio > parent.prio) {
			// Swap these bad boys
			elements.set(parentIdx, current);
			elements.set(idx, parent);

			bubbleUp(parentIdx);
		}
	}

	private void trickleDown(int idx) {
		// TODO 1c)
		// Get current and parent entry
		int leftChildIdx = leftChild(idx);
		int rightChildIdx = rightChild(idx);

		Entry current = elements.get(idx);

		// Current element has no children
		if (leftChildIdx >= size()) return;

		Entry leftChild = elements.get(leftChildIdx);

		// Only left child is present
		if (rightChildIdx >= size()) {

			if (current.prio < leftChild.prio) {

				elements.set(leftChildIdx, current);
				elements.set(idx, leftChild);
			}
		} else {

			Entry rightChild = elements.get(rightChildIdx);

			if (current.prio < leftChild.prio || current.prio < rightChild.prio) {
				// Check which child is larger and swap current with larger child
				if (rightChild.prio > leftChild.prio) {
					elements.set(rightChildIdx, current);
					elements.set(idx, rightChild);
					trickleDown(rightChildIdx);
				} else {
					elements.set(leftChildIdx, current);
					elements.set(idx, leftChild);
					trickleDown(leftChildIdx);
				}
			}
		}
	}

	protected static int parent(int idx) {
		// TODO 1a)
		// Return parent
		return (idx + 1) / 2 - 1;
	}

	protected static int leftChild(int idx) {
		// TODO 1a)
		// Return left child
		return (idx + 1) * 2 - 1;
	}

	protected static int rightChild(int idx) {
		// TODO 1a)
		// Return right child
		return (idx + 1) * 2;
	}
}
