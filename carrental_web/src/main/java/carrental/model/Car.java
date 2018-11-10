package carrental.model;

import javax.persistence.*;

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
	private Integer id;
	private int rentalPrice;
	private String name;

	@ManyToOne
	private Type type;

	@ManyToOne
	private Category category;

	@OneToMany(mappedBy = "car")
	private Booking booking;

	public Car(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}
