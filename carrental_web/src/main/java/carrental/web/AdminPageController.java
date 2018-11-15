package carrental.web;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import carrental.model.Car;
import carrental.model.Category;
import carrental.model.Type;
import carrental.model.Type.FuelType;
import carrental.model.Type.GearType;
import carrental.repository.CarRepository;
import carrental.service.SearchService;

@Controller
public class AdminPageController {
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	SearchService searchService;
	
	@PostMapping("/admin")
	public String createAdminPageFromPost(Map<String, Object> model) {
		
		return "redirect:/admin";
		
	}
	
	@GetMapping("/admin")
	public String getAllCars(Map<String, Object> model) {
		
		List<Car> allCars = carRepository.findAll();
		model.put("cars", allCars);	
		model.put("car", new Car());
		return "admin";
		
	}
	
	@PostMapping("/admin/newcar")
	public String createCar(Map<String, Object> model, Car car) {
		
		car.setCategory(new Category());
		car.setType(new Type(5, GearType.auto, FuelType.gasoline));
		carRepository.save(car);
		return "redirect:/admin";
		
	}
	
	@PostMapping("/admin/found")
	public String findCar(Map<String, Object> model, Car car) {

		List<Car> foundedCars = searchService.searchCarByName(car.getName());
		model.put("foundCarsByName", foundedCars);	
		return getAllCars(model);
		
	}
	
	@PostMapping("/admin/deleted")
	public String deleteCar(Map<String, Object> model, Car car) {
		
		carRepository.deleteById(car.getId());	
		return getAllCars(model);
		
	}
	
	@PostMapping("/admin/updated")
	public String updateCar(Map<String, Object> model, Car car) {
		
		try {
		Optional<Car> foundedOptionalCar = carRepository.findById(car.getId());
		updateExistingCar(car, foundedOptionalCar);
		} catch(Exception e) {
			System.out.println(e.getMessage() + "\n" + e.getStackTrace());
		}
		return getAllCars(model);
		
	}

	private void updateExistingCar(Car car, Optional<Car> foundedOptionalCar) {
		
		Car foundedCar = foundedOptionalCar.get();
		foundedCar.setName(car.getName());
		foundedCar.setRentalPrice(car.getRentalPrice());
		carRepository.save(foundedCar);
		
	}
	
}
