package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
    
    private Car car;
    private Feedback feedback;
    private Payment price;
    
}