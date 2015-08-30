package network;
import java.io.Serializable;

import socialNet.exceptions.*;
import dataStructures.*;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 * Contact - Contacto da rede social, os dados do mesmo sao acessiveis para fins de consulta a todos os amigos que possuir.
 */
public interface Contact extends Serializable {
	
	/**
	 * Devolve o login do contacto (identificador unico do contacto).
	 * @return uma <code>String</code> com o login do contacto. 
	 */
	String getLogin();
	
	/**
	 * Devolve o nome do contacto.
	 * @return uma <code>String</code> com o nome do contacto. 
	 */
	String getName();
	
	/** 
	 * Devolve a localizacao do contacto.
	 * @return uma <code>String</code> com a localizacao do contacto.
	 */
	String getLocation();
	
	/** 
	 * Devolve o emprego do contacto.
	 * @return uma <code>String</code> com o emprego do contacto.
	 */
	String getJob();

	/**
	 * Devolve a idade do contacto.
	 * @return um <code>int</code> com a idade do contacto.
	 */
	int getAge();
	
	/** Devolve o numero de amigos que o contacto possui.
	 * @return um <code>int</code> com o numero de amigos do contacto.
	 */
	int getNumberOfFriends();
	
	/**
	 * Devolve se o contacto e' ou nao amigo de outro contacto inserido pelo utilizador.
	 * @param user - O contacto sobre o qual se quer verificar a existencia de amizade.
	 * @return <code>true</code> se forem amigos, <code>false</code> caso contrario.
	 */
	boolean hasFriendship(Contact user);
	
	/**
	 * Devolve se o contacto e' ou nao membro de um determinado grupo.
	 * @param group - O grupo em que se quer verificar a aderencia do contacto.
	 * @return <code>true</code> se o contacto pertencer ao grupo, <code>false</code> caso contrario.
	 */
	boolean isMember(Group group);
	
	/**
	 * Devolve os amigos do contacto.
	 * @return um objecto do tipo <code>Entry<String, Contact></code> com os amigos do contacto.
	 * @throws NoFriendsException - Quando o contacto nao tem amigos.
	 */
	Iterator<Entry<String, Contact>> listFriends() throws NoFriendsException;
	
	/**
	 * Devolve os posts realizados pelo contacto.
	 * @return um <code>Iterator</code> com os posts realizados pelo contacto, ordenados do mais recente para o mais antigo. 
	 * @throws NoPostsException - Quando o contacto nao realizou qualquer post.
	 */
	Iterator<Post> listPosts() throws ContactNoPostsException;
	
	/**
	 * Devolve o numero de posts do contacto.
	 * @return um <code>int</code> com o numero de posts do contacto.
	 */
	int getNumberOfPosts();
	
}
