package manager;
import java.io.Serializable;
import socialNet.exceptions.*;
import network.*;
import dataStructures.*;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 * NetworkManager - Permite controlo sobre a rede social.
 */
public interface NetworkManager extends Serializable {
	
	
	/**
	 * Adiciona um contacto 'a rede social.
	 * @param login - O login do novo contacto;
	 * @param name - O nome do novo contacto;
	 * @param location - A localizacao do novo contacto;
	 * @param job - O emprego do novo contacto;
	 * @param age - A idade do novo contacto.
	 * @throws ContactAlreadyExistsException - Quando ja' existe um contacto com o mesmo login.
	 */
	void addContact(String login, String name, String location, String job, int age) throws ContactAlreadyExistsException;
	
	/**
	 * Estabelece uma relacao de amizade entre dois contactos.
	 * @param login1 - O login do contacto.
	 * @param login2 - O login do contacto.
	 * @throws NotAContactException - Quando um dos contactos nao existe na rede social;
	 * @throws AlreadyFriendsException - Quando os dois contactos ja' sao amigos ou os parametros de entrada sao iguais.
	 */
	void addFriendship(String login1, String login2) throws NotAContactException, AlreadyFriendsException;
	
	/**
	 * Adiciona um grupo 'a rede social.
	 * @param group - O nome do grupo a adicionar;
	 * @param description - Uma breve descricao do grupo a adicionar.
	 * @throws GroupAlreadyExistsException - Quando o nome do grupo ja' existe no sistema.
	 */
	void addGroup(String group, String description) throws GroupAlreadyExistsException;
	
	/**
	 * Insere um novo post na zona de comunicacao de um determinado contacto escolhido pelo utilizador.
	 * @param login - O login do contacto;
	 * @param title - O titulo do post;
	 * @param content - O conteudo do post;
	 * @param url - O URL do post.
	 * @throws NotAContactException - Quando o contacto nao existe no sistema.
	 */
	void addPost(String login, String title, String content, String url) throws NotAContactException;
	
	/**
	 * Adiciona um contacto a um determinado grupo.
	 * @param login - O login do contacto;
	 * @param group - O nome do grupo.
	 * @throws NotAContactException - Quando o contacto nao existe no sistema;
	 * @throws NotAGroupException - Quando o grupo nao existe no sistema;
	 * @throws AlreadyMemberException - Quando o contacto e' membro do grupo especificado.
	 * @throws MaxGroupsExceededException - Quando o contacto excedeu o limite de grupos a que pode aderir.
	 */
	void addMembership(String login, String groupName) throws NotAContactException, NotAGroupException, AlreadyMemberException, MaxGroupsExceededException;
	
	/**
	 * Remove uma amizade entre dois contactos.
	 * @param login1 - O login do contacto;
	 * @param login2 - O login do contacto.
	 * @throws NotAContactException - Quando um dos contactos nao existe na rede social.
	 * @throws NotFriendsException - Quando os dois contactos nao sao amigos.
	 * @throws CannotUnfriendYourselfException - Quando o contacto tenta remover uma amizade consigo proprio, 
	 * ou seja, quando os parametros de entrada sao iguais.
	 */
	void removeFriendship(String login1, String login2) throws NotAContactException, NotFriendsException, CannotUnfriendYourselfException;
	
	/**
	 * Remove um contacto de um determinado grupo.
	 * @param login - O login do contacto;
	 * @param group - O nome do grupo.
	 * @throws NotAContactException - Quando o contacto nao existe na rede social;
	 * @throws NotAGroupException - Quando o grupo nao existe na rede social;
	 * @throws NotMemberException - Quando o contacto em questao nao faz parte do grupo especificado.
	 */
	void removeMembership(String login, String groupName) throws NotAContactException, NotAGroupException, NotMemberException;
	
	/**
	 * Remove um grupo da rede social.
	 * @param group - O nome do grupo a remover.
	 * @throws NotAGroupException - Quando o grupo em questao nao existe na rede social.
	 * @throws NotMemberException - Quando o grupo não tem membros.
	 * @throws NoMembersException - , 
	 */
	void removeGroup(String group) throws NotAGroupException, NoMembersException, NotMemberException;
	
