package socialNet.exceptions;

import manager.Messages;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 */
public class NoMembersException extends SocialNetException {
	
    static final long serialVersionUID = 0L;

    public NoMembersException() {
        super(Messages.NO_MEMBERS + Messages.PRINT_END);
    }
    
}
