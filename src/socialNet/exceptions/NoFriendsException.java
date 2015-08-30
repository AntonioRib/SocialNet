package socialNet.exceptions;

import manager.Messages;

/**
 * @author Antonio Ribeiro, n�41674 / Francisco Godinho, n�41611
 */
public class NoFriendsException extends SocialNetException {

    static final long serialVersionUID = 0L;

    public NoFriendsException() {
        super(Messages.NO_FRIENDS + Messages.PRINT_END);
    }
    
}
