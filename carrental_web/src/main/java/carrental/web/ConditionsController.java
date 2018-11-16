package carrental.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/conditions")
public class ConditionsController {
	
	@GetMapping
	public String getConditions(Map<String, Object> model) {
		
		return "conditions";
		
	}
	
	@PostMapping
	public String createConditions(Map<String, Object> model) {
		
		return "redirect:/conditions";
		
	}
	
}
