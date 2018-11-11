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
		
		final String from = "temalabor2018@gmail.com"; // change accordingly
        final String password = "20Temalabor18"; // change accordingly
        String userName = "Tema Labor";
        String to = client.getEmailAddress(); // change accordingly
        String host = "smtp.dreamhost.com"; // or IP address

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", from);
        properties.put("mail.password", password);

        // Get the default Session object.
        Authenticator auth = new Authenticator()
        {
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(from, password);
            }
        };
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });

        try
        {
           // Create a default MimeMessage object.
           MimeMessage message = new MimeMessage(session);

           // Set From: header field of the header.
           message.setFrom(new InternetAddress(from));

           // Set To: header field of the header.
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));

           // Set Subject: header field
           message.setSubject("Car Rental");

           // Now set the actual message
           message.setText(feedbackRepository.findByClient(client).get(0).getText());

           // Send message
           Transport.send(message);
           if(emailvalidator.isValid(client.getEmailAddress(), null))
				return false;
			return true;
        }
        catch (SendFailedException mex)
        {
           mex.printStackTrace();
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
		return false;
	}
}
