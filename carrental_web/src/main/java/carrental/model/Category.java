package carrental.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Category {
	
	@Id
	@GeneratedValue
    private int id;
    private String name;
    private String brand;

    @OneToOne(mappedBy = "category")
    private Car car;

    public Category() {
    }
    
}
