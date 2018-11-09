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
public class Type {
	
	@Id
	@GeneratedValue
	private int id;
	
	private enum GearType {manual, auto};
	private enum FuelType {diesel, gasoline, electric};
	
	private GearType gearType;
	private FuelType fuelType;
	private int seatNumber;

	@OneToOne(mappedBy = "type")
	private Car car;

	public Type() {
	}

}
