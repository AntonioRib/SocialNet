package network;

/**
 * @author Antonio Ribeiro, nº41674 / Francisco Godinho, nº41611
 */
public class PostClass implements Post {
	
	private static final long serialVersionUID = 0L;
	private String title, content, url;						 // max char: title = 20, content & url = 200
	
	
	public PostClass(String title, String content, String url) {
		this.title = title;
		this.content = content;
		this.url = url;
	}
	
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getURL() {
		return url;
	}
	

}
