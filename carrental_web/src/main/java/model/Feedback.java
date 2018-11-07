package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Feedback {
	
	@Id
	@GeneratedValue
	private int id;
	private String text;
	
	
	public Feedback(String text) {
		super();
		this.text = text;
	}
	
	
}