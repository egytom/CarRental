package model;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Admin {
	
	public Admin() {
	}
	
	public Admin(int id, String password, ArrayList<Booking> booking) {
		super();
		this.id = id;
		this.password = password;
		this.booking = booking;
	}

	public Admin(int id) {
		super();
		this.id = id;
	}

	public Admin(int id, String password) {
		super();
		this.id = id;
		this.password = password;
	}

	public Admin(int id, ArrayList<Booking> booking) {
		super();
		this.id = id;
		this.booking = booking;
	}

	@Id
	@GeneratedValue
    private int id;
    private String password;
    
    @OneToMany(mappedBy = "admin")
    private ArrayList<Booking> booking;
}