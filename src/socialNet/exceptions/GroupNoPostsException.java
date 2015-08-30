package socialNet.exceptions;

import manager.Messages;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 */
public class GroupNoPostsException extends SocialNetException {
	
    static final long serialVersionUID = 0L;

    public GroupNoPostsException() {
        super(Messages.GROUP_NO_POSTS + Messages.PRINT_END);
    }
    
}
