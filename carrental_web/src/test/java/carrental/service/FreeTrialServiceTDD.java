package carrental.service;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import carrental.model.Booking;
import carrental.model.Car;
import carrental.model.Client;

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
		Client client = new Client();
		Car car = new Car();
				
		//ACT
		Booking booking = freeTrialService.createFreeTrial(client, car);
		
		//ASSERT
		assertThat(booking.getClass(), equalTo(Booking.class));
		
	}
	
}
