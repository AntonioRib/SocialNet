package dataStructures.exceptions;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 */
public class FullDictionaryException extends RuntimeException
{

    static final long serialVersionUID = 0L;


    public FullDictionaryException( )
    {
        super();
    }

    public FullDictionaryException( String message )
    {
        super(message);
    }

}

