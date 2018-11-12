package carrental.service;

import carrental.model.*;
import carrental.repository.BookingRepository;
import carrental.repository.CarRepository;
import carrental.repository.ClientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class BookingServiceTest {

    @InjectMocks
    BookingService bookingService;

    @Mock
    ClientRepository clientRepository;

    @Mock
    CarRepository carRepository;

    @Mock
    BookingRepository bookingRepository;

    @Test
    public void testCreateBooking() throws Exception {

        Client client = new Client("Teszt Kliens", "teszt@kliens.hu");
        clientRepository.save(client);

        Category bugatti = new Category("Bugatti");
        Car car = new Car(5, "Chiron", new Type(), bugatti);
        carRepository.save(car);

        Booking booking = bookingService.createBooking(client, car,
                new Date(2018,11,10),
                new Date(2018,11,15));
        booking = bookingRepository.save(booking);

        assertThat(booking.getCar(), equalTo(car));
        assertThat(booking.getClient(), equalTo(client));
        assertThat(client.getBooking(), contains(booking));
    }

}
