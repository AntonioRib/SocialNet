package dataStructures;

import java.io.Serializable;
/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 */
public interface Entry<K,V> extends Serializable
{

    // Returns the key in the entry.
    K getKey( );

    // Returns the value in the entry.
    V getValue( );

}
