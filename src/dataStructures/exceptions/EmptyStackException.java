package dataStructures.exceptions;

/**
 * @author Antonio Ribeiro, n�41674 / Francisco Godinho, n�41611
 */
public class EmptyStackException extends RuntimeException
{

    static final long serialVersionUID = 0L;


    public EmptyStackException( )
    {
        super();
    }

    public EmptyStackException( String message )
    {
        super(message);
    }

}

