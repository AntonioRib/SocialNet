package dataStructures;
import dataStructures.exceptions.*;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 */
public class SinglyLinkedList<E> implements List<E> {

	private static final long serialVersionUID = 0L;
	
	 // Node at the head of the list.
    protected SListNode<E> head;
	
    // Number of elements in the list.
    protected int currentSize;
	
	public SinglyLinkedList(){
		head = null;
		currentSize = 0;
	}
	
	 // Returns true iff the list contains no elements.
	public boolean isEmpty() {
		return currentSize ==0;
	}
	
	// Returns the number of elements in the list.
	public int size() {
		return currentSize;
	}
	
    // Returns an iterator of the elements in the list (in proper sequence).
	public Iterator<E> iterator() {
		return new SinglyLLIterator<E>(head);
	}
	
    // Returns the element at the specified position in the list.
    // Range of valid positions: 0, ..., size()-1.
    // If the specified position is 0, get corresponds to getFirst. 
    // If the specified position is size()-1, get corresponds to getLast.  
	public E get(int position) throws InvalidPositionException {
		if (position < 0 || position >= currentSize)
			throw new InvalidPositionException();
		return this.getNode(position).getElement();
	}

	 // Returns the first element of the list.
	public E getFirst() throws EmptyListException {
		if (this.isEmpty())
			throw new EmptyListException();
		return head.getElement();
	}

    // Returns the last element of the list.
	public E getLast() throws EmptyListException {
		if(this.isEmpty())
			throw new EmptyListException();
		return this.get(currentSize-1);
	}


    // Returns the node at the specified position in the list.
    // Pre-condition: position ranges from 0 to currentSize-1.
	protected SListNode<E> getNode(int position){
		SListNode<E> node = head;
		if (position <= ((currentSize-1)/2)) {
			for(int i = 0; i<position; i++)
				node= node.getNext();
		} else {
			node = this.getNode(currentSize-1/2);
			for(int i = ((currentSize-1)/2); i<position; i++)
				node= node.getNext();
		}
		return node;
	}
	
    // Returns the position of the first occurrence of the specified element
    // in the list, if the list contains the element.
    // Otherwise, returns -1.
	public int find(E element) {
		SListNode<E> node = head;
		int position = 0;
		while (node!=null && !node.getElement().equals(element)) {
			node = node.getNext();
			position++;
		}
		if (node == null)
			return -1;
		else
			return position;
	}
	
    // Inserts the specified element at the specified position in the list.
    // Range of valid positions: 0, ..., size(). 
    // If the specified position is 0, add corresponds to addFirst.  
    // If the specified position is size(), add corresponds to addLast.
	public void add(int position, E element) throws InvalidPositionException {
		if (position < 0 || position > currentSize)
			throw new InvalidPositionException();
		else if (position == 0)
				this.addFirst(element);
			else if(position==(currentSize))
					this.addLast(element);
				else
					this.addMiddle(position, element);
	}

    // Inserts the specified element at the specified position in the list.
    // Pre-condition: position ranges from 1 to currentSize-1.
	protected void addMiddle(int position, E element) {
		SListNode<E> prevNode = this.getNode(position-1);
		SListNode<E> nextNode = prevNode.getNext();
		SListNode<E> newNode = new SListNode<E>(element, nextNode);
		prevNode.setNext(newNode);
		currentSize++;
	}

    // Inserts the specified element at the first position in the list.
	public void addFirst(E element) {
		SListNode<E> newNode = new SListNode<E>(element, head);
		head = newNode;
		currentSize++;
	}

    // Inserts the specified element at the last position in the list.
	public void addLast(E element) {
		SListNode<E> newNode = new SListNode<E>(element, null);
		SListNode<E> tail = this.getNode(currentSize-1);
		if(this.isEmpty())
			head = newNode;
		else
			tail.setNext(newNode);
		currentSize++;
	}
	
    // Removes and returns the element at the specified position in the list.
    // Range of valid positions: 0, ..., size()-1.
    // If the specified position is 0, remove corresponds to removeFirst. 
    // If the specified position is size()-1, remove corresponds to removeLast.
	public E remove(int position) throws InvalidPositionException {
		if(position < 0 || position >= currentSize)
			throw new InvalidPositionException();
		else if(position==0)
				return this.removeFirst();
			else if(position == currentSize-1)
					return this.removeLast();
				else {
					SListNode<E> nodeToRemove = this.getNode(position);
					this.removeMiddleNode(nodeToRemove);
					return nodeToRemove.getElement();
				}
	}

    // Removes the first occurrence of the specified element from the list
    // and returns true, if the list contains the element.
    // Otherwise, returns false.
	public boolean remove(E element) {
		SListNode<E> node = this.findNode(element);
		if(node==null)
			return false;
		else {
			if(node == head)
					this.removeFirst();
				else if (node == this.getNode(currentSize-1))
						this.removeLastNode();
					else
						this.removeMiddleNode(node);
		}
		return true;
	}
	
    // Returns the node with the first occurrence of the specified element
    // in the list, if the list contains the element.
    // Otherwise, returns null.
	protected SListNode<E> findNode(E element){
		SListNode<E> node = head;
		while(node!=null && !node.getElement().equals(element))
			node = node.getNext();
		return node;
	}

    // Removes and returns the element at the first position in the list.
	public E removeFirst() throws EmptyListException {
		if(this.isEmpty())
			throw new EmptyListException();
		E element = head.getElement();
		this.removeFirstNode();
		return element;
	}
	
    // Removes the first node in the list.
    // Pre-condition: the list is not empty.
	protected void removeFirstNode(){
		head = head.getNext();
		currentSize--;
	}

    // Removes and returns the element at the last position in the list.
	public E removeLast() throws EmptyListException {
		if (this.isEmpty())
			throw new EmptyListException();
		E element = this.getLast();
		this.removeLastNode();
		return element;
	}
	
    // Removes the last node in the list.
    // Pre-condition: the list is not empty.
	protected void removeLastNode(){
		SListNode<E> tail = this.getNode(currentSize-2);
		if(tail == null)
			head = null;
		else
			tail.setNext(null);
		currentSize--;
	}
	
    // Removes the specified node from the list.
    // Pre-condition: the node is neither the head nor the tail of the list.
	protected void removeMiddleNode(SListNode<E> node){
		SListNode<E> nextNode = node.getNext();
		SListNode<E> prevNode = this.getNode(this.find(node.getElement())-1);
		prevNode.setNext(nextNode);
		currentSize--;
	}
	
}
