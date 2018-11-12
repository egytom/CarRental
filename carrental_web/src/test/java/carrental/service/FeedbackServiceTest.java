package carrental.service;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import carrental.model.Booking;
import carrental.model.Client;
import carrental.model.Feedback;
import carrental.model.Payment;
import carrental.repository.ClientRepository;
import carrental.repository.FeedbackRepository;

@RunWith(MockitoJUnitRunner.class)
public class FeedbackServiceTest {
	
	@InjectMocks
	FeedbackService feedbackService;

	@Mock
	FeedbackRepository feedbackRepository;
	
	@Mock
	ClientRepository clientrepository;

	@Test
	public void testClientCreation() {
		
		Client client = new Client(1, "Kurdi Boti", "kurdi.boti@gmail.com", "0620426742");
		Booking booking = new Booking(1);
		Feedback feedback = new Feedback(1, "Successful rent");
		Payment payment = new Payment();
		
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
		client.setFeedback(feedback);
		
		when(clientrepository.findByName(client.getName())).thenReturn(Arrays.asList(client));
		when(feedbackRepository.findByClient(client)).thenReturn(Arrays.asList(feedback));
		
		boolean result = feedbackService.send(client.getName());
		
		assertTrue(result);
	}
}
