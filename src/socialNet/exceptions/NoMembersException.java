package socialNet.exceptions;

import manager.Messages;

/**
 * @author Antonio Ribeiro, n�41674 / Francisco Godinho, n�41611
 */
public class NoMembersException extends SocialNetException {
	
    static final long serialVersionUID = 0L;

    public NoMembersException() {
        super(Messages.NO_MEMBERS + Messages.PRINT_END);
    }
    
}
