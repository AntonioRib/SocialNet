package network;
import java.io.Serializable;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 * Post - Mensagem submetida ou recebida por um determinado contacto.
 */
public interface Post extends Serializable {

	/**
	 * Devolve o titulo do post.
	 * @return uma <code>String</code> com o titulo do post.
	 */
	String getTitle();
	
	/**
	 * Devolve o conteudo do post.
	 * @return uma <code>String</code> com o conteudo do post.
	 */
	String getContent();
	
	/**
	 * Devolve um URL para uma foto relacionada com a mensagem. 
	 * @return uma <code>String</code> com o URL contido no post ou <code>null</code> caso o utilizador nao tenha inserido um URL.
	 */
	String getURL();
	
}
