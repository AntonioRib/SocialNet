package socialNet.exceptions;

import manager.Messages;

/**
 * @author Antonio Ribeiro, n�41674 / Francisco Godinho, n�41611
 */
public class ContactAlreadyExistsException extends SocialNetException {

	private static final long serialVersionUID = 0L;

    public ContactAlreadyExistsException() {
        super(Messages.HAS_CONTACT + Messages.PRINT_END);
    }
	
}
