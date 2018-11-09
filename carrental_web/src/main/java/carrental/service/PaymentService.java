package carrental.service;

import org.springframework.transaction.annotation.Transactional;

import carrental.model.Booking;
import carrental.model.Client;
import carrental.repository.BookingRepository;
import carrental.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
	
	@Autowired
    PaymentRepository paymentRepository;
	
	@Autowired
    BookingRepository bookingRepository;
	
	@Transactional
    public void pay(Client client, int bookingId) {
    	
        Booking booking = bookingRepository.findById(bookingId).get(0); // a foglalas, amit most kifizet

        paymentRepository.delete(booking.getPrice());  // adott fizetesi kotelezettseg torlodik
        client.getBooking().remove(booking);		// adott foglalás törlése, teljesítettük a fizetést

    }
}
