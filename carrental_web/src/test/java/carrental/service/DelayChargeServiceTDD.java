package carrental.service;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
		
		client.addBooking(booking);
		booking.setClient(client);
		
		delayChargeService.delayCharge();
		
		
		int expectedPriceAfterCharge = 5400;
		double delta = 0.00001;
		assertThat((double)client.getBooking().get(0).getPrice().getAmount(), closeTo(expectedPriceAfterCharge, delta));
	}

}
