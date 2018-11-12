package carrental.service;

import carrental.model.Booking;
import carrental.model.Client;
import carrental.repository.BookingRepository;
import carrental.repository.CarRepository;
import carrental.repository.ClientRepository;
import carrental.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class LastMinuteService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Transactional
    public void EcoLastMinuteDiscount(Client client) {

        Date today = Calendar.getInstance().getTime();

        List<Client> alreadyClient = clientRepository.findByName(client.getName());
        List<Booking> bookings = bookingRepository.findByClient(client);
        Booking booking = new Booking();

        if (alreadyClient.isEmpty() == false) {
            for (Booking b : bookings) {
                if (b.getFromDate().getDay() == today.getDay())
                    booking = b;
            }

        }


    }
}
