package carrental.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import carrental.model.Car;
import carrental.model.Category;
import carrental.model.Type;
import carrental.repository.CarRepository;

@Service
public class SearchService {
	
	@Autowired
	CarRepository carRepository;
	
	@Transactional
	public List<Car> searchCarByName(String name){
		return carRepository.findByName(name);
	}
	
	@Transactional
	public List<Car> searchCarByType(Type type){
		return carRepository.findByType(type);
	}
	
	@Transactional
	public List<Car> searchCarByCategory(Category category){
		return carRepository.findByCategory(category);
	}
	
	@Transactional
	public List<Car> searchCarByPrice(int maxPrice){
		return carRepository.findByRentalPrice(maxPrice);
	}
	
	@Transactional
	public List<Car> searchCarByNameAndType(String name, Type type){
		List<Car> carsByName = carRepository.findByName(name);
		List<Car> carsByType = carRepository.findByType(type);
					
		return containsBothCarList(carsByName, carsByType);
	}
	
	@Transactional
	public List<Car> searchCarByNameAndCategory(String name, Category category){
		List<Car> carsByName = carRepository.findByName(name);
		List<Car> carsByCategory = carRepository.findByCategory(category);
					
		return containsBothCarList(carsByName, carsByCategory);
	}
	
	@Transactional
	public List<Car> searchCarByCategoryAndType(Category category, Type type){
		List<Car> carsByCategory = carRepository.findByCategory(category);
		List<Car> carsByType = carRepository.findByType(type);
					
		return containsBothCarList(carsByCategory, carsByType);
	}
	
	@Transactional
	public List<Car> searchCarByNameAndCategoryAndType(String name, Category category, Type type){
		List<Car> carsByName = carRepository.findByName(name);
		List<Car> carsByCategory = carRepository.findByCategory(category);
		List<Car> carsByType = carRepository.findByType(type);
		
		return containsBothCarList(carsByName, 
								   containsBothCarList(carsByCategory, carsByType));
	}

	private List<Car> containsBothCarList(List<Car> list1, List<Car> list2) {
		List<Car> cars = new ArrayList<Car>();
		
		for(Car car1 : list1)
			for(Car car2 : list2)
				if (car1.equals(car2))
					cars.add(car1);
		
		return cars;
	}
}
