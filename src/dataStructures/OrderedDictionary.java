package dataStructures;  

import dataStructures.exceptions.EmptyDictionaryException;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 */
public interface OrderedDictionary<K extends Comparable<K>, V> 
    extends Dictionary<K,V>
{                                                                   

    // Returns the entry with the smallest key in the dictionary.
    Entry<K,V> minEntry( ) throws EmptyDictionaryException;

    // Returns the entry with the largest key in the dictionary.
    Entry<K,V> maxEntry( ) throws EmptyDictionaryException;

    // Returns an iterator of the entries in the dictionary 
    // which preserves the key order relation.
    Iterator<Entry<K,V>> iterator( ); 

} 

