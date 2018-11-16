package carrental.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import carrental.model.Feedback;
import carrental.repository.FeedbackRepository;

@Controller
public class FeedbackController {
	
	@Autowired
	FeedbackRepository feedbackRepository;
	
	@PostMapping("/adminfeedback")
	public String redirectFeedback(Map<String, Object> model) {
		return "redirect:/adminfeedback";
	}
	
	@PostMapping("/adminfeedback/create")
	public String createFeedback(Map<String, Object> model, Feedback feedback) {
		feedbackRepository.save(feedback);
		return "redirect:/adminfeedback";
	}
	
//	@PostMapping
//	public String deleteFeedback(Map<String, Object> model, int feedbackID) {
//		feedbackRepository.deleteById(feedbackID);
//		return "redirect:/feedback";
//	}
	
	@GetMapping("/adminfeedback")
	public String getAllFeedback(Map<String, Object> model) {
		List<Feedback> allFeedback = feedbackRepository.findAll();
		model.put("feedbacks", allFeedback);
		model.put("feedback", new Feedback());
		return "adminfeedback";
	}
	
//	@PostMapping
//	public String updateFeedback(Map<String, Object> model, String feedbackText, int feedbackId){
//		Optional<Feedback> feedback = feedbackRepository.findById(feedbackId); //ezt nem értem :D
//		feedback.get().setText(feedbackText);
//		return "redirect:/feedback";
//	}

}
