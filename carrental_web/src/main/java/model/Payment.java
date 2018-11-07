package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Payment {
	
	@Id
	@GeneratedValue
    private int id;
    private int amount;
    
}