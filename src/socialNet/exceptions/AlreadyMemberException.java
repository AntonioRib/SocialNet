package socialNet.exceptions;

import manager.Messages;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 */
public class AlreadyMemberException extends SocialNetException {
	
	private static final long serialVersionUID = 0L;

	public AlreadyMemberException() {
        super(Messages.HAS_MEMBERSHIP + Messages.PRINT_END);
    }

}
