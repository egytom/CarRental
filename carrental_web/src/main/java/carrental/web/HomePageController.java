package carrental.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/homepage")
public class HomePageController {
	
	@GetMapping
	public String getHomepage(Map<String, Object> model) {
		
		return "homepage";
		
	}
	
	@PostMapping
	public String createHompage(Map<String, Object> model) {
		
		return "redirect:/homepage";
		
	}
	
}
