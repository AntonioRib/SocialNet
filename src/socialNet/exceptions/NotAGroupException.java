package socialNet.exceptions;

import manager.Messages;

/**
 * @author Antonio Ribeiro, n�41674 / Francisco Godinho, n�41611
 */
public class NotAGroupException extends SocialNetException {

	private static final long serialVersionUID = 0L;

	public NotAGroupException() {
        super(Messages.HASNT_GROUP + Messages.PRINT_END);
    }
}
