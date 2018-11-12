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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class LastMinuteServiceTDD {

    @Autowired
    LastMinuteService lastMinuteService;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Test
    public void testEcoLastMinuteDiscount() throws Exception {

        //ARRANGE
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDate = Calendar.getInstance().getTime();
        Date toDate = sdf.parse("2019-01-01");

        Client client = new Client("Teszt Kliens", "teszt@kliens.hu");
        Car car = new Car(1, "Passat", new Type(5, Type.GearType.auto, Type.FuelType.electric), new Category("Volkswagen"));
        Payment payment = new Payment(1, 5000);

        car = carRepository.save(car);
        client = clientRepository.save(client);
        payment = paymentRepository.save(payment);

        Booking booking = new Booking(1, fromDate, toDate, car, payment, client);

        booking = bookingRepository.save(booking);
        client.addBooking(booking);

        //ACT
        lastMinuteService.EcoLastMinuteDiscount(client);

        //ASSERT
        client = clientRepository.findByName("Teszt Kliens").get(0);
        booking = bookingRepository.findByClient(client).get(0);

        assertThat(client.getName(), equalTo("Teszt Kliens"));
        //noinspection deprecation
        assertThat(booking.getFromDate().getDay(), equalTo(Calendar.getInstance().getTime().getDay()));
        assertThat(car.getType().getFuelType(), equalTo(Type.FuelType.electric));
        assertThat(booking.getPrice().getAmount(), equalTo(2500));
        
    }
}
