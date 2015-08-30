package dataStructures;
import dataStructures.exceptions.NoSuchElementException;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 */
public class HashTableIterator<K,V> implements Iterator<Entry<K,V>> {

    private static final long serialVersionUID = 0L;
    
    //The dictionary to iterate
    protected Dictionary<K,V>[] dictionary;
    
    //Iterator for the buckets.
    protected Iterator<Entry<K,V>> list;
    
    //Position where it stops iterating over the dictionary and starts to iterate over the bucket.
    protected int current;
    
    
    public HashTableIterator(Dictionary<K,V>[] dictionary){
        this.dictionary = dictionary;
        this.rewind();
    }
    
    // Checks if there are elements to iterate.
    public boolean hasNext() {
    	// if the list contains an iterator, return its own "hasNext()" function.
        if (list != null)
            return list.hasNext();
        return false;
    }

    // Returns the next element in the iteration.
    public Entry<K,V> next() throws NoSuchElementException {
        if (!this.hasNext())
            throw new NoSuchElementException();
        
        // gets the next element on the iterator of the list
        Entry<K,V> tmp = list.next();
        // if the iterator of the list doesn't have a next element, search for another bucket with elements to iterate.
        if (!this.hasNext())
            this.searchNext();
        
        return tmp;
    }
    
    // Searches for the next occupied bucket in the hashtable and gets its iterator.
    private void searchNext() {
        while (++current < dictionary.length) {
        	// if the bucket isn't empty, gets its iterator.
            if (!dictionary[current].isEmpty()) {
                list = dictionary[current].iterator();
                return;
            }
        }
    }

    // Restarts the iteration and gets the iterator from the first bucket in the hash table.
    public void rewind() {
    	this.current = 0;
        while (current < dictionary.length) {
        	// if the bucket isn't empty, gets its iterator.
            if (!dictionary[current].isEmpty()) {
                list = dictionary[current].iterator();
                return;
            }
            current++;
        }
    }

}

