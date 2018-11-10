package carrental.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
public class Payment {

	@Id
	@GeneratedValue
    private Integer id;
    private int amount;

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