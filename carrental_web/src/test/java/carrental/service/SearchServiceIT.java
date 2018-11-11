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

import carrental.model.Car;
import carrental.model.Type;
import carrental.model.Type.FuelType;
import carrental.model.Type.GearType;
import carrental.repository.CarRepository;
import carrental.repository.CategoryRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase
public class SearchServiceIT {
	
	@Autowired
	SearchService searchService;
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Test
	public void testSearchCarByName() throws Exception {

		//ARRANGE
		Car car1 = new Car(1, "Ferrari", new Type());
		Car car2 = new Car(2, "Porsche", new Type());
		car1 = carRepository.save(car1);
		car2 = carRepository.save(car2);
				
		//ACT
		car1 = searchService.searchCarByName("Ferrari").get(0);
		car2 = searchService.searchCarByName("Porsche").get(0);
		
		//ASSERT
		assertThat(car1.getName(), equalTo("Ferrari"));
		assertThat(car2.getName(), equalTo("Porsche"));
		
	}
	
	@Test
	public void testSearchCarByType() throws Exception {

		//ARRANGE
		Type type = new Type(2, GearType.manual, FuelType.gasoline);
		Car car = new Car(3, "Ferrari", type);
		car = carRepository.save(car);
				
		//ACT
		car = searchService.searchCarByType(type).get(0);
		
		//ASSERT
		assertThat(car.getName(), equalTo("Ferrari"));
	
	}
	
	@Test
	public void testSearchCarByPrice() throws Exception {

		//ARRANGE
		Car car1 = new Car(1, "Ferrari", new Type());
		Car car2 = new Car(2, "Porsche", new Type());
		
		car1.setRentalPrice(5000);
		car2.setRentalPrice(5000);
		
		car1 = carRepository.save(car1);
		car2 = carRepository.save(car2);
				
		//ACT
		car1 = searchService.searchCarByPrice(5000).get(0);
		car2 = searchService.searchCarByPrice(5000).get(1);
		
		//ASSERT
		assertThat(car1.getName(), equalTo("Ferrari"));
		assertThat(car2.getName(), equalTo("Porsche"));
		
		assertThat(car1.getRentalPrice(), equalTo(5000));
		assertThat(car2.getRentalPrice(), equalTo(5000));
		
	}
	
}
