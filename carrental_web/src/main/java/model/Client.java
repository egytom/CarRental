package model;

import java.awt.List;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Client {
	private int id;
	private String name;
	private String emailAddress;
	private String phoneNumber;
	
	private ArrayList<Booking> booked;
	
}
