package carrental.service;

import carrental.model.Booking;
import carrental.model.Car;
import carrental.model.Client;
import carrental.model.Payment;
import carrental.repository.CarRepository;
import carrental.repository.ClientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase
public class LoyaltyPointServiceTDD {

    @Autowired
    LoyaltyPointService loyaltyPointService;

    @Autowired
    DiscountService discountService;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CarRepository carRepository;

    private Date fromDate;
    private Date toDate;
    private Client client;
    private Car car;
    private Payment payment;
    private Booking booking;

    @Test
    public void testLoyaltyPointsAdded() throws Exception{
        //ARRANGE
        initialize();

        //ACT
        loyaltyPointService.addLoyaltyPoints(client);

        //ASSERT
        assertThat(client.getLoyaltyPoint(), equalTo(5));

    }


    private void initialize() {
        Calendar cal = setCalendar(2018, 5);
        fromDate = cal.getTime();

        cal.set(Calendar.DAY_OF_MONTH, 13);
        toDate = cal.getTime();

        car = new Car();

        payment = new Payment(1, 10000);
        client = new Client(1, "Balázs", "email@email.hu", "+36");

        booking = new Booking(1, fromDate, toDate, car, payment, client);

        client.addBooking(booking);
    }

    private Calendar setCalendar(int year, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, Calendar.NOVEMBER);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal;
    }
}
