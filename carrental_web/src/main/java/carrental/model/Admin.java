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
public class Admin {

	@Id
	@GeneratedValue
	private Integer id;
	private String password;

	@OneToMany(mappedBy = "admin")
	private List<Booking> booking;

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