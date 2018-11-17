package carrental.web;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import carrental.model.Car;
import carrental.model.Client;
import carrental.model.Feedback;
import carrental.repository.ClientRepository;
import carrental.repository.FeedbackRepository;
import carrental.service.FeedbackService;

@Controller
public class AdminFeedbackController {

	@Autowired
	FeedbackRepository feedbackRepository;
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	FeedbackService feedbackService;
	

	@PostMapping("/adminfeedback")
	public String redirectFeedback(Map<String, Object> model) {
		return "redirect:/adminfeedback";
	}

	@PostMapping("/adminfeedback/create")
	public String createFeedback(Map<String, Object> model, Feedback feedback, String name) {
		try {
			Client targetClient = clientRepository.findByName(name).get(0);
			feedback.setClient(targetClient);
			feedbackRepository.save(feedback);
		} catch (Exception e) {
			System.out.println(e.getMessage() + "\n" + e.getStackTrace());
		}
		return "redirect:/adminfeedback";
	}

	@PostMapping("/adminfeedback/delete")
	public String deleteFeedback(Map<String, Object> model, Feedback feedback) {
		try {
			feedbackRepository.deleteById(feedback.getId());
		} catch (Exception e) {
			System.out.println(e.getMessage() + "\n" + e.getStackTrace());
		}

		return getAllFeedback(model);
	}

	@GetMapping("/adminfeedback")
	public String getAllFeedback(Map<String, Object> model) {
		List<Feedback> allFeedbacks = feedbackRepository.findAll();
		model.put("feedbacks", allFeedbacks);
		model.put("feedback", new Feedback());
		return "adminfeedback";
	}

	@PostMapping("/adminfeedback/update")
	public String updateFeedback(Map<String, Object> model, Feedback feedback) {
		try {
			Optional<Feedback> feedbacks = feedbackRepository.findById(feedback.getId());
			Feedback feedbackFounded = feedbacks.get();
			feedbackFounded.setText(feedback.getText());
			feedbackRepository.save(feedbackFounded);
		} catch (Exception e) {
			System.out.println(e.getMessage() + "\n" + e.getStackTrace());
		}
		return getAllFeedback(model);
	}

	@PostMapping("/adminfeedback/find")
	public String sendFeedbackById(Map<String, Object> model, Feedback feedback) {
		try {
			Optional<Feedback> foundedFeedbacks = feedbackRepository.findById(feedback.getId());
			Feedback foundedFeedback = foundedFeedbacks.get();
			feedbackService.send(foundedFeedback);
			model.put("result", "Sikeres küldés");
			
		} catch (Exception e) {
			model.put("result", "Sikertelen küldés");
			System.out.println(e.getMessage() + "\n" + e.getStackTrace());
		}
		return getAllFeedback(model);

	}

}
