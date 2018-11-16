package carrental.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminPaymentController {

	@PostMapping("/adminpayment")
	public String createAdminPageFromPost(Map<String, Object> model) {

		return "redirect:/adminpayment";

	}

	@GetMapping("/adminpayment")
	public String getAllPayments(Map<String, Object> model) {
		
		return "adminpayment";

	}

}
