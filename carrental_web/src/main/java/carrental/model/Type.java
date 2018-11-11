package carrental.model;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Type {
	
	private int seatNumber;
	
	public enum GearType {manual, auto};
	public enum FuelType {diesel, gasoline, electric};
	
	@Enumerated(EnumType.STRING)
	private GearType gearType;
	
	@Enumerated(EnumType.STRING)
	private FuelType fuelType;

}
