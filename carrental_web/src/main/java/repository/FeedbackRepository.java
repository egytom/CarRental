package repository;


import java.util.List;

import model.Feedback;

public interface FeedbackRepository {
	List<Feedback> findById(int id);
	
	Feedback create(int id, String text);
	
	Feedback update(Feedback feedback);

    boolean delete(Feedback feedback);

    void save(Feedback feedback);
}
