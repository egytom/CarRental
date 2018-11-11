package carrental.service;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

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
	
	@Test
	public void testFreeTrialCreation() throws Exception {

		//ARRANGE
		Client client = new Client(1, "Egyed Tamás", "egyed.t@gmail.com", "063012345678", new ArrayList<Booking>(), new Feedback());
		Car car = new Car(1, "Ferrari", 
				new Type(2, GearType.manual, FuelType.gasoline), 
				new Category("Ferrari"));
				
		//ACT
		Booking booking = freeTrialService.createFreeTrial(client, car);
		
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
		Booking booking = freeTrialService.createFreeTrial(client, car);
		
		//ASSERT
		assertThat(booking.getClient().getName(), equalTo("Egyed Tamás"));
		
	}
	
}
