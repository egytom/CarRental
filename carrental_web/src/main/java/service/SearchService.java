package service;

import java.util.ArrayList;
import java.util.List;

import model.Car;
import model.Category;
import model.Type;
import repository.CarRepository;

public class SearchService {
	CarRepository carRepository;
	
	public List<Car> searchCarByName(String name){
		return carRepository.findByName(name);
	}
	
	public List<Car> searchCarByType(Type type){
		return carRepository.findByType(type);
	}
	
	public List<Car> searchCarByCategory(Category category){
		return carRepository.findByCategory(category);
	}
	
	public List<Car> searchCarByPrice(int maxPrice){
		
		//FIXME: nem hatékony az összeset beolvasni memóriába, és ott szûrni
		//ugyanúgy a repositoryba kell egy finder, mint a többi keresésnél
		List<Car> allCars = carRepository.getAll();
		List<Car> cars = new ArrayList<Car>();
		
		for (Car car : allCars)
			if (car.getRentalPrice() <= maxPrice)
				cars.add(car);
		
		return cars;
	}
	
	public List<Car> searchCarByNameAndType(String name, Type type){
		List<Car> carsByName = carRepository.findByName(name);
		List<Car> carsByType = carRepository.findByType(type);
					
		return containsBothCarList(carsByName, carsByType);
	}
	
	public List<Car> searchCarByNameAndCategory(String name, Category category){
		List<Car> carsByName = carRepository.findByName(name);
		List<Car> carsByCategory = carRepository.findByCategory(category);
					
		return containsBothCarList(carsByName, carsByCategory);
	}

	public List<Car> searchCarByCategoryAndType(Category category, Type type){
		List<Car> carsByCategory = carRepository.findByCategory(category);
		List<Car> carsByType = carRepository.findByType(type);
					
		return containsBothCarList(carsByCategory, carsByType);
	}
	
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
