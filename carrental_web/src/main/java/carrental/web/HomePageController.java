package carrental.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import carrental.model.Article;
import carrental.repository.ArticleRepository;

@Controller
@RequestMapping("/homepage")
public class HomePageController {
	
	@Autowired
	ArticleRepository articleRepository;
	
	@GetMapping
	public String getHomepage(Map<String, Object> model) {
		
		List<Article> news = articleRepository.findAll();
		model.put("news", news);
		return "homepage";
		
	}
	
	@PostMapping
	public String createHompage(Map<String, Object> model) {
		
		return "redirect:/homepage";
		
	}
	
}
