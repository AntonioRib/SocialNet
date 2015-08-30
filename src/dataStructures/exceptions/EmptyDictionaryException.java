package dataStructures.exceptions;

/**
 * @author Antonio Ribeiro, n�41674 / Francisco Godinho, n�41611
 */
public class EmptyDictionaryException extends RuntimeException
{

    static final long serialVersionUID = 0L;


    public EmptyDictionaryException( )
    {
        super();
    }

    public EmptyDictionaryException( String message )
    {
        super(message);
    }

}

