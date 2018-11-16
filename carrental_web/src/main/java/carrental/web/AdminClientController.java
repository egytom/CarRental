package carrental.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminClientController {

	@PostMapping("/adminclient")
	public String createAdminPageFromPost(Map<String, Object> model) {

		return "redirect:/adminclient";

	}

	@GetMapping("/adminclient")
	public String getAllClients(Map<String, Object> model) {
		
		return "adminclient";

	}

}
