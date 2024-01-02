package lk.ijse.vehiServePro.Utill;


import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailSender {
    private  Session newSession = null;
    private  MimeMessage mimeMessage = new MimeMessage(Session.getDefaultInstance(new Properties(),null));
    public void sendMail(String email,String body,String subject ) throws MessagingException {

        setUpServerProperties();
        draftEmail(email,body,subject);
        sendEmail();
    }

    public  void setUpServerProperties() {

        Properties properties = new Properties();
        properties.put("mail.smtp.port", "587"); // Use TLS port
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS

        newSession = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("leocareservicegalle@gmail.com", "brhq cumz xytc iset");
            }
        });
    }
    public  MimeMessage draftEmail(String email,String emailBody,String emailSubject) throws MessagingException {

        mimeMessage.setFrom(new InternetAddress("leocareservicegalle@gmail.com"));
        String recipients = email;
        String subject = emailSubject;
        String body = emailBody;

        mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(recipients)));
        mimeMessage.setSubject(subject);

        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(body, "text/html");

        MimeMultipart multipart = new MimeMultipart(); //mime msg's body
        multipart.addBodyPart(bodyPart);

        mimeMessage.setContent(multipart);

        return mimeMessage;
    }
    public  void sendEmail() throws MessagingException {

        String host = "smtp.gmail.com";
        String userName = "leocareservicegalle@gmail.com";
        String password = "brhq cumz xytc iset"; // Replace with your actual Gmail password or App Password

        Transport transport = newSession.getTransport("smtp");
        transport.connect(host, userName, password);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        System.out.println("Email Sent Successfully !");
    }

}
