package carrental.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category {
	
	@Id
	@GeneratedValue
    private int id;
    private String name;
    private String brand;

    @OneToMany(mappedBy = "category")
    private List<Car> car;

    public Category() {
    }
    
}
