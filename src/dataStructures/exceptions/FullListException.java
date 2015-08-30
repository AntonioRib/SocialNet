package dataStructures.exceptions;

/**
 * @author Antonio Ribeiro, n�41674 / Francisco Godinho, n�41611
 */
public class FullListException extends RuntimeException
{

    static final long serialVersionUID = 0L;


    public FullListException( )
    {
        super();
    }

    public FullListException( String message )
    {
        super(message);
    }

}

