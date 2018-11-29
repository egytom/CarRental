package carrental.service;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import carrental.model.Booking;
import carrental.model.Car;
import carrental.model.Category;
import carrental.model.Client;
import carrental.model.Type;
import carrental.repository.CarRepository;


@RunWith(MockitoJUnitRunner.class)
public class BookingServiceTest {

    @InjectMocks
    BookingService bookingService;

    @Mock
    CarRepository carRepository;

    @Test
    public void testCreateBooking() throws Exception {

        Client client = new Client("Teszt Kliens", "teszt@kliens.hu");

        Category bugatti = new Category("Bugatti");
        Car car = new Car(5, "Chiron", new Type(), bugatti);

        Booking booking = bookingService.createBooking(client, car,
                new Date(2018,11,10),
                new Date(2018,11,15));

        assertThat(booking.getCar(), equalTo(car));
        assertThat(booking.getClient(), equalTo(client));
        assertThat(client.getBooking(), contains(booking));
    }

}
