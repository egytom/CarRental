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
public class Car {
	
	@Id
	@GeneratedValue
	private int id;
	private int rentalPrice;
	private String name;

	@OneToOne
	private Type type;

	@OneToOne
	private Category category;

	@OneToOne(mappedBy = "car")
	private Booking booking;

	public Car() {
	}

}
