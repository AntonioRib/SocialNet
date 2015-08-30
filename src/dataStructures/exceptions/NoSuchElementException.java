package dataStructures.exceptions;

/**
 * @author Antonio Ribeiro, n�41674 / Francisco Godinho, n�41611
 */
public class NoSuchElementException extends RuntimeException
{

    static final long serialVersionUID = 0L;


    public NoSuchElementException( )
    {
        super();
    }

    public NoSuchElementException( String message )
    {
        super(message);
    }

}

