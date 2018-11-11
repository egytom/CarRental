package carrental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import carrental.model.Booking;
import carrental.model.Car;
import carrental.model.Client;
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
		return new Booking();
	}
	
}
