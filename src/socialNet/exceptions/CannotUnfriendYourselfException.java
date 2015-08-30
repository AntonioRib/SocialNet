package socialNet.exceptions;

import manager.Messages;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 */
public class CannotUnfriendYourselfException extends SocialNetException {
	
	private static final long serialVersionUID = 0L;

	public CannotUnfriendYourselfException() {
        super(Messages.SELF_REMOVAL + Messages.PRINT_END);
    }

}
