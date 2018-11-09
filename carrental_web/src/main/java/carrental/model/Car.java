package carrental.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	
	private Type type;
	private Category category;
	
}
