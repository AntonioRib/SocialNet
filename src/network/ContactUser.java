package network;
import socialNet.exceptions.*;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 * ContactUser - Contacto da rede social, permite o controlo e modificacao dos atributos do mesmo.
 */
public interface ContactUser extends Contact {
	
	/**
	 * Adiciona um post 'a zona de comunicacao do contacto e 'a zona de comunicacao dos seus grupos e amigos.
	 * @param post - o post a adicionar;
	 */
	void addPost(Post post);
	
	/**
	 * Estabelece uma amizade entre este contacto e outro contacto qualquer inserido pelo utilizador.
	 * @param user - o contacto sobre o qual estabelecer a amizade.
	 */
	void addFriendship(Contact user);
	
	/**
	 * Remove uma amizade entre este contacto e outro contacto qualquer inserido pelo utilizador.
	 * @param user - O contacto sobre o qual se pretende remover a amizade.
	 * @throws NotFriendsException - Quando o contacto inserido como parametro nao e' amigo deste contacto.
	 */
	void eraseFriendship(Contact user) throws NotFriendsException;
	
	/**
	 * Permite o contacto aderir a um determinado grupo.
	 * @param group - O grupo a aderir.
	 * @throws MaxGroupsExceededException - - Quando o contacto excedeu o limite de grupos a que pode aderir.
	 */
	void joinGroup(Group group) throws MaxGroupsExceededException;
	
	/**
	 * Remove o contacto de um determinado grupo.
	 * @param group - O grupo de onde se pretende remover o contacto.
	 * @throws NotMemberException - Quando o contacto nao e' membro do grupo.
	 */
	void leaveGroup(Group group) throws NotMemberException;
	
	/**
	 * Metodo adicional para adicionar posts. Permite a actualizacao das zonas de comunicacao entre o contacto e os amigos.
	 * @param post - O post a adicionar;
	 */
	void syncWithFriends(Post post);
}
