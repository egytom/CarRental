package carrental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import carrental.model.Article;

public interface ArticleRepository  extends JpaRepository<Article, Integer> {
    
	List<Article> findByAuthor(String author);
    
}