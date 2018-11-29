package carrental.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {

	@Id
	@GeneratedValue
    private int id;
    private int amount;
    private String client_name;
    
    @OneToOne(mappedBy = "price")
    private Booking booking;

	public Payment(int amount) {
		super();
		this.amount = amount;
	}

	public Payment(int id, int amount) {
		super();
		this.id = id;
		this.amount = amount;
	}
    
}