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
import carrental.repository.PaymentRepository;

@Service
public class FreeTrialService {
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Transactional
	public Booking createFreeTrial(Client client, Car car, Date fromDate, Date toDate){
		
		Booking booking = new Booking();
		
		List<Client> clientBefore = clientRepository.findByName(client.getName());
		
		if(clientBefore.isEmpty()) 
			addFreeTrial(booking, client, car, fromDate, toDate);
		
		return booking;
		
	}

	private void addFreeTrial(Booking booking, Client client, Car car, Date fromDate, Date toDate) {
		booking.setFromDate(fromDate);
		booking.setToDate(toDate);
		booking.setCar(car);
		booking.setPrice(createPayment(0));
		booking.setClient(client);
				
		
		finalizeClient(client, booking);
		booking = bookingRepository.save(booking);
		finalizeCar(car);
	}

	private void finalizeCar(Car car) {
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
		return paymentRepository.save(payment);
	}
	
}
