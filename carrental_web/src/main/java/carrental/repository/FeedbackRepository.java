package carrental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import carrental.model.Client;
import carrental.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
	
	List<Feedback> findByClient(Client client);

}