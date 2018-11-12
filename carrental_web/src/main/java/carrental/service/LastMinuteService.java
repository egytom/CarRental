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

        List<Client> alreadyClient = clientRepository.findByName(client.getName());
        List<Booking> bookings;

        if (alreadyClient.isEmpty() == false)
            bookings = bookingRepository.findByClient(client);

    }
}
