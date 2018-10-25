package repository;

import java.util.List;

import model.Car;
import model.Category;
import model.Type;

public interface CarRepository {
	List<Car> findByName(String name);

	List<Car> findByType(Type type);

	List<Car> findByCategory(Category category);

	List<Car> getAll();
	
	Car create(int id, String name);
	
	Car update(Car car);
	
	boolean delete(Car car);
	
	void save(Car car);
}
