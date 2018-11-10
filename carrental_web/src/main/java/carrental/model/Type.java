package carrental.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Type {
	
	@Id
	@GeneratedValue
	private int id;
	
	public enum GearType {manual, auto};
	public enum FuelType {diesel, gasoline, electric};
	
	private GearType gearType;
	private FuelType fuelType;
	private int seatNumber;

	@OneToMany(mappedBy = "type")
	private List<Car> car;

}
