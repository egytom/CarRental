package carrental.service;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import carrental.model.Booking;
import carrental.model.Car;
import carrental.model.Category;
import carrental.model.Client;
import carrental.model.Payment;
import carrental.model.Type;
import carrental.repository.BookingRepository;
import carrental.repository.PaymentRepository;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {

    @InjectMocks
    PaymentService paymentService;

    @Mock
    BookingRepository bookingRepository;

    @Mock
    PaymentRepository paymentRepository;

    @Test
    public void testPay() throws Exception {

        //ARRANGE
        Client client = new Client("Teszt Kliens", "teszt@kliens.hu");
        Payment payment = new Payment(1, 5000);
        Booking booking = new Booking(1, new Car(1, "Opel Vectra",
                new Type(5, Type.GearType.auto, Type.FuelType.diesel),
                new Category("Volkswagen")), payment);
        client.addBooking(booking);

        // paymentRepository.save(payment);

        when(bookingRepository.findById(1)).thenReturn(java.util.Optional.of(booking));

        //ACT
        paymentService.pay(client, 1);

        //ASSERT
        assertThat(booking.getPrice().getAmount(), equalTo(payment.getAmount()));
        assertTrue(!(client.getBooking().contains(booking)));
    }

}
