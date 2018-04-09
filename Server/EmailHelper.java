package Server;

import SharedDataObjects.Email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/*
* @author Evan Fenton
* */
public class EmailHelper {

    private String serverEmail;
    private String serverPassword;
    private Properties properties;

    public EmailHelper(String sEmail, String sPassword){
        serverEmail= sEmail;
        serverPassword= sPassword;

        properties= new Properties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

    }

    public void sendEmail(Email email){

        try{
            Message message= new MimeMessage(newSession());
            message.setFrom(new InternetAddress(serverEmail));

            for(int i=0; i < email.getTo().size(); i++)
                message.addRecipient(Message.RecipientType.CC, new InternetAddress(email.getTo().get(i)));

            message.setSubject(email.getSubject());
            message.setText(email.getContent());

            Transport.send(message);

        }catch(MessagingException e){
            e.getStackTrace();
        }

    }

    public Session newSession(){

        return Session.getInstance(properties, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(serverEmail, serverPassword);
            }
        });
    }

}
