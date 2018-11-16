package carrental.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminBookingController {

	@PostMapping("/adminbooking")
	public String createAdminPageFromPost(Map<String, Object> model) {

		return "redirect:/adminbooking";

	}

	@GetMapping("/adminbooking")
	public String getAllBookings(Map<String, Object> model) {
		
		return "adminbooking";

	}

}
