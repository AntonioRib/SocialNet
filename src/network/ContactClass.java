package network;
import socialNet.exceptions.*;
import dataStructures.*;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 */
public class ContactClass implements Contact, ContactUser {

	private static final long serialVersionUID = 0L;
	public static final int MAX_GROUPS = 10;
	
	private String login, name, location, job;
	private int age;
	private List<Post> posts;
	private OrderedDictionary<String, Contact> friends;
	private OrderedDictionary<String, Group> groups;
	
	
	public ContactClass(String login, String name, String location, String job, int age) {
		this.login = login;
		this.name = name;
		this.location = location;
		this.job = job;
		this.age = age;
		posts = new SinglyLinkedList<Post>();
		friends = new AVLTree<String, Contact>();
		groups = new AVLTree<String, Group>();
	}
	
	public String getLogin() {
		return login;
	}
	public String getName() {
		return name;
	}
	public String getLocation() {
		return location;
	}
	public String getJob() {
		return job;
	}
	public int getAge() {
		return age;
	}

	public int getNumberOfFriends() {
		return friends.size();
	}
	
	public int getNumberOfPosts() {
		return posts.size();
	}
	
	public Iterator<Entry<String,Contact>> listFriends() throws NoFriendsException {
		if (friends.isEmpty())
			throw new NoFriendsException();
		return friends.iterator();
	}

	public Iterator<Post> listPosts() throws ContactNoPostsException {
		if (posts.isEmpty())
			throw new ContactNoPostsException();
		return posts.iterator();
	}

	public void addPost(Post post) {
		posts.addFirst(post);
		syncPostsWithGroups(post);
		syncPostsWithFriends(post);
	}
	
	private void syncPostsWithFriends(Post post) {
		if (!friends.isEmpty()) {
			Iterator<Entry<String,Contact>> it = friends.iterator();
			while (it.hasNext())
				((ContactUser)it.next().getValue()).syncWithFriends(post);
		}
	}
	
	private void syncPostsWithGroups(Post post) {
		if (!groups.isEmpty()) {
			Iterator<Entry<String, Group>> it = groups.iterator();
			while (it.hasNext())
				it.next().getValue().addPost(post);
		}
	}

	public void addFriendship(Contact user) {
		friends.insert(user.getLogin(), user); 
	}

	public void eraseFriendship(Contact user) throws NotFriendsException {
		if (friends.remove(user.getLogin()) == null)
			throw new NotFriendsException();
	}

	public void joinGroup(Group group) throws MaxGroupsExceededException {
		if(groups.size() == MAX_GROUPS)
			throw new MaxGroupsExceededException();
		this.groups.insert(group.getName(), group);
		group.addMember(this);
	}

	public void leaveGroup(Group group) throws NotMemberException {
		if (this.groups.remove(group.getName()) == null)
			throw new NotMemberException();
	}
	
	public boolean hasFriendship(Contact user) {
		return (friends.find(user.getLogin()) != null || user == this);
	}

	public boolean isMember(Group group) {
		return (groups.find(group.getName()) != null);
	}

	public void syncWithFriends(Post post) {
		posts.addFirst(post);
	}
	
}
