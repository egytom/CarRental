package carrental.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import carrental.model.Car;
import carrental.repository.CarRepository;

@Controller
@RequestMapping("/homepage")
public class HomePageController {
	
	@Autowired
	CarRepository carRepository;
	
	@GetMapping
	public String getHomepage(Map<String, Object> model) {
		
		return "homepage";
		
	}
	
	@PostMapping
	public String createHompage(Map<String, Object> model, Car car) {
		
		return "redirect:/homepage";
		
	}
	
}
