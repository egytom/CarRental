package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car {
	private int id;
	private int rentalPrice;
	private String name;
	
	private Type type;
	private Category category;
}
