package service;

import model.Payment;
import model.Client;
import model.Booking;

import repository.BookingRepository;
import repository.PaymentRepository;

import java.util.List;


public class PaymentService {
    PaymentRepository paymentRepository;
    BookingRepository bookingRepository;

    public void pay(Client client, int id) {
        List<Booking> bookings = bookingRepository.findByClient(client);    // kliens osszes foglalasa
        Booking booking; // a foglalas, amit most kifizet

        for (Booking b : bookings) {
            if (b.getId() == id)
                booking = b;
        }

        int paid = booking.getPrice().getAmount();  // foglalasert fizetendo ar
        client.getPayment().setAmount((client.getPayment().getAmount()) - paid);  // kliens elkuldi a megfelelo osszeget

        paymentRepository.delete(booking.getPrice());  // adott fizetesi kotelezettseg torlodik

        bookingRepository.save(booking);     // booking ezzel vegleges lesz - elmentesre kerul
    }
}
