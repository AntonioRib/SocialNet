package dataStructures;
import dataStructures.exceptions.EmptyDictionaryException;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 */
public class OrderedDoublyLL<K extends Comparable<K>,V> extends DoublyLinkedList<Entry<K,V>> implements OrderedDictionary<K,V> {

	private static final long serialVersionUID = 0L;

	// Returns the tail. The last element on list.
	public Entry<K,V> maxEntry() throws EmptyDictionaryException {
		if (this.isEmpty())
			throw new EmptyDictionaryException();
		return this.getLast();
	}

	// Returns the head. The first element on list.
	public Entry<K,V> minEntry() throws EmptyDictionaryException {
		if (this.isEmpty())
			throw new EmptyDictionaryException();
		return this.getFirst();
	}

	// Goes through the list, checking if the key exists. If it does, returns its node element.
	private Entry<K,V> findEntry(K key){
		DListNode<Entry<K, V>> temp = head;
		
		while (temp != null && temp.getElement().getKey().compareTo(key) != 0)
			temp = temp.getNext();
		
		if(temp != null)
			return temp.getElement();
		return null;
	}
	
	// Searches for an entry and returns its value.
	public V find(K key) {
		Entry<K,V> temp = this.findEntry(key);
		if (temp != null)
			return temp.getValue();
		return null;
	}

	// Inserts the specified element at his position on the list
	// If theres already a key there, saves his value to return and removes it.
	// It the list is empty add corresponds to addFirst.
    // If the comparison between new element and the minimum returns less than 0 add corresponds to addFirst.  
	// If the comparison between new element and the minimum returns more than 0 add corresponds to addLast. 
	public V insert(K key, V value) {
	
		Entry<K,V> newEntry = new EntryClass<K,V>(key, value);
		// removes the old element if it exists.
		V oldValue = this.remove(key);
		
		// compares with the lower entry, if new entry is lower, inserts at head.
		if (this.isEmpty() || key.compareTo(this.minEntry().getKey()) < 0)
			this.addFirst(newEntry);
		// compares with the higher entry, if new entry is higher, inserts at tail.
		else if (key.compareTo(this.maxEntry().getKey()) > 0)
			this.addLast(newEntry);
		// couldn't insert at head or tail, try somewhere else.
		else
			this.addMiddle(newEntry);
		// return the removed element value if it was found.
		if (oldValue != null)
			return oldValue;
		return null;
	}
	
	// Inserts the specified element at hist position in the list
	// Inserts on positions: 1, ..., size()-1. 
	private void addMiddle(Entry<K, V> newEntry) {
		DListNode<Entry<K,V>> temp = head;
		DListNode<Entry<K,V>> prevTemp = null;
		
		while (newEntry.getKey().compareTo(temp.getElement().getKey()) > 0)
			temp = temp.getNext();
		
		prevTemp = temp.getPrevious();
		// creates the new node and connects the previous and the next node to them.
		DListNode<Entry<K,V>> newNode = new DListNode<Entry<K, V>>(newEntry, prevTemp, temp);
		prevTemp.setNext(newNode);
		temp.setPrevious(newNode);
	}

	// Removes and returns the element with the specified key on list.
	public V remove(K key) {
		// searches for an old entry with the same key.
		Entry<K,V> oldEntry = this.findEntry(key);
		// if it exists, remove it.
		if(this.remove(oldEntry))
			return oldEntry.getValue();
		return null;
	}
	
}