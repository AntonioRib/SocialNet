package dataStructures.exceptions;

/**
 * @author Antonio Ribeiro, n�41674 / Francisco Godinho, n�41611
 */
public class EmptyListException extends RuntimeException
{

    static final long serialVersionUID = 0L;


    public EmptyListException( )
    {
        super();
    }

    public EmptyListException( String message )
    {
        super(message);
    }

}

