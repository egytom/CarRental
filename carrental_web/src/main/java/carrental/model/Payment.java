package carrental.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

import java.awt.print.Book;

@Getter
@Setter
@Entity
public class Payment {
	
	@Id
	@GeneratedValue
    private int id;
    private int amount;

    @OneToOne(mappedBy = "price")
    private Booking booking;

    public Payment() {
    }
    
}