package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Type {
	private enum GearType {manual, auto};
	private enum FuelType {diesel, gasoline, electric};
	
	private GearType gearType;
	private FuelType fuelType;
	private int seatNumber;
}
