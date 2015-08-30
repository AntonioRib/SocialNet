package manager;
import socialNet.exceptions.*;
import network.*;
import dataStructures.*;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 */
public class NetworkManagerClass implements NetworkManager {


	private static final long serialVersionUID = 0L;
	public static final int SYS_MAX_USERS = 12000;
	public static final int SYS_MAX_GROUPS = 3000;
	
	private Dictionary<String, Contact> users; 
	private Dictionary<String, Group> groups;
	
	
	public NetworkManagerClass() {
		users = new SepChainHashTable<String, Contact>(SYS_MAX_USERS);
		groups = new SepChainHashTable<String, Group>(SYS_MAX_GROUPS);
	}
	
	private boolean hasFriendship(Contact user1, Contact user2) {
		return user1.hasFriendship(user2) && user2.hasFriendship(user1);
	}

	private Group getGroup(String groupName) {
		return groups.find(groupName.toUpperCase());
	}
	
	private Contact getContact(String login) {
		return users.find(login.toUpperCase());
	}

	public void addContact(String login, String name, String location, String job, int age) throws ContactAlreadyExistsException {
		if (getContact(login) != null)
			throw new ContactAlreadyExistsException();
		users.insert(login.toUpperCase(), new ContactClass(login, name, location, job, age));
	}
	
	public void addFriendship(String login1, String login2) throws NotAContactException, AlreadyFriendsException {
		
		Contact user1, user2;		
		if ((user1 = getContact(login1)) == null || (user2 = getContact(login2)) == null)
			throw new NotAContactException();
		if (hasFriendship(user1, user2))
			throw new AlreadyFriendsException();
		((ContactUser) user1).addFriendship(user2);
		((ContactUser) user2).addFriendship(user1);
	}

	public void addGroup(String group, String description) throws GroupAlreadyExistsException {
		if (getGroup(group) != null)
			throw new GroupAlreadyExistsException();
		groups.insert(group.toUpperCase(), new GroupClass(group, description));
	}
	
	public void addMembership(String login, String groupName) throws NotAContactException, NotAGroupException, AlreadyMemberException, MaxGroupsExceededException {
		
		Contact user;
		Group group;
		
		if((user = getContact(login)) == null)
			throw new NotAContactException();
		if((group = getGroup(groupName)) == null)
			throw new NotAGroupException();
		if(user.isMember(group))
			throw new AlreadyMemberException();
		((ContactUser) user).joinGroup(group);
	}

	public void addPost(String login, String title, String content, String url) throws NotAContactException {
		Contact user;
		if ((user = getContact(login)) == null)
			throw new NotAContactException();
		Post post = new PostClass(title, content, url);
		((ContactUser) user).addPost(post);
	}

	public void removeFriendship(String login1, String login2) throws NotAContactException, NotFriendsException, CannotUnfriendYourselfException {
		
		Contact user1, user2;
		
		if ((user1 = getContact(login1)) == null || (user2 = getContact(login2)) == null)
			throw new NotAContactException();
		if (login1.equalsIgnoreCase(login2))
			throw new CannotUnfriendYourselfException();
		((ContactUser) user1).eraseFriendship(user2);
		((ContactUser) user2).eraseFriendship(user1);
	}

	public void removeMembership(String login, String groupName) throws NotAContactException, NotAGroupException, NotMemberException {
		
		Contact user;
		Group group;
		if ((user = getContact(login)) == null)
			throw new NotAContactException();
		if ((group = getGroup(groupName)) == null)
			throw new NotAGroupException();
		group.removeMember(user);
	}

	
	public void removeGroup(String groupName) throws NotAGroupException {
		
		Group group;
		if ((group = getGroup(groupName)) == null)
			throw new NotAGroupException();
		groupMembersRemoval(group);
		groups.remove(groupName.toUpperCase());
	}
	
	private void groupMembersRemoval(Group group) {
		try {
			Iterator<Entry<String, Contact>> it = listMembers(group.getName());
			while (it.hasNext())
				((ContactUser) it.next().getValue()).leaveGroup(group);
			
		} catch(SocialNetException e) {}
	}
	
	public String getContactName(String login) throws NotAContactException {
		Contact user;
		if ((user = getContact(login)) == null)
			throw new NotAContactException();
		return user.getName();
	}

	public String getContactLocation(String login) throws NotAContactException {
		Contact user;
		if ((user = getContact(login)) == null)
			throw new NotAContactException();
		return user.getLocation();
	}

	public String getContactJob(String login) throws NotAContactException {
		Contact user;
		if ((user = getContact(login)) == null)
			throw new NotAContactException();
		return user.getJob();
	}

	public int getContactAge(String login) throws NotAContactException {
		Contact user;
		if ((user = getContact(login)) == null)
			throw new NotAContactException();
		return user.getAge();
	}

	public String getGroupDescription(String groupName) throws NotAGroupException {
		Group group;
		if ((group = getGroup(groupName)) == null)
			throw new NotAGroupException();
		return group.getDescription();
	}

	public Iterator<Entry<String,Contact>> listFriends(String login) throws NotAContactException, NoFriendsException {
		Contact user;
		if ((user = getContact(login)) == null)
			throw new NotAContactException();
		if(user.getNumberOfFriends() == 0)
			throw new NoFriendsException();
		return user.listFriends();
	}

	public Iterator<Entry<String, Contact>> listMembers(String groupName) throws NotAGroupException, NoMembersException {
		Group group;
		if ((group = getGroup(groupName)) == null)
			throw new NotAGroupException();
		if (group.getNumberOfMembers() == 0)
			throw new NoMembersException();
		return group.listMembers();
	}

	public Iterator<Post> listPosts(String login1, String login2) throws NotAContactException, PermissionDeniedException, ContactNoPostsException {
		Contact user1, user2;
		if ((user1 = getContact(login1)) == null || (user2 = getContact(login2)) == null)
			throw new NotAContactException();
		if(!hasFriendship(user1, user2))
			throw new PermissionDeniedException();
		return user1.listPosts();
	}

	public Iterator<Post> listGroupPosts(String groupName, String login) throws NotAGroupException, NotAContactException, PermissionDeniedException, GroupNoPostsException {
		
		Contact user;
		Group group;
		if ((group = getGroup(groupName)) == null)
			throw new NotAGroupException();
		if ((user = getContact(login)) == null)
			throw new NotAContactException();
		if(!user.isMember(group))
			throw new PermissionDeniedException();
		if(group.getNumberOfPosts() == 0)
			throw new GroupNoPostsException();
		return group.listPosts();
	}

}
