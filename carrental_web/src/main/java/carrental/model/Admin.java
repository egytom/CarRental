package carrental.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class Admin {

	@Id
	@GeneratedValue
	private int id;
	private String password;

	@OneToMany(mappedBy = "admin")
	private List<Booking> booking;
	
	public Admin() {
	}
	
	public Admin(int id, String password, List<Booking> booking) {
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

	public Admin(int id, List<Booking> booking) {
		super();
		this.id = id;
		this.booking = booking;
	}

}