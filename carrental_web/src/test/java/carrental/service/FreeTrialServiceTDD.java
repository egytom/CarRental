package carrental.service;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import carrental.model.Booking;
import carrental.model.Car;
import carrental.model.Category;
import carrental.model.Client;
import carrental.model.Feedback;
import carrental.model.Type;
import carrental.model.Type.FuelType;
import carrental.model.Type.GearType;
import carrental.repository.BookingRepository;
import carrental.repository.CarRepository;
import carrental.repository.ClientRepository;
import carrental.repository.PaymentRepository;

/**
*
* Egyed Tamás Barnabás - TDD
*/

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase
public class FreeTrialServiceTDD {
	
	@Autowired
	FreeTrialService freeTrialService;
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Test
	public void testFreeTrialCreation() throws Exception {

		//ARRANGE
		Client client = new Client(1, "Egyed Tamás", "egyed.t@gmail.com", "063012345678", new ArrayList<Booking>(), new Feedback());
		Car car = new Car(1, "Ferrari", 
				new Type(2, GearType.manual, FuelType.gasoline), 
				new Category("Ferrari"));
				
		//ACT
		@SuppressWarnings("deprecation")
		Booking booking = freeTrialService.createFreeTrial(client, car, new Date(2018, 11, 13), new Date(2018, 11, 14));
		
		//ASSERT
		assertThat(booking.getClass(), equalTo(Booking.class));
		
	}
	
	@Test
	public void testFreeTrialCreationWithClient() throws Exception {

		//ARRANGE
		Client client = new Client(1, "Egyed Tamás", "egyed.t@gmail.com", "063012345678");
		Car car = new Car(1, "Ferrari", 
				new Type(2, GearType.manual, FuelType.gasoline), 
				new Category("Ferrari"));
				
		//ACT
		@SuppressWarnings("deprecation")
		Booking booking = freeTrialService.createFreeTrial(client, car, new Date(2018, 11, 13), new Date(2018, 11, 14));
		
		//ASSERT
		assertThat(booking.getClient().getName(), equalTo("Egyed Tamás"));
		
	}
	
	@Test
	public void testFreeTrialCreationWithClientAndCar() throws Exception {

		//ARRANGE
		Client client = new Client(1, "Egyed Tamás", "egyed.t@gmail.com", "063012345678");
		Car car = new Car(1, "Ferrari", 
				new Type(2, GearType.manual, FuelType.gasoline), 
				new Category("Ferrari"));
				
		//ACT
		@SuppressWarnings("deprecation")
		Booking booking = freeTrialService.createFreeTrial(client, car, new Date(2018, 11, 13), new Date(2018, 11, 14));
		
		//ASSERT
		assertThat(booking.getClient().getName(), equalTo("Egyed Tamás"));
		assertThat(booking.getCar().getName(), equalTo("Ferrari"));
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testFreeTrialFromAndToDate() throws Exception {

		//ARRANGE
		Client client = new Client(1, "Egyed Tamás", "egyed.t@gmail.com", "063012345678");
		Car car = new Car(2, "Porsche", 
				new Type(4, GearType.manual, FuelType.gasoline), 
				new Category("Porsche"));
				
		//ACT
		Booking booking = freeTrialService.createFreeTrial(client, car, new Date(2018, 11, 13), new Date(2018, 11, 14));
		
		//ASSERT
		assertThat(booking.getFromDate(), equalTo(new Date(2018, 11, 13)));
		assertThat(booking.getFromDate(), equalTo(new Date(2018, 11, 14)));
		
	}
	
}
