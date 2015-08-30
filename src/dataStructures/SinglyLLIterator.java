package dataStructures;
import dataStructures.exceptions.*;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 */
public class SinglyLLIterator<E> implements Iterator<E> {
	
	private static final long serialVersionUID = 0L;
	
    // Node with the first element in the iteration.
	protected SListNode<E> firstNode;
	
    // Node with the next element in the iteration.
	protected SListNode<E> nextToReturn;
	
	public SinglyLLIterator(SListNode<E> head){
		firstNode = head;
		this.rewind();
	}

    // Returns true iff the iteration has more elements. 
    // In other words, returns true if next would return an element 
    // rather than throwing an exception.
	public boolean hasNext() {
		return nextToReturn != null;
	}

    // Returns the next element in the iteration.
	public E next() throws NoSuchElementException {
		if(!this.hasNext())
			throw new NoSuchElementException();
		E element = nextToReturn.getElement();
		nextToReturn = nextToReturn.getNext();
		return element;
	}

    // Restarts the iteration.
    // After rewind, if the iteration is not empty,
    // next will return the first element in the iteration.
	public void rewind() {
		nextToReturn = firstNode;
	}

}
