package dataStructures.exceptions;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 */
public class InvalidPositionException extends RuntimeException
{

    static final long serialVersionUID = 0L;


    public InvalidPositionException( )
    {
        super();
    }

    public InvalidPositionException( String message )
    {
        super(message);
    }

}

