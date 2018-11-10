package carrental.model;

import java.util.List;

import javax.persistence.*;

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
	private List<Booking> booking;

	@OneToOne(mappedBy = "client")
	private Feedback feedback;

	public Client() {}

	public Client(int id, String emailAddress) {
		super();
		this.id = id;
		this.emailAddress = emailAddress;
	}

	public Client(String name, String emailAddress) {
		super();
		this.name = name;
		this.emailAddress = emailAddress;
	}

}
