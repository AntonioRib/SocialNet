package dataStructures;
import java.io.Serializable;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 */
class SListNode<E> implements Serializable {

	private static final long serialVersionUID = 0L;

	// Element stored in the node.
	private E element;
	
	// (Pointer to) the next node.
	private SListNode<E> next;
	
	public SListNode(E theElement, SListNode<E> theNext){
		element = theElement;
		next = theNext;
	}
	
	
	public SListNode (E theElement){
		this(theElement, null);
	}
	
	public E getElement(){
		return element;
	}
	
	public SListNode<E> getNext(){
		return next;
	}
	
	public void setElement (E newElement){
		this.element = newElement;
	}
	
	public void setNext(SListNode<E> newNext){
		next = newNext;
	}
}

