package carrental.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Car {
	
	@Id
	@GeneratedValue
	private int id;
	private int rentalPrice;
	private String name;

	@ManyToOne
	private Type type;

	@ManyToOne
	private Category category;

	@OneToOne(mappedBy = "car")
	private Booking booking;

	public Car() {
	}

}
