package carrental.service;


import carrental.model.*;
import carrental.repository.BookingRepository;
import carrental.repository.CarRepository;
import carrental.repository.ClientRepository;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase
public class ChangeBookingDateServiceTDD {


    @Autowired
    CarRepository carRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    BookingService bookingService;

    @Autowired
    ChangeBookingDateService changeBookingDateService;

    @Test
    public void testChangeBookingDate() {
        Client client = new Client("Teszt Kliens", "teszt@kliens.hu");
        clientRepository.save(client);

        Category bugatti = new Category("Bugatti");
        Car car = new Car(5, "Chiron", new Type(), bugatti);
        car.setRentalPrice(1000);
        carRepository.save(car);

        Booking booking = bookingService.createBooking(client, car,
                new Date(2018, 11, 10),
                new Date(2018, 11, 15));
        booking = bookingRepository.save(booking);

        double delta = 1E-5;
        assertThat((double) booking.getPrice().getAmount(), closeTo(5000.0, delta));

        changeBookingDateService.changeBookingDate(booking, new Date(2018, 11,17));

        assertThat(booking.getToDate(), equalTo(new Date(2018, 11 , 17)));
        assertThat((double)booking.getPrice().getAmount(), closeTo(7000, delta));
    }
}
