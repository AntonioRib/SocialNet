import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.Scanner;
import socialNet.exceptions.*;
import dataStructures.*;
import manager.*;
import network.*;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 * Main
 */
public class Main {
	
	
	public enum Commands {
		IC, CC, IA, RA, IG, CG, RG, ID, RD, IP, LA, LD, LC, LG, DEFAULT
	}
	
	public static void main(String[] args) {
			menu();
	}
	
	private static void menu() {
		Scanner in = new Scanner(System.in);
		Commands cmd = null;
		NetworkManager web = load();
		while (in.hasNext()) {
			try {
				cmd = Commands.valueOf(in.next().toUpperCase());
			} catch (IllegalArgumentException e) {
				cmd = Commands.DEFAULT;
			} finally {
				switch (cmd) {
					case IC:
						addContact(in, web);
						break;
					case CC:
						getContactDetails(in, web);
						break;
					case IA:
						addFriendship(in, web);
						break;
					case RA:
						removeFriendship(in, web);
						break;
					case IG:
						addGroup(in, web);
						break;
					case CG:
						getGroupDetails(in, web);
						break;
					case RG:
						removeGroup(in, web);
						break;
					case ID:
						addMember(in, web);
						break;
					case RD:
						removeMember(in, web);
						break;
					case IP:
						addPost(in, web);
						break;
					case LA:
						listContactFriends(in, web);
						break;
					case LD:
						listGroupMembers(in, web);
						break;
					case LC:
						listContactPosts(in, web);
						break;
					case LG:
						listGroupPosts(in, web);
						break;
					default:
						break;
				}
			}
		}
		store(web);
		in.close();
	}

	/**
	 * Adiciona um novo contacto 'a rede social. Necessita de um login, 
	 * do nome do contacto, da sua idade, da sua localizacao e do seu emprego.
	 * Falha se o login ja' existir no sistema.
	 */
	private static void addContact(Scanner in, NetworkManager web) {
		String login = in.next();
		String name = in.nextLine().trim();
		int age = in.nextInt();
		String location = in.nextLine().trim();
		String job = in.nextLine();
		in.nextLine();
		try {
			web.addContact(login, name, location, job, age);
			System.out.print(Messages.ADDED_CONTACT + Messages.PRINT_END);
		} catch (SocialNetException e) {
			System.out.print(e.getMessage());
		}
	}

	/**
	 * Consulta os detalhes de um contacto: o seu nome, a sua idade, a sua localizacao e o seu emprego.
	 * Necessita do login do contacto. Falha se o contacto nao existir no sistema.
	 */
	private static void getContactDetails(Scanner in, NetworkManager web) {
		String login = in.nextLine().trim();
		in.nextLine();
		try {
			System.out.print(login.toUpperCase() + " " + web.getContactName(login).toUpperCase() + " " + web.getContactAge(login) + '\n'
					+ web.getContactLocation(login).toUpperCase() + " " + web.getContactJob(login).toUpperCase() + Messages.PRINT_END);
		} catch (SocialNetException e) {
			System.out.print(e.getMessage());
		}
	}

	/**
	 * Estabelece uma amizade entre dois contactos. Necessita dos logins de ambos os contactos.
	 * Falha se algum dos contactos nao existir no sistema ou se os mesmos ja' forem amigos.
	 */
	private static void addFriendship(Scanner in, NetworkManager web) {
		String login1 = in.next();
		String login2 = in.nextLine().trim();
		in.nextLine();
		try {
			web.addFriendship(login1, login2);
			System.out.print(Messages.ADDED_FRIENDSHIP + Messages.PRINT_END);
		} catch (SocialNetException e) {
			System.out.print(e.getMessage());
		}
	}

	/**
	 * Remove uma amizade estabelecida entre dois contactos. Necessita dos logins de ambos os contactos.
	 * Falha se algum dos contactos nao existir no sistema, se nao forem amigos
	 * ou se o utilizador tentar remover a amizade de um contacto consigo proprio.
	 */
	private static void removeFriendship(Scanner in, NetworkManager web) {
		String login1 = in.next();
		String login2 = in.nextLine().trim();
		in.nextLine();
		try {
			web.removeFriendship(login1, login2);
			System.out.print(Messages.REMOVED_FRIENDSHIP + Messages.PRINT_END);
		} catch (SocialNetException e) {
			System.out.print(e.getMessage());
		}
	}

