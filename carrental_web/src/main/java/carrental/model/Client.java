package carrental.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String emailAddress;
	private String phoneNumber;
	private int loyaltyPoint;

	@OneToMany(mappedBy="client")
	private List<Booking> booking;

	@OneToOne(mappedBy = "client")
	private Feedback feedback;

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

	public Client(int id, String name, String emailAddress, String phoneNumber) {
		super();
		this.id = id;
		this.name = name;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
	}

    public Client(int id, String name, String emailAddress, String phoneNumber, List<Booking> booking, Feedback feedback) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.booking = booking;
        this.feedback = feedback;
    }

    public void addBooking(Booking booking) {
		booking.setClient(this);
		booking.getPrice().setBooking(booking);
		if (this.booking == null)
			this.booking = new ArrayList<>();
		this.booking.add(booking);
	}

	public void addLoyaltyPoint(int loyaltyPoint) {
	    this.loyaltyPoint += loyaltyPoint;
    }

}
