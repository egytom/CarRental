package carrental.service;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.awt.print.Book;

import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import carrental.model.Booking;
import carrental.model.Client;
import carrental.model.Feedback;
import carrental.repository.BookingRepository;
import carrental.repository.ClientRepository;
import carrental.repository.FeedbackRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase
public class FeedbackServiceIT {
	
	@Autowired
	FeedbackService feedbackService;

	@Autowired
	FeedbackRepository feedbackRepository;
	
	@Autowired
	ClientRepository clientrepository;
	
	@Autowired
	BookingRepository bookingrepository;
	
	@Test
	public void testClientCreation() {
		Client client = new Client(1, "Kurdi Boti", "kurdi.boti@gmail.com", "0620426742");
		Booking booking = new Booking(1);
		Feedback feedback = new Feedback(1, "Successful rent");
		
		client = clientrepository.save(client);
		feedback = feedbackRepository.save(feedback);
		
		client.addBooking(booking);
		client.setFeedback(feedback);
		
		assertThat(client.getName(), equalTo("Kurdi Boti"));
		assertThat(client.getEmailAddress(), equalTo("kurdi.boti@gmail.com"));
		assertThat(client.getPhoneNumber(), equalTo("0620426742"));
		assertThat(client.getBooking().get(0), equalTo(booking));
		assertThat(client.getFeedback(), equalTo(feedback));
	}
	
	@Test
	public void sendTest() throws MessagingException {
		Client client = new Client("Kurdi Boti", "kurdi.boti@gmail.com");
		Feedback feedback = new Feedback(1, "Successful rent");
		client.setFeedback(feedback);
		feedback.setClient(client);
		
		client = clientrepository.save(client);
		feedback = feedbackRepository.save(feedback);
		
		boolean result = feedbackService.send(client.getName());
		
		assertTrue(result);
	}
}
