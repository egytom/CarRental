package service;

import model.Booking;
import model.Car;
import model.Client;
import model.Payment;

import repository.BookingRepository;
import repository.ClientRepository;
import repository.PaymentRepository;


public class BookingService {
    BookingRepository bookingRepository;
    ClientRepository clientRepository;
    PaymentRepository paymentRepository;

    Booking createBooking(Booking booking, Client client, Car car)
    {
        booking.setClient(client);  //kliens és foglalás összekötése
        client.setBooked(booking);

        booking.setCar(car);

        int amount = car.getRentalPrice();  //payment létrehozása és összekötése a klienssel és a foglalással
        Payment payment = paymentRepository.create(amount);
        booking.setPrice(payment);
        client.setPayment(payment);

        bookingRepository.save(booking);    //felülírt példányok mentése. update/save ?
        clientRepository.save(client);
        return booking;
    }


}
