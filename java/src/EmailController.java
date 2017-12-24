import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailController {




        static Properties mailServerProperties;
        static Session getMailSession;
        static MimeMessage generateMailMessage;
        /*private String userEmail;

        void setUserEmail(String userEmail){
            this.userEmail = userEmail;
        }
        String getUserEmail(){
            return this.userEmail;
        }

        EmailController(){}
        EmailController(String userEmail){
            setUserEmail(userEmail);
        }*/

        public static void generateAndSendEmail(String userEmail,String bookName,String userName) throws AddressException, MessagingException {

            mailServerProperties = System.getProperties();
            mailServerProperties.put("mail.smtp.port", "587");
            mailServerProperties.put("mail.smtp.auth", "true");
            mailServerProperties.put("mail.smtp.starttls.enable", "true");
            System.out.println("Mail Server Properties have been setup successfully..");


            getMailSession = Session.getDefaultInstance(mailServerProperties, null);
            generateMailMessage = new MimeMessage(getMailSession);
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
            generateMailMessage.setSubject("Greetings from BookShare You Have New Book Request..");
            String emailBody = "Your book " + bookName + "has been requested from" + userName + "<br><br> Regards, <br>Erkin KURT";
            generateMailMessage.setContent(emailBody, "text/html");
            System.out.println("Mail Session has been created successfully..");


            Transport transport = getMailSession.getTransport("smtp");

            //There will be an error because Google can't validate my username and password.
            //transport.connect("smtp.gmail.com","user","password");

            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            transport.close();
        }
    }

