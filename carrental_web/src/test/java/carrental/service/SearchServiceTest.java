package carrental.service;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import carrental.model.Car;
import carrental.model.Category;
import carrental.model.Type;
import carrental.model.Type.FuelType;
import carrental.model.Type.GearType;
import carrental.repository.CarRepository;

@RunWith(MockitoJUnitRunner.class)
public class SearchServiceTest {

	@InjectMocks
	SearchService searchService;
	
	@Mock
	CarRepository carRepository;

	/**
	 *
	 * Egyed Tamás Barnabás tesztesetei
	 */

	@Test
	public void testCarCreation() {
		
		//ARRANGE
		Car car = new Car(1, "Ferrari California", new Type(), new Category());
		
		when(carRepository.findByName("Ferrari California")).thenReturn(Arrays.asList(car));
			
		//ACT
		car = searchService.searchCarByName("Ferrari California").get(0); 
		Type type = new Type(2, GearType.auto, FuelType.gasoline);
		Category category = new Category();
		car.setType(type);
		car.setCategory(category);
		car.setRentalPrice(10000);
		
		//ASSERT
		double delta = 0.00001;
		assertThat((double) car.getRentalPrice(), closeTo(10000.0, delta));
		assertThat(car.getType(), equalTo(type));
		assertThat(car.getCategory(), equalTo(category));
		
	}

	@Test
	public void testTypeModification() {
		
		//ARRANGE
		Type type = new Type(5, GearType.manual, FuelType.electric);
		Car car = new Car(2, "Volvo", type, new Category());
		
		when(carRepository.findByType(type)).thenReturn(Arrays.asList(car));
			
		//ACT
		car = searchService.searchCarByType(type).get(0);
		type.setFuelType(FuelType.gasoline);
		type.setGearType(GearType.auto);
		type.setSeatNumber(7);
		
		//ASSERT
		assertThat(car.getType().getFuelType(), equalTo(FuelType.gasoline));
		assertThat(car.getType().getGearType(), equalTo(GearType.auto));
		assertThat(car.getType().getSeatNumber(), equalTo(7));
		
	}

	@Test
	public void testContainsBothCarList() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		//ARRANGE
		String methodName = "containsBothCarList";
		
		@SuppressWarnings("rawtypes")
		Class[] argClasses = new Class[2];
		
		argClasses[0] = List.class;
		argClasses[1] = List.class;
		
		Method method = searchService.getClass().getDeclaredMethod(methodName, argClasses);
		method.setAccessible(true);
		
		Car car1 = new Car(1, "Ferrari California 1", new Type(), new Category());
		Car car2 = new Car(2, "Ferrari California 2", new Type(), new Category());
		Car car3 = new Car(3, "Ferrari California 3", new Type(), new Category());
		Car car4 = new Car(4, "Ferrari California 4", new Type(), new Category());
		Car car5 = new Car(5, "Ferrari California 5", new Type(), new Category());
		
		List<Car> carList1 = new ArrayList<Car>();
		List<Car> carList2 = new ArrayList<Car>();
		
		carList1.add(car1);
		carList1.add(car2);
		carList1.add(car3);
		carList1.add(car4);
		carList2.add(car3);
		carList2.add(car4);
		carList2.add(car5);
			
		//ACT
		@SuppressWarnings("unchecked")
		List<Car> carResultList = (List<Car>) method.invoke(searchService, carList1, carList2);
		method.setAccessible(false);
		
		//ASSERT
		assertThat(carResultList.get(0).getName(), equalTo("Ferrari California 3"));
		assertThat(carResultList.get(1).getName(), equalTo("Ferrari California 4"));
		assertThat(carResultList.size(), equalTo(2));
		
	}

	/**
	 * Kurdi Botond tesztesete
	 */

	@Test
	public void testSearchCarByNameAndType() {
		
		Type type = new Type(2, GearType.manual, FuelType.gasoline);
		Car car = new Car(3, "Ferrari", type);
		
		when(carRepository.findByName	("Ferrari")).thenReturn(Arrays.asList(car));
		when(carRepository.findByType(type)).thenReturn(Arrays.asList(car));
		
		car = searchService.searchCarByNameAndType("Ferrari", type).get(0);
		
		assertThat(car.getName(), equalTo("Ferrari"));
		assertThat(car.getType().getSeatNumber(), equalTo(2));
		assertThat(car.getType().getGearType(), equalTo(GearType.manual));
		assertThat(car.getType().getFuelType(), equalTo(FuelType.gasoline));
		
	}

	/**
	 * Soós Sarolta tesztesetei
	 */

	@Test
	public void testSearchCarByCategory() throws Exception {

		//ARRANGE
		Category category = new Category("Volkswagen");
		Car car = new Car(15, "Polo", new Type(), category);

		when(carRepository.findByCategory(category)).thenReturn(Arrays.asList(car));

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

		when(carRepository.findByCategory(category)).thenReturn(Arrays.asList(car));
		when(carRepository.findByName("Passat")).thenReturn(Arrays.asList(car));

		//ACT
		car = searchService.searchCarByNameAndCategory("Passat", category).get(0);

		//ASSERT
		assertThat(car.getName(), equalTo("Passat"));
		assertThat(car.getCategory().getBrand(), equalTo("Volkswagen"));

	}

	/**
	 Somogyi Balázs tesztesetei
	 */

	@Test
    public void testSearchCarByCategoryAndType() throws Exception {

	    //ARRANGE
        Category category = new Category("Bugatti");
        Type type = new Type(2, GearType.manual, FuelType.gasoline);
        Car car = new Car(20, "Chiron", type, category);

        when(carRepository.findByCategory(category)).thenReturn(Arrays.asList(car));
        when(carRepository.findByType(type)).thenReturn(Arrays.asList(car));

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

        when(carRepository.findByCategory(category)).thenReturn(Arrays.asList(car));
        when(carRepository.findByType(type)).thenReturn(Arrays.asList(car));
        when(carRepository.findByName(carName)).thenReturn(Arrays.asList(car));

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