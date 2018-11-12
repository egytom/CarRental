package carrental.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import carrental.model.Booking;
import carrental.model.Client;
import carrental.repository.BookingRepository;
import carrental.repository.CarRepository;
import carrental.repository.ClientRepository;
import carrental.repository.PaymentRepository;

@Service
public class DelayChargeService {
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Transactional
	public void delayCharge(List<Client> clients) {
		Date today = Calendar.getInstance().getTime();
		
		for(Client c : clients) {
			for(Booking b : c.getBooking()) {
				if(today.compareTo(b.getToDate()) > 0) {
					long diffInMillies = Math.abs(today.getTime() - b.getToDate().getTime());
				    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
				    
				    b.getPrice().setAmount((int) (b.getPrice().getAmount()+(diff*200)));
				}
			}
		}
	}
	
}
