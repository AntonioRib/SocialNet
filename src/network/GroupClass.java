package network;
import socialNet.exceptions.*;
import dataStructures.*;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 */
public class GroupClass implements Group {

	private static final long serialVersionUID = 0L;
	
	private String name, description;
	private OrderedDictionary<String, Contact> members;
	private List<Post> posts;
	
	
	
	public GroupClass(String name, String description) {
		this.name = name;
		this.description = description;
		members = new AVLTree<String, Contact>();
		posts = new SinglyLinkedList<Post>();
	}
	
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	public int getNumberOfMembers() {
		return members.size();
	}

	public int getNumberOfPosts() {
		return posts.size();
	}
	
	public Iterator<Entry<String, Contact>> listMembers() throws NoMembersException {
		if (members.isEmpty())
			throw new NoMembersException();
		return members.iterator();
	}

	public void addMember(Contact user) {
		members.insert(user.getLogin(), user);
	}

	public void removeMember(Contact user) throws NotMemberException {
		if (this.members.remove(user.getLogin()) == null)
			throw new NotMemberException();
		((ContactUser) user).leaveGroup(this);
	}

	public void addPost(Post post) {
		posts.addFirst(post);
	}

	public Iterator<Post> listPosts() throws GroupNoPostsException {
		if (posts.isEmpty())
			throw new GroupNoPostsException();
		return posts.iterator();
	}

}
