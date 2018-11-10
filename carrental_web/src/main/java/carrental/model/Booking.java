package carrental.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    private Client client;
    
    @ManyToOne
    private Admin admin;

    @OneToOne
    private Car car;

    @OneToOne
    private Feedback feedback;

    @OneToOne
    private Payment price;
    
}