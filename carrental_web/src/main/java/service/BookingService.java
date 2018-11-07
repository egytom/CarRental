package service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Booking;
import model.Car;
import model.Client;
import model.Payment;
import repository.BookingRepository;
import repository.ClientRepository;
import repository.PaymentRepository;

@Service
public class BookingService {
	
	@Autowired
    BookingRepository bookingRepository;
    
	@Autowired
	ClientRepository clientRepository;
    
	@Autowired
	PaymentRepository paymentRepository;
	
	@Transactional
    Booking createBooking(Booking booking, Client client, Car car){
		
        booking.setClient(client);  //kliens és foglalás összekötése
        client.getBooking().add(booking);

        booking.setCar(car);

        int amount = car.getRentalPrice();  //payment létrehozása és összekötése a klienssel és a foglalással
        Payment payment = paymentRepository.create(amount);
        booking.setPrice(payment);

        return booking;
        
    }

}
