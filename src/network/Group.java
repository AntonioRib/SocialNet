package network;
import java.io.Serializable;
import socialNet.exceptions.*;
import dataStructures.Entry;
import dataStructures.Iterator;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 * Group - Zona de comunicacao onde um determinado conjunto de contactos podem debater sobre um determinado tema atraves de posts.
 */
public interface Group extends Serializable {
	
	/**
	 * Devolve o nome do grupo.
	 * @return uma <code>String</code> com o nome do grupo.
	 */
	String getName();
	
	/**
	 * Devolve a descricao do grupo.
	 * @return uma <code>String</code> com a descricao do grupo.
	 */
	String getDescription();
	
	/**
	 * Devolve os aderentes do grupo.
	 * @throws NoMembersException - Quando o grupo nao tem aderentes.
	 * @return um objecto <code>Contact</code> aderente do grupo.
	 */
	Iterator<Entry<String, Contact>> listMembers() throws NoMembersException;
	
	/** 
	 * Adiciona um novo membro ao grupo.
	 * @param user - O contacto que se pretende aderir ao grupo.
	 */
	void addMember(Contact user);
	
	/**
	 * Remove um membro do grupo.
	 * @param user - O contacto a remover do grupo.
	 * @throws NotMemberException - Se o contacto nao fizer parte do grupo.
	 */
	void removeMember(Contact user) throws NotMemberException;
	
	/**
	 * Adiciona um post 'a zona de comunicacao do grupo.
	 * @param post - O post a adicionar ao grupo.
	 */
	void addPost(Post post);
	
	/**
	 * Retorna um iterador com todos os posts presentes na zona de comunicacao do grupo, ordenados do mais recente para o mais antigo.
	 * @return <code>Iterator</code> do tipo <code>Post</code>.
	 * @throws NoPostsException - Quando o grupo nao tem posts registados.
	 */
	Iterator<Post> listPosts() throws GroupNoPostsException;
	
	/** 
	 * Devolve quantos membros existem no grupo.
	 * @return um <code>int</code> com o numero de aderentes do grupo.
	 */
	int getNumberOfMembers();
	
	/**
	 * Devolve o numero de posts na zona de comunicacao do grupo.
	 * @return um <code>int</code> com o numero de posts do grupo.
	 */
	public int getNumberOfPosts();
}