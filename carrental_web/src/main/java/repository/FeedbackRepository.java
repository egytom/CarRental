package repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Client;
import model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
	
	List<Feedback> findById(int id);

	List<Feedback> findByClient(Client client);
	
	Feedback create(int id, String text);
	
	Feedback update(Feedback feedback);

}