	/**
	 * Adiciona um novo grupo 'a rede social.
	 * Necessita de um nome para o grupo e de uma breve descricao sobre o mesmo.
	 * Falha se o nome do grupo ja' existir no sistema.
	 */
	private static void addGroup(Scanner in, NetworkManager web) {
		String group = in.nextLine().trim();
		String description = in.nextLine();
		in.nextLine();
		try {
			web.addGroup(group, description);
			System.out.print(Messages.ADDED_GROUP + Messages.PRINT_END);
		} catch (SocialNetException e) {
			System.out.print(e.getMessage());
		}
	}

	/**
	 * Consulta os detalhes de um grupo: a sua descricao. Necessita do nome do grupo.
	 * Falha se o grupo nao existir no sistema.
	 */
	private static void getGroupDetails(Scanner in, NetworkManager web) {
		String group = in.nextLine().trim();
		in.nextLine();
		try {
			System.out.print(group.toUpperCase() + '\n' + web.getGroupDescription(group.toUpperCase()).toUpperCase() + Messages.PRINT_END);
		} catch (SocialNetException e) {
			System.out.print(e.getMessage());
		}
	}

	/**
	 * Remove um grupo da rede social. Necessita do nome do grupo.
	 * Falha se o grupo nao existir no sistema.
	 */
	private static void removeGroup(Scanner in, NetworkManager web) {
		String group = in.nextLine().trim();
		in.nextLine();
		try {
			web.removeGroup(group);
			System.out.print(Messages.REMOVED_GROUP + Messages.PRINT_END);
		} catch (SocialNetException e) {
			System.out.print(e.getMessage());
		}
	}

	/**
	 * Adiciona um contacto a um grupo da rede social.
	 * Necessita do login do contacto e do nome do grupo ao qual se pretende que o contacto adira.
	 * Falha se o contacto ou o grupo nao existirem no sistema, ou se o contacto ja' for membro do grupo em questao. 
	 */
	private static void addMember(Scanner in, NetworkManager web) {
		String login = in.next();
		String group = in.nextLine().trim();
		in.nextLine();
		try {
			web.addMembership(login, group);
			System.out.print(Messages.ADDED_MEMBER + Messages.PRINT_END);
		} catch (SocialNetException e) {
			System.out.print(e.getMessage());
		}
		
	}

	/**
	 * Remove um contacto de um grupo da rede social.
	 * Necessita do login do contacto a remover e do nome do grupo ao qual pertence.
	 * Falha se o contacto ou o grupo nao existirem, ou se o contacto nao for membro do grupo em questao.
	 */
	private static void removeMember(Scanner in, NetworkManager web) {
		String login = in.next();
		String group = in.nextLine().trim();
		in.nextLine();
		try {
			web.removeMembership(login, group);
			System.out.print(Messages.REMOVED_MEMBERSHIP + Messages.PRINT_END);
		} catch (SocialNetException e) {
			System.out.print(e.getMessage());
		}
	}

	/**
	 * Adiciona um post atraves de um contacto escolhido pelo utilizador.
	 * Necessita do login do contacto, do titulo do post e do seu conteudo.
	 * Opcionalmente pode-se adicionar um url ao post.
	 * Falha se o contacto em questao nao existir no sistema.
	 */
	private static void addPost(Scanner in, NetworkManager web) {
		String login = in.nextLine().trim();
		String title = in.nextLine();
		String content = in.nextLine();
		String url = in.nextLine().trim();
		if (!url.equals(""))
			in.nextLine();
		try {
			web.addPost(login, title, content, url);
			System.out.print(Messages.ADDED_POST + Messages.PRINT_END);
		} catch (SocialNetException e) {
			System.out.print(e.getMessage());
		}
	}

	/**
	 * Lista os amigos de um contacto escolhido pelo utilizador. Necessita do login do contacto.
	 * Falha se o contacto nao existir no sistema ou se nao tiver quaisquer amigos.
	 */
	private static void listContactFriends(Scanner in, NetworkManager web) {
		String login = in.nextLine().trim();
		in.nextLine();
		try {
			Iterator<Entry<String,Contact>> it = web.listFriends(login);
			while (it.hasNext()) {
				Contact friend = it.next().getValue();
				System.out.println(friend.getLogin().toUpperCase() + " " + friend.getName().toUpperCase());
			}
			System.out.println();
		} catch (SocialNetException e) {
			System.out.print(e.getMessage());
		}
	}