	/**
	 * Devolve o nome de um contacto identificado pelo login inserido pelo utilizador.
	 * @param login - O login do contacto.
	 * @return uma <code>String</code> com o nome do contacto.
	 * @throws NotAContactException - Quando o contacto referido nao existe na rede social.
	 */
	String getContactName(String login) throws NotAContactException;
	
	/**
	 * Devolve a localizacao de um contacto identificado pelo login inserido pelo utilizador.
	 * @param login - O login do contacto.
	 * @return uma <code>String</code> com a localizacao do contacto.
	 * @throws NotAContactException - Quando o contacto referido nao existe na rede social.
	 */
	String getContactLocation(String login) throws NotAContactException;
	
	/**
	 * Devolve o emprego de um contacto identificado pelo login inserido pelo utilizador.
	 * @param login - O login do contacto.
	 * @return uma <code>String</code> com o emprego do contacto.
	 * @throws NotAContactException - Quando o contacto referido nao existe na rede social.
	 */
	String getContactJob(String login) throws NotAContactException;
	
	/**
	 * Devolve a idade de um contacto identificado pelo login inserido pelo utilizador.
	 * @param login - O login do contacto.
	 * @return um <code>int</code> com a idade do contacto.
	 * @throws NotAContactException - Quando o contacto referido nao existe na rede social.
	 */
	int getContactAge(String login) throws NotAContactException;
	
	/**
	 * Devolve a descricao de um grupo identificado pelo nome inserido pelo utilizador.
	 * @param group - O nome do grupo.
	 * @return uma <code>String</code> com a breve descricao do grupo.
	 * @throws NotAGroupException - Quando o grupo referido nao existe na rede social.
	 */
	String getGroupDescription(String group) throws NotAGroupException;
	
	/**
	 * Lista os amigos de um determinado contacto inserido pelo utilizador.
	 * @param login - O login do contacto.
	 * @return um objecto <code>Iterator</code> do tipo <code>Entry<String, Contact></code> com amigos do contacto.
	 * @throws NotAContactException - Quando o contacto nao existe na rede social;
	 * @throws NoFriendsException - Quando o contacto nao tem amigos.
	 */
	Iterator<Entry<String, Contact>> listFriends(String login) throws NotAContactException, NoFriendsException;
	
	/**
	 * Lista os membros de um determinado grupo inserido pelo utilizador.
	 * @param group - O nome do grupo.
	 * @return um objecto <code>Iterator</code> do tipo <code>Contact</code> com os membros do grupo.
	 * @throws NotAGroupException - Quando o grupo nao existe na rede social;
	 * @throws NoMembersException - Quando o grupo nao tem quaisquer aderentes.
	 */
	Iterator<Entry<String, Contact>> listMembers(String groupName) throws NotAGroupException, NoMembersException;
	
	/**
	 * Lista os posts do contacto identificado pelo login1 se o contacto identificado pelo login2 for seu amigo.
	 * @param login1 - O login do contacto;
	 * @param login2 - O login do contacto.
	 * @return um <code>Iterator</code> do tipo <code>Post</code> com todos os posts realizados pelo contacto identificado com o login1.
	 * @throws NotAContactException - Quando um dos contactos nao existe no sistema;
	 * @throws NotFriendsException - Quando os contactos especificados nao sao amigos;
	 * @throws NoPostsException - Quando o contacto nao tem posts a listar.
	 */
	Iterator<Post> listPosts(String login1, String login2) throws NotAContactException, PermissionDeniedException, ContactNoPostsException;
	
	/**
	 * Lista os posts de registados num grupo se o contacto identificado pelo login for membro do mesmo.
	 * @param group - O nome do grupo;
	 * @param login - O login do contacto.
	 * @return um <code>Iterator</code> do tipo <code>Post</code> com todos os posts registados no grup identificado pelo nome referido.
	 * @throws NotAGroupException - Quando o grupo nao existe na rede social;
	 * @throws NotAContactException - Quando o contacto nao existe na rede social;
	 * @throws NotMemberException - Quando o contacto inserido nao e' membro do grupo especificado;
	 * @throws NoPostsException - Quando o grupo nao tem quaisquer posts registados.
	 */
	Iterator<Post> listGroupPosts(String groupName, String login) throws NotAGroupException, NotAContactException, PermissionDeniedException, GroupNoPostsException;

}
