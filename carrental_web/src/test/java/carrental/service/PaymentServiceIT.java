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

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class PaymentServiceIT {

    @Autowired
    PaymentService paymentService;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CarRepository carRepository;

    @Test
    public void testPay() throws Exception {

        //ARRANGE
        Car car = new Car(1, "Opel Vectra", new Type(5, Type.GearType.auto, Type.FuelType.diesel), new Category("Volkswagen"));
        Client client = new Client("Teszt Kliens", "teszt@kliens.hu");
        Payment payment = new Payment(1, 5000);

        car = carRepository.save(car);
        client = clientRepository.save(client);
        payment = paymentRepository.save(payment);

        Booking booking = new Booking(1, car, payment, client);

        booking = bookingRepository.save(booking);
        client.addBooking(booking);

        //ACT
        paymentService.pay(client, booking.getId());

        //ASSERT
        booking = bookingRepository.findById(booking.getId()).get();

        assertThat(booking.getPrice().getAmount(), equalTo(payment.getAmount()));
        assertTrue(!(client.getBooking().contains(booking)));
    }
}
