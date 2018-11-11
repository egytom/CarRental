package carrental.service;

import java.util.ArrayList;
import java.util.Date;
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
	public Booking createFreeTrial(Client client, Car car, Date from, Date to){
		
		Booking booking = new Booking();
		
		List<Client> clientBefore = clientRepository.findByName(client.getName());
		
		if(clientBefore.isEmpty()) 
			booking = addFreeTrial(client, car, from, to);
		
		return booking;
		
	}

	private Booking addFreeTrial(Client client, Car car, Date from, Date to) {
		Booking booking = new Booking(0, from, to, car, createPayment(0), client);
		
		finalizeClient(client, booking);
		finalizeCar(car, booking);
		
		return bookingRepository.save(booking);
	}

	private void finalizeCar(Car car, Booking booking) {
		car.setBooking(booking);
		car = carRepository.save(car);
	}

	private void finalizeClient(Client client, Booking booking) {
		List<Booking> bookingList = new ArrayList<Booking>();
		bookingList.add(booking);
		client.setBooking(bookingList);
		client = clientRepository.save(client);
	}

	private Payment createPayment(int amount) {
		Payment payment = new Payment();
		payment.setAmount(amount);
		return payment;
	}
	
}
