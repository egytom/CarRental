package carrental.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
	
	public Feedback(int id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

}