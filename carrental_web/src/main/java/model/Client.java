package model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Client {
	private int id;
	private String name;
	private String emailAddress;
	private String phoneNumber;
	
	private Booking booked;
	private Payment payment;
	
}
