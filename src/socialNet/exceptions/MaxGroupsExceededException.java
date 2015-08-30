package socialNet.exceptions;

import manager.Messages;

/**
 * @author Antonio Ribeiro, n�41674 / Francisco Godinho, n�41611
 */
public class MaxGroupsExceededException extends SocialNetException {

    static final long serialVersionUID = 0L;

    public MaxGroupsExceededException() {
        super(Messages.MAX_GROUPS_EXCEEDED + Messages.PRINT_END);
    }
    
}