	/**
	 * Lista os aderentes de um grupo escolhido pelo utilizador. Necessita do nome do grupo.
	 * Falha se o grupo em questao nao existir ou se nao tiver quaisquer aderentes.
	 */
	private static void listGroupMembers(Scanner in, NetworkManager web) {
		String group = in.nextLine().trim();
		in.nextLine();
		try {
			Iterator<Entry<String, Contact>> it = web.listMembers(group);
			while (it.hasNext()) {
				Contact member = it.next().getValue();
				System.out.println(member.getLogin().toUpperCase() + " " + member.getName().toUpperCase());
			}
			System.out.println();
		} catch (SocialNetException e) {
			System.out.print(e.getMessage());
		}
	}

	/**
	 * Lista os posts de um contacto escolhido pelo utilizador.
	 * Necessita do login do contacto do qual se quer listar os seus posts e do login de um contacto amigo.
	 * Falha se algum dos contactos nao existir no sistema, se os contactos nao forem amigos ou se o contacto 
	 * do qual se listar os posts nao tiver realizado qualquer post.
	 */
	private static void listContactPosts(Scanner in, NetworkManager web) {
		String login1 = in.next();
		String login2 = in.nextLine().trim();
		in.nextLine();
		try {
			Iterator<Post> it = web.listPosts(login1, login2);
			while (it.hasNext()) {
				Post post = it.next();
				System.out.println(post.getTitle().toUpperCase() + '\n' + post.getContent().toUpperCase());
				if (!post.getURL().equals(""))
					System.out.print(post.getURL().toUpperCase() + Messages.PRINT_END);
				else
					System.out.print('\n');
			}
		} catch (SocialNetException e) {
			System.out.print(e.getMessage());
		}
	}
	
	/**
	 * Lista os posts registados na zona de comunicacao de um grupo escolhido pelo utilizador.
	 * Necessita do nome do grupo do qual se quer listar os posts e do login de um contacto aderente.
	 * Falha se o grupo ou o contacto nao existirem no sistema, se o contacto nao for membro do grupo 
	 * ou se o grupo nao tiver nao tiver quaisquer posts registados.
	 */
	private static void listGroupPosts(Scanner in, NetworkManager web) {
		String group = in.next();
		String login = in.nextLine().trim();
		in.nextLine();
		try {
			Iterator<Post> it = web.listGroupPosts(group, login);
			while (it.hasNext()) {
				Post post = it.next();
				System.out.println(post.getTitle().toUpperCase() + '\n' + post.getContent().toUpperCase());
				if (!post.getURL().equals(""))
					System.out.print(post.getURL().toUpperCase() + Messages.PRINT_END);
				else
					System.out.print('\n');
			}	
		} catch (SocialNetException e) {
			System.out.print(e.getMessage());
		}
	}
	
	
	/* Metodos de serializacao */
	
	/**
	 * Grava os dados da simulacao num ficheiro de texto com o nome "base_de_dados.ser".
	 * @param web - A simulacao a gravar.
	 */
	private static void store(NetworkManager web) {
		try {
			ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream("base_de_dados.ser"));
			file.writeObject(web);
			file.flush();
			file.close();
		} catch (IOException e) {}
	}
	
	/**
	 * Carrega os dados de uma simulacao realizada anteriormente caso exista algum ficheiro de texto
	 * com o nome "base_de_dados.ser". Caso esse ficheiro nao exista ou ocorra algum problema com os
	 * dados gravados no ficheiro, o metodo cria uma simulacao nova.
	 * @return a simulacao gravada/nova.
	 */
	private static NetworkManager load() {
		NetworkManager web = null;
		try {
			ObjectInputStream file = new ObjectInputStream(new FileInputStream("base_de_dados.ser"));
			web = (NetworkManager) file.readObject();
			file.close();
		} catch (IOException e) {
			web = new NetworkManagerClass();
		} catch (ClassNotFoundException f) {
			web = new NetworkManagerClass();
		}
		return web;
	}

}