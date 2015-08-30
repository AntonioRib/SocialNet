package dataStructures;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 */
public class EntryClass<K, V> implements Entry<K, V> {

	private static final long serialVersionUID = 0L;
	
	private K key;
	private V value;
	
	
	public EntryClass(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	// Returns the key in the entry.
	public K getKey() {
		return key;
	}
	
	 // Returns the value in the entry.
	public V getValue() {
		return value;
	}
	
	// Sets the key of the entry.
	public void setKey(K key) {
		this.key = key;
	}
	
	// Sets the value of the entry.
	public void setValue(V value) {
		this.value = value;
	}


}
