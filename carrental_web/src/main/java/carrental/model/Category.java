package carrental.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
	
	@Id
	@GeneratedValue
    private int id;
    private String name;
    private String brand;

    @OneToMany(mappedBy = "category")
    private List<Car> car;

	public Category(int id, String brand) {
		super();
		this.id = id;
		this.brand = brand;
	}

    
    
}
