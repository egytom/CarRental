package carrental.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import carrental.model.Car;
import carrental.model.Category;
import carrental.model.Type;
import carrental.service.SearchService;

@Controller
public class CarsController {

	@Autowired
	SearchService searchService;

	@GetMapping("/cars")
	public String getCars(Map<String, Object> model) {

		model.put("car", new Car());
		model.put("type", new Type());
		model.put("category", new Category());
		return "cars";

	}

	@PostMapping("/cars")
	public String createCars(Map<String, Object> model) {

		return "redirect:/cars";

	}

	@PostMapping("/carssearched")
	public String searchCars(Map<String, Object> model, Car car, Type type, Category category) {
		
		List<Car> searchedCars = searchCarsByData(car.getName(), type.getSeatNumber(), category.getBrand());
		model.put("searchedCars", searchedCars);
		model.put("seatNumber", type.getSeatNumber());
		model.put("brand", category.getBrand().equals("")? "-" : category.getBrand());
		return getCars(model);

	}

	private List<Car> searchCarsByData(String name, int seatNumber, String carBrand) {
		
		List<Car> searchedCars;
		
		if(carBrand.equals("")) 
			searchedCars = searchService.searchCarByNameAndSeatNumber(name, seatNumber);
		else 
			searchedCars = searchService.searchCarByNameAndCarBrandAndSeatNumber(name, carBrand, seatNumber);
		
		return searchedCars;
		
	}

}
