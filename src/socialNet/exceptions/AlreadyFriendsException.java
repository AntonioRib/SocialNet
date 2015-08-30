package socialNet.exceptions;

import manager.Messages;

/**
 * @author Antonio Ribeiro, n�41674 / Francisco Godinho, n�41611
 */
public class AlreadyFriendsException extends SocialNetException {

	private static final long serialVersionUID = 0L;

	public AlreadyFriendsException() {
        super(Messages.HAS_FRIENDSHIP + Messages.PRINT_END);
    }

}
