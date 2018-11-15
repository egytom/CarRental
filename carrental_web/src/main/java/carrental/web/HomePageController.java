package carrental.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import carrental.model.Car;
import carrental.model.Category;
import carrental.model.Type;
import carrental.model.Type.FuelType;
import carrental.model.Type.GearType;
import carrental.repository.CarRepository;

@Controller
@RequestMapping("/homepage")
public class HomePageController {
	
	@Autowired
	CarRepository carRepository;
	
	@GetMapping
	public String getAllCars(Map<String, Object> model) {
		
		List<Car> allCars = carRepository.findAll();
		model.put("cars", allCars);	
		model.put("car", new Car());
		return "homepage";
		
	}
	
	@PostMapping
	public String createCar(Map<String, Object> model, Car car) {
		
		car.setCategory(new Category());
		car.setType(new Type(5, GearType.auto, FuelType.gasoline));
		carRepository.save(car);
		return "redirect:/homepage";
		
	}
	
}
