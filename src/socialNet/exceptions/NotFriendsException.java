package socialNet.exceptions;

import manager.Messages;

/**
 * @author Antonio Ribeiro, n�41674 / Francisco Godinho, n�41611
 */
public class NotFriendsException extends SocialNetException {

    static final long serialVersionUID = 0L;


    public NotFriendsException() {
        super(Messages.HASNT_FRIENDSHIP + Messages.PRINT_END);
    }
}
