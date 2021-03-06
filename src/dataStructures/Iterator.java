package dataStructures;
import dataStructures.exceptions.NoSuchElementException;
import java.io.Serializable;

/**
 * @author Antonio Ribeiro, n�41674 / Francisco Godinho, n�41611
 */
public interface Iterator<E> extends Serializable
{

    // Returns true iff the iteration has more elements. 
    // In other words, returns true if next would return an element 
    // rather than throwing an exception.
    boolean hasNext( );

    // Returns the next element in the iteration.
    E next( ) throws NoSuchElementException;

    // Restarts the iteration.
    // After rewind, if the iteration is not empty,
    // next will return the first element in the iteration.
    void rewind( );

}
