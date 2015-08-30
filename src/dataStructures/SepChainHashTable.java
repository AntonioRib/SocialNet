package dataStructures;  

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 */
public class SepChainHashTable<K extends Comparable<K>, V> 
    extends HashTable<K,V> 
{ 

    static final long serialVersionUID = 0L;


    // The array of dictionaries.
    protected Dictionary<K,V>[] table;

    //Contructor makes an array of dictionaries with a OrderedDoublyLL treating colisions.
	@SuppressWarnings("unchecked")
	public SepChainHashTable( int capacity )
    {
        int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
        table = (Dictionary<K,V>[]) new Dictionary[arraySize];
        for ( int i = 0; i < arraySize; i++ )
            table[i] = new OrderedDoublyLL<K,V>();
        maxSize = capacity;
        currentSize = 0;
    }                                      

	//Starts object with a default size
    public SepChainHashTable( )
    {
        this(DEFAULT_CAPACITY);
    }                                                                


    // Returns the hash value of the specified key.
    protected int hash( K key )
    {
        return Math.abs( key.hashCode() ) % table.length;
    }


    // If there is an entry in the dictionary whose key is the specified key,
    // returns its value; otherwise, returns null.
    public V find( K key )
    {
        return table[ this.hash(key) ].find(key);
    }


    // If there is an entry in the dictionary whose key is the specified key,
    // replaces its value by the specified value and returns the old value;
    // otherwise, inserts the entry (key, value) and returns null.
    public V insert( K key, V value )
    {
        if ( this.isFull() )
            this.rehash();
        
        if (this.find(key) == null)
        	currentSize++;
        
        return table[this.hash(key)].insert(key, value);
    }

	// If there is an entry in the dictionary whose key is the specified key,
    // removes it from the dictionary and returns its value;
    // otherwise, returns null.
    public V remove( K key )
    {
    	currentSize--;
    	return table[this.hash(key)].remove(key);
    }


    // Returns an iterator of the entries in the dictionary.
    public Iterator<Entry<K,V>> iterator( )
    {
		return new HashTableIterator<K,V>(table);
    } 
    
    // Rebuilds the Dictionary by doubling its size.
    // Creates an iterator with all the values, restarts the array doubling its size and re-adds all the values.
    @SuppressWarnings("unchecked")
	private void rehash() 
    {
    	// creates an iterator in order to contain all the elements from the old table.
    	Iterator<Entry<K, V>> it = this.iterator();
    	
    	int capacity = 2*maxSize;
        int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
        Dictionary<K,V>[] tmp = (Dictionary<K,V>[]) new Dictionary[arraySize];
        for ( int i = 0; i < arraySize; i++ )
            tmp[i] = new OrderedDoublyLL<K,V>();
        
        while (it.hasNext()) {
        	Entry<K, V> entry = it.next();
        	// gets a new hashcode for each element according to the new table length.
        	int newHash = Math.abs( entry.getKey().hashCode() ) % tmp.length;
        	tmp[newHash].insert(entry.getKey(), entry.getValue());
        }
        table = tmp;
	}
}
