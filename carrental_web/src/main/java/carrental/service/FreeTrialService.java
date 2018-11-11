package carrental.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import carrental.model.Booking;
import carrental.model.Car;
import carrental.model.Client;
import carrental.model.Payment;
import carrental.repository.BookingRepository;
import carrental.repository.CarRepository;
import carrental.repository.ClientRepository;

@Service
public class FreeTrialService {
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Transactional
	public Booking createFreeTrial(Client client, Car car){
		
		Booking booking = new Booking();
		
		
		List<Client> clientBefore = clientRepository.findByName(client.getName());
		
		if(clientBefore.isEmpty()) {
			Payment payment = new Payment();
			payment.setAmount(0);
			
			booking = new Booking(0, car, payment, client);
			
			List<Booking> bookingList = new ArrayList<Booking>();
			bookingList.add(booking);
			client.setBooking(bookingList);
			
			client = clientRepository.save(client);
			
			booking = bookingRepository.save(booking);
		}
		
		return booking;
		
	}
	
}
