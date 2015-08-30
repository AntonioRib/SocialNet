package socialNet.exceptions;

import manager.Messages;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 */
public class MaxGroupsExceededException extends SocialNetException {

    static final long serialVersionUID = 0L;

    public MaxGroupsExceededException() {
        super(Messages.MAX_GROUPS_EXCEEDED + Messages.PRINT_END);
    }
    
}
