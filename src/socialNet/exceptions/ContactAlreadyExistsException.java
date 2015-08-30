package socialNet.exceptions;

import manager.Messages;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 */
public class ContactAlreadyExistsException extends SocialNetException {

	private static final long serialVersionUID = 0L;

    public ContactAlreadyExistsException() {
        super(Messages.HAS_CONTACT + Messages.PRINT_END);
    }
	
}
