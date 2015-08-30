package socialNet.exceptions;

import manager.Messages;

/**
 * @author Antonio Ribeiro, n�41674 / Francisco Godinho, n�41611
 */
public class NotMemberException extends SocialNetException {

	static final long serialVersionUID = 0L;

    public NotMemberException() {
        super(Messages.HASNT_MEMBERSHIP + Messages.PRINT_END);
    }
}
