package Server;

import SharedDataObjects.Email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Properties;

/*
* used to send emails between users
* but can only send from gmail accounts
* @author Evan Fenton
* */
public class EmailHelper {

    //email of person sending message (gmail account)
    private String senderEmail;

    //email account password
    private String senderPassword;

    //properties of the email
    private Properties properties;

    //constructor
    public EmailHelper(String sEmail, String sPassword){
        senderEmail= sEmail;
        senderPassword= sPassword;

        properties= new Properties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

    }

    /*
    * send an email
    * @param email to send
    * */
    public void sendEmail(Email email){

        try{
            Message message= new MimeMessage(newSession());
            message.setFrom(new InternetAddress(senderEmail));

            for(int i=0; i < email.getTo().size(); i++)
                message.addRecipient(Message.RecipientType.CC, new InternetAddress(email.getTo().get(i)));

            message.setSubject(email.getSubject());
            message.setText(email.getContent());

            System.out.println("About to send message from "+ senderEmail+ " with password "+ senderPassword);
            System.out.println("Subject: "+ email.getSubject()+ "\nContent: "+email.getContent());

            Transport.send(message);

        }catch(MessagingException e){
            e.getStackTrace();
        }

    }

    /*
    * authenticates the sender's gmail account, and creates a new session
    * @return session created
    * */
    private Session newSession(){

        return Session.getInstance(properties, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
    }

}
