package carrental.service;

import carrental.model.*;
import carrental.repository.BookingRepository;
import carrental.repository.CarRepository;
import carrental.repository.ClientRepository;
import carrental.repository.PaymentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase
public class BookingServiceIT {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    BookingService bookingService;

    @Test
    public void testCreateBooking() throws Exception {
        Client client = new Client("Teszt Kliens", "teszt@kliens.hu");
        clientRepository.save(client);

        Category bugatti = new Category("Bugatti");
        Car car = new Car(5, "Chiron", new Type(), bugatti);
        carRepository.save(car);

        Booking booking = bookingService.createBooking(client, car);
        booking = bookingRepository.save(booking);


        assertThat(booking.getCar(), equalTo(car));
        assertThat(booking.getClient(), equalTo(client));
        assertThat(client.getBooking(), contains(booking));
    }
}

