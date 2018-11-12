package carrental.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import carrental.model.Client;
import carrental.repository.ClientRepository;
import carrental.repository.FeedbackRepository;

@Service
public class FeedbackService {

	@Autowired
	FeedbackRepository feedbackRepository;
	
	@Autowired
	ClientRepository clientrepository;

	@Transactional
	public boolean send(String clientName) throws MessagingException {
		
		Client client = clientrepository.findByName(clientName).get(0);
		
		EmailValidator emailvalidator = new EmailValidator();
		
//		final String from = "temalabor2018@gmail.com"; // change accordingly
//        final String password = "20Temalabor18"; // change accordingly
        String userName = "temalabor2018@gmail.com";
//        String host = "smtp.dreamhost.com"; // or IP address

     // Recipient's email ID needs to be mentioned.
        String to = client.getEmailAddress();

        // Sender's email ID needs to be mentioned
        String from = "web@gmail.com";

        // Assuming you are sending email from localhost
        String host = "localhost";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
           // Create a default MimeMessage object.
           MimeMessage message = new MimeMessage(session);

           // Set From: header field of the header.
           message.setFrom(new InternetAddress(from));

           // Set To: header field of the header.
           message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

           // Set Subject: header field
           message.setSubject("Car Rental");

           // Now set the actual message
           message.setText(feedbackRepository.findByClient(client).get(0).getText());

           // Send message
           Transport.send(message);
           System.out.println("Sent message successfully....");
           if(emailvalidator.isValid(client.getEmailAddress(), null))
				return true;
			return false;
        } catch (MessagingException mex) {
           mex.printStackTrace();
        }
        return false;
     }
}
//		
//		EmailValidator emailvalidator = new EmailValidator();
//		
//		
//		String to = client.getEmailAddress();
//
//		String from = "temalabor2018@gmail.com";
//		final String username = "Tema Labor";
//		final String password = "20Temalabor18";
//
//		String host = "relay.jangosmtp.net";
//
//		Properties props = new Properties();
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.smtp.host", host);
//		props.put("mail.smtp.port", "25");
//
//		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(username, password);
//			}
//		});
//
//		try {
//			Message message = new MimeMessage(session);
//			message.setFrom(new InternetAddress(from));
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
//			message.setSubject("Car Rental");
//			message.setText(feedbackRepository.findByClient(client).get(0).getText());
//
//			Transport.send(message);
//			
//			if(emailvalidator.isValid(client.getEmailAddress(), null))
//				return false;
//			return true;
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//		}
