package SharedDataObjects;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Contains all the fields and methods for a Email object.
 * @author Gibson Dziwinski
 * @version 1.0
 * @since April 3, 2018
 *
 */
public class Email implements Serializable{

	/**
	 * Serial ID for the object
	 */
	private static final long serialVersionUID = 87921379L;
	
	/**
	 * who the email is from
	 */
	private String from;
	/**
	 * whom the email is to
	 * can be more than 1 person.
	 */
	private ArrayList<String> to;
	/**
	 * subject of the email
	 */
	private String subject;
	/**
	 * the email body.
	 */
	private String content;
	/**
	 * Constructor for the email.
	 * @param from
	 * @param to
	 * @param subject
	 * @param content
	 */
	public Email(String from, ArrayList<String> to, String subject, String content)
	{
		this.from = from;
		this.to = to;
		this.content = content;
		this.subject = subject;
	}
	/**
	 * getters and setters for the class
	 */
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public ArrayList<String> getTo() {
		return to;
	}
	public void setTo(ArrayList<String> to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	 
}
