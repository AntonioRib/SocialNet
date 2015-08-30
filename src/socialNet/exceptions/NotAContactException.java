package socialNet.exceptions;

import manager.Messages;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 */
public class NotAContactException extends SocialNetException {

	private static final long serialVersionUID = 0L;
	
    public NotAContactException() {
        super(Messages.HASNT_CONTACT + Messages.PRINT_END);
    }

}
