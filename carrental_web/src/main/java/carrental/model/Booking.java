package carrental.model;

import java.util.Date;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Booking {
	
	@Id
	@GeneratedValue
    private int id;
    private Date fromDate;
    private Date toDate;
    
    @ManyToOne    
    private Client client;
    
    @ManyToOne
    private Admin admin;

    @OneToOne
    private Car car;

    @OneToOne
    private Feedback feedback;

    @OneToOne
    private Payment price;

    public Booking() {
    }
    
}