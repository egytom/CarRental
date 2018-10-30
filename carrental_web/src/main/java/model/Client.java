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
	
	//FIXME: biztos, hogy egy kliensnek csak egy bookingja lehet?
	private Booking booked;
	//FIXME: ha a bookingban már ott van a payment, miért van szükség rá a kliensben is?
	private Payment payment;
	
}
