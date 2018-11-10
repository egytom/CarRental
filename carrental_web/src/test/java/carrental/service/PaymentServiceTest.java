package carrental.service;

import carrental.model.Booking;
import carrental.model.Car;
import carrental.model.Client;
import carrental.model.Payment;
import carrental.repository.BookingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {

    @InjectMocks
    PaymentService paymentService;

    @Mock
    BookingRepository bookingRepository;

    @Test
    public void testPay() throws Exception {

        //ARRANGE
        Client client = new Client("Teszt Kliens", "teszt@kliens.hu");
        Booking booking = new Booking(1, new Car(1, "Opel Vectra"), new Payment(1, 5000));
        client.addBooking(booking);

        when(bookingRepository.findById(1)).thenReturn(Arrays.asList(booking));

        //ACT
        paymentService.pay(new Client("Teszt Kliens", "teszt@kliens,hu"), 1);

        //ASSERT
        assertThat(booking.getPrice(), null);
        assertTrue(!(client.getBooking().contains(booking)));
    }

}
