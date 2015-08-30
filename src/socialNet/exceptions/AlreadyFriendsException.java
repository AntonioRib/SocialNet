package socialNet.exceptions;

import manager.Messages;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 */
public class AlreadyFriendsException extends SocialNetException {

	private static final long serialVersionUID = 0L;

	public AlreadyFriendsException() {
        super(Messages.HAS_FRIENDSHIP + Messages.PRINT_END);
    }

}
