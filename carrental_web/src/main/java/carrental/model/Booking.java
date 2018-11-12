package carrental.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {

	@Id
	@GeneratedValue
	private int id;
	private Date fromDate;
	private Date toDate;

	@ManyToOne
	@JoinColumn(name = "client")
	private Client client;

	@ManyToOne
	@JoinColumn(name = "admin")
	private Admin admin;

	@ManyToOne
	@JoinColumn(name = "car")
	private Car car;

	@OneToOne
	private Feedback feedback;

	@OneToOne
	private Payment price;

	public Booking(int id, Car car, Payment price) {
		super();
		this.id = id;
		this.car = car;
		this.price = price;
	}

	public Booking(int id, Car car, Payment price, Client client) {
		super();
		this.id = id;
		this.car = car;
		this.price = price;
		this.client = client;
	}

	public Booking(int id) {
		super();
		this.id = id;
	}

	public Booking(int id, Date fromDate, Date toDate, Car car, Payment price, Client client) {
		super();
		this.id = id;
		this.car = car;
		this.price = price;
		this.client = client;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public Booking(int id, Date fromDate, Date toDate, Payment price) {
		super();
		this.id = id;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.price = price;
	}

}