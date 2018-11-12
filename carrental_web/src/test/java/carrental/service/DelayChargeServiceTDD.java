package carrental.service;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import carrental.model.Booking;
import carrental.model.Client;
import carrental.model.Payment;
import carrental.repository.BookingRepository;
import carrental.repository.CarRepository;
import carrental.repository.ClientRepository;
import carrental.repository.PaymentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase
public class DelayChargeServiceTDD {
	
	@Autowired
	DelayChargeService delayChargeService;
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Test
	public void delayChargeTest() throws ParseException {
		
		Client client = new Client(1, "Kurdi Boti");
		Payment payment = new Payment(5000);
		
		Date toDate = new GregorianCalendar(2018, Calendar.OCTOBER, 15).getTime();
		Date fromDate = new GregorianCalendar(2018, Calendar.OCTOBER, 10).getTime();
		Booking booking = new Booking(1, fromDate, toDate, payment);
		Date today = Calendar.getInstance().getTime();
		
		client = clientRepository.save(client);
		payment = paymentRepository.save(payment);
		
		long diffInMillies = Math.abs(today.getTime() - toDate.getTime());
	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		
	    int expectedPriceAfterCharge = (int) (payment.getAmount()+(diff*200));
		client.addBooking(booking);
		booking.setClient(client);
		List<Client> clients = new ArrayList<Client>();
		clients.add(client);
		delayChargeService.delayCharge(clients);
		
		double delta = 0.00001;
		assertThat((double)client.getBooking().get(0).getPrice().getAmount(), closeTo(expectedPriceAfterCharge, delta));
	}

}
