package carrental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import carrental.model.Car;
import carrental.model.Category;
import carrental.model.Type;

public interface CarRepository extends JpaRepository<Car, Integer> {
	List<Car> findByName(String name);

	List<Car> findByType(Type type);

	List<Car> findByCategory(Category category);
	
	List<Car> findByPrice(int price);

	List<Car> findAll();
	
	Car create(int id, String name);
	
	Car update(Car car);
	
}
