package carrental.service;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import carrental.model.Category;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase
public class SearchServiceIT {
	
	@Autowired
	SearchService searchService;
	
	@Autowired
	CarRepository carRepository;

	/**
	 *
	 * Egyed Tamás Barnabás tesztesetei
	 */

	@Test
	public void testSearchCarByName() throws Exception {

		//ARRANGE
		Car car1 = new Car(1, "Ferrari", new Type(), new Category());
		Car car2 = new Car(2, "Porsche", new Type(), new Category());
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
		Car car = new Car(3, "Ferrari", type, new Category());
		car = carRepository.save(car);
				
		//ACT
		car = searchService.searchCarByType(type).get(0);
		
		//ASSERT
		assertThat(car.getName(), equalTo("Ferrari"));
	
	}

	@Test
	public void testSearchCarByPrice() throws Exception {

		//ARRANGE
		Car car1 = new Car(1, "Ferrari", new Type(), new Category());
		Car car2 = new Car(2, "Porsche", new Type(), new Category());
		
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

	/**
	 * Kurdi Botond tesztesete
	 */

	@Test
	public void testSearchCarByNameAndType() {
		Type type = new Type(2, GearType.manual, FuelType.gasoline);
		Car car = new Car(3, "Ferrari", type);
		car = carRepository.save(car);
		
		car = searchService.searchCarByNameAndType("Ferrari", type).get(0);
		
		assertThat(car.getName(), equalTo("Ferrari"));
		assertThat(car.getType(), equalTo(type));
	}

	/**
	 *
	 * Soós Sarolta tesztesetei
	 */

	@Test
	public void testSearchCarByCategory() throws Exception {

		//ARRANGE
		Category category = new Category("Volkswagen");
		Car car = new Car(15, "Polo", new Type(), category);
		carRepository.save(car);

		//ACT
		car = searchService.searchCarByCategory(category).get(0);

		//ASSERT
		assertThat(car.getCategory().getBrand(), equalTo("Volkswagen"));
	}

	@Test
	public void testSearchCarByNameAndCategory() throws Exception {

		//ARRANGE
		Category category = new Category("Volkswagen");
		Car car = new Car(15, "Passat", new Type(), category);
		carRepository.save(car);

		//ACT
		car = searchService.searchCarByNameAndCategory("Passat", category).get(0);

		//ASSERT
		assertThat(car.getName(), equalTo("Passat"));
		assertThat(car.getCategory().getBrand(), equalTo("Volkswagen"));

	}

    /**
     * Somogyi Balázs tesztesetei
     */

    @Test
    public void testSearchCarByCategoryAndType() throws Exception {

        //ARRANGE
        Category category = new Category("Bugatti");
        Type type = new Type(2, GearType.manual, FuelType.gasoline);
        Car car = new Car(20, "Chiron", type, category);

        carRepository.save(car);

        //ACT
        car = searchService.searchCarByCategoryAndType(category, type).get(0);

        //ASSERT
        assertThat(car.getType().getFuelType(), equalTo(FuelType.gasoline));
        assertThat(car.getType().getGearType(), equalTo(GearType.manual));
        assertThat(car.getType().getSeatNumber(), equalTo(2));

        assertThat(car.getCategory().getBrand(), equalTo("Bugatti"));
    }

    @Test
    public void testSearchCarByNameAndCategoryAndType() throws Exception {

        //ARRANGE
        Category category = new Category("Bugatti");
        Type type = new Type(2, GearType.manual, FuelType.gasoline);
        String carName = "Chiron";

        Car car = new Car(20, carName, type, category);

        carRepository.save(car);

        //ACT
        car = searchService.searchCarByNameAndCategoryAndType(carName, category, type).get(0);

        //ASSERT
        assertThat(car.getType().getFuelType(), equalTo(FuelType.gasoline));
        assertThat(car.getType().getGearType(), equalTo(GearType.manual));
        assertThat(car.getType().getSeatNumber(), equalTo(2));

        assertThat(car.getCategory().getBrand(), equalTo("Bugatti"));
        assertThat(car.getName(), equalTo("Chiron"));

    }
}
