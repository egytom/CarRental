package carrental.service;

import carrental.model.*;
import carrental.repository.BookingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {

    @InjectMocks
    PaymentService paymentService;

    @Mock
    BookingRepository bookingRepository;

    @Test
    public void testPay() throws Exception {    // TODO még nem jó

        //ARRANGE
        Client client = new Client("Teszt Kliens", "teszt@kliens.hu");
        Booking booking = new Booking(1, new Car(1, "Opel Vectra", new Type(5, Type.GearType.auto, Type.FuelType.diesel), new Category("Volkswagen")), new Payment(1, 5000));
        client.addBooking(booking);

        when(bookingRepository.findById(1)).thenReturn(java.util.Optional.of(booking));

        //ACT
        paymentService.pay(new Client("Teszt Kliens", "teszt@kliens,hu"), 1);

        //ASSERT
        assertThat(booking.getPrice(), null);
        assertTrue(!(client.getBooking().contains(booking)));
    }

}
