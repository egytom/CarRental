package service;

import model.Payment;
import model.Client;
import model.Booking;

import repository.BookingRepository;
import repository.PaymentRepository;


public class PaymentService {
    PaymentRepository paymentRepository;
    BookingRepository bookingRepository;
    
    //FIXME: a service oszt�lyok �llapotmentesek legyenek
    boolean paymentDeleted = false;
    boolean bookingSaved = false;

    public void pay(Client client) {
        Payment payment = paymentRepository.findById(client.getPayment().getId());  // klienshez tartozó fizetési kötelezettség

        if(client.getBooked().getPrice().getAmount() == payment.getAmount())    // ha a foglalás ára megegyezik a fizetési kötelezettség összegével
            client.getPayment().setAmount(0);  // kliens elküldi a pénzt

        if (!paymentDeleted)
            paymentDeleted = paymentRepository.delete(payment);  // fizetési kötelezettség törlődik

        if (!bookingSaved)
            bookingRepository.save(client.getBooked());     // booking végleges lesz - elmentésre kerül
    }
}
