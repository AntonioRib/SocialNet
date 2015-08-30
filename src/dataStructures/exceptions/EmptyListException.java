package dataStructures.exceptions;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
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

