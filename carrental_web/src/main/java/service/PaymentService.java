package service;

import model.Payment;
import model.Client;
import model.Booking;

import repository.BookingRepository;
import repository.PaymentRepository;


public class PaymentService {
    PaymentRepository paymentRepository;
    BookingRepository bookingRepository;

    public void pay(Client client) {
        Payment payment = paymentRepository.findById(client.getPayment().getId());  // klienshez tartozó fizetési kötelezettség

        if(client.getBooked().getPrice().getAmount() == payment.getAmount())    // ha a foglalás ára megegyezik a fizetési kötelezettség összegével
            client.getPayment().setAmount(0);  // kliens elküldi a pénzt

        paymentRepository.delete(payment);  // fizetési kötelezettség törlődik

        bookingRepository.save(client.getBooked());     // booking végleges lesz - elmentésre kerül
    }
}
