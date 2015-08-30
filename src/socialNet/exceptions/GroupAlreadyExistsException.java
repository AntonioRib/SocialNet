package socialNet.exceptions;

import manager.Messages;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 */
public class GroupAlreadyExistsException extends SocialNetException {
	
	private static final long serialVersionUID = 0L;

	public GroupAlreadyExistsException() {
        super(Messages.HAS_GROUP + Messages.PRINT_END);
    }

}
