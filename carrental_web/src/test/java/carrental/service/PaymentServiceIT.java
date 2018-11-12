package carrental.service;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import carrental.model.Booking;
import carrental.model.Car;
import carrental.model.Category;
import carrental.model.Client;
import carrental.model.Payment;
import carrental.model.Type;
import carrental.repository.BookingRepository;
import carrental.repository.ClientRepository;
import carrental.repository.PaymentRepository;

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

    @Test
    public void testPay() throws Exception {

        //ARRANGE
        Client client = new Client("Teszt Kliens", "teszt@kliens.hu");
        Payment payment = new Payment(1, 5000);
        Booking booking = new Booking(1, new Car(1, "Opel Vectra",
                new Type(5, Type.GearType.auto, Type.FuelType.diesel),
                new Category("Volkswagen")), payment, client);

        paymentRepository.save(payment);

        client.addBooking(booking);
        clientRepository.save(client);
        //bookingRepository.save(booking);

        //ACT
        paymentService.pay(client, 1); //TODO

        //ASSERT
        booking = bookingRepository.findById(1).get();

        assertThat(booking.getPrice().getAmount(), equalTo(payment.getAmount()));
        assertThat(client.getBooking(), hasItems(booking));
    }
}
