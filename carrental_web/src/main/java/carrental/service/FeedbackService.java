package carrental.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import carrental.model.Client;
import carrental.model.Feedback;
import carrental.repository.ClientRepository;
import carrental.repository.FeedbackRepository;

@Service
public class FeedbackService {

	@Autowired
	FeedbackRepository feedbackRepository;

	@Autowired
	ClientRepository clientrepository;

	@Transactional
	public boolean send(Feedback feedback) throws MessagingException {

		
		Client client = clientrepository.findByName(feedback.getClient().getName()).get(0);

		EmailValidator emailvalidator = new EmailValidator();

		// final String from = "temalabor2018@gmail.com"; 
		// final String password = "20Temalabor18"; 

		String to = client.getEmailAddress();
		String from = "web@gmail.com";
		String host = "localhost";
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(properties);

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Car Rental");
			message.setText(feedbackRepository.findByClient(client).get(0).getText());
			Transport.send(message);
			System.out.println("Sent message successfully....");
			if (emailvalidator.isValid(client.getEmailAddress(), null))
				return true;
			return false;
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
		return false;
	}
}