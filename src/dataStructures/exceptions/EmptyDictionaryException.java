package dataStructures.exceptions;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
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

