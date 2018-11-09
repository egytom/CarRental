package carrental.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Type {
	
	@Id
	@GeneratedValue
	private int id;
	
	private enum GearType {manual, auto};
	private enum FuelType {diesel, gasoline, electric};
	
	private GearType gearType;
	private FuelType fuelType;
	private int seatNumber;

	@OneToMany(mappedBy = "type")
	private List<Car> car;

	public Type() {
	}

}
