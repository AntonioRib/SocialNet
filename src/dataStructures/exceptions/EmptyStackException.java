package dataStructures.exceptions;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
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

