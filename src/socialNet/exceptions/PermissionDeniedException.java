package socialNet.exceptions;

import manager.Messages;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 */
public class PermissionDeniedException extends SocialNetException {

    static final long serialVersionUID = 0L;

    public PermissionDeniedException() {
        super(Messages.DENY_POSTS + Messages.PRINT_END);
    }
    
}
