package carrental.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}