package dataStructures;

import java.io.Serializable;
/**
 * @author Antonio Ribeiro, n�41674 / Francisco Godinho, n�41611
 */
public interface Entry<K,V> extends Serializable
{

    // Returns the key in the entry.
    K getKey( );

    // Returns the value in the entry.
    V getValue( );

}
