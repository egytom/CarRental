package carrental.service;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import carrental.model.Booking;
import carrental.model.Client;
import carrental.model.Feedback;
import carrental.model.Payment;
import carrental.repository.BookingRepository;
import carrental.repository.ClientRepository;
import carrental.repository.FeedbackRepository;
import carrental.repository.PaymentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class FeedbackServiceIT {
	
	@Autowired
	FeedbackService feedbackService;

	@Autowired
	FeedbackRepository feedbackRepository;
	
	@Autowired
	ClientRepository clientrepository;
	
	@Autowired
	BookingRepository bookingrepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Test
	public void testClientCreation() {
		Client client = new Client(1, "Kurdi Boti", "kurdi.boti@gmail.com", "0620426742");
		Booking booking = new Booking(1);
		Feedback feedback = new Feedback(1, "Successful rent");
		Payment payment = new Payment();
		
		payment = paymentRepository.save(payment);
		client = clientrepository.save(client);
		feedback = feedbackRepository.save(feedback);
		booking = bookingrepository.save(booking);
		
		booking.setPrice(payment);
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
		Payment payment = new Payment();
		Booking booking = new Booking(1);
		
		
		booking.setPrice(payment);
		client.addBooking(booking);
		client.setFeedback(feedback);
		feedback.setClient(client);
		
		payment = paymentRepository.save(payment);
		client = clientrepository.save(client);
		feedback = feedbackRepository.save(feedback);
		booking = bookingrepository.save(booking);
		
		boolean result = feedbackService.send(client.getName());
		
		assertTrue(result);
	}
}
