package carrental.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car {
	
	@Id
	@GeneratedValue
	private int id;
	private int rentalPrice;
	private String name;
	
	@Embedded
	private Type type;

	@Embedded
	private Category category;

	@ManyToOne
	private Booking booking;

	public Car(int id, String name, Type type, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.category = category;
	}

	public Car(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Car(int id, String name, Type type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
	}

}
