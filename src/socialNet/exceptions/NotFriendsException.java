package socialNet.exceptions;

import manager.Messages;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 */
public class NotFriendsException extends SocialNetException {

    static final long serialVersionUID = 0L;


    public NotFriendsException() {
        super(Messages.HASNT_FRIENDSHIP + Messages.PRINT_END);
    }
}
