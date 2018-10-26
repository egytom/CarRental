package service;

import model.Client;
import model.Feedback;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class FeedbackService {
	Feedback feedback;
	
	void send(Client client) {
		
	      String to = client.getEmailAddress();

	      String from = "temalabor2018@gmail.com";
	      final String username = "Téma Labor";
	      final String password = "20Temalabor18";

	      String host = "relay.jangosmtp.net";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "25");

	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
		   }
	         });

	      try {
		   Message message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(from));
		   message.setRecipients(Message.RecipientType.TO,
	               InternetAddress.parse(to));
		   message.setSubject("Car Rental");
		   message.setText(feedback.getText());

		   Transport.send(message);
	      } catch (MessagingException e) {
	         throw new RuntimeException(e);
	      }
	}
}
