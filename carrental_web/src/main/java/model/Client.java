package model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Client {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String emailAddress;
	private String phoneNumber;
	
	@OneToMany(mappedBy="client")
	private ArrayList<Booking> booking;
	
}
