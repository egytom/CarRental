package carrental.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Feedback {
	
	@Id
	@GeneratedValue
	private int id;
	private String text;

	@OneToOne
	private Client client;

	@OneToOne(mappedBy = "feedback")
	private Booking booking;

	public Feedback() {
	}
	
	public Feedback(String text) {
		super();
		this.text = text;
	}

}