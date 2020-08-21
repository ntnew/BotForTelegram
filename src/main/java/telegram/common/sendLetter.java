package telegram.common;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static telegram.common.ReadProp.getProp;

public class sendLetter {

    public static void sendLetter(String text){
        String to = getProp("email");         // sender email
        String from = "telegrampizzappbot@gmail.com";       // receiver email
        String host = "smtp.gmail.com";            // mail server host
        String password = "NTNew021194158";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.user", from);
        properties.put("mail.smtp.password", password);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");



        Session session = Session.getDefaultInstance(properties); // default session

        try {
            MimeMessage message = new MimeMessage(session); // email message

            message.setFrom(new InternetAddress(from)); // setting header fields

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject("Заказ из telegram"); // subject line

            message.setText(text);

            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, password);
            transport.sendMessage(message, message.getAllRecipients());System.out.println("Email Sent successfully....");
            transport.close();
            System.out.println("Сделан заказ");
        } catch (MessagingException mex){
            mex.printStackTrace();
            System.out.println("не отправил");
        }

    }
}
