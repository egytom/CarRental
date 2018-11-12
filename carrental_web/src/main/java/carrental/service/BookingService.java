package carrental.service;

import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.concurrent.TimeUnit;

import carrental.model.Booking;
import carrental.model.Car;
import carrental.model.Client;
import carrental.model.Payment;
import carrental.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

	@Autowired
	PaymentRepository paymentRepository;
	
	@Transactional
    Booking createBooking(Client client, Car car, Date fromDate, Date toDate){
		Booking booking = new Booking();
		booking.setFromDate(fromDate);
		booking.setToDate(toDate);
        booking.setClient(client);  //kliens és foglalás összekötése

        booking.setCar(car);

        long bookingLength = toDate.getTime() - fromDate.getTime();
        long bookingLengthInDays = TimeUnit.DAYS.convert(bookingLength, TimeUnit.MILLISECONDS);

        int amount = car.getRentalPrice()* (int)bookingLengthInDays ;  //payment létrehozása és összekötése a klienssel és a foglalással
        Payment payment = new Payment(amount);
        booking.setPrice(payment);

        client.addBooking(booking);

        return booking;
    }
}
