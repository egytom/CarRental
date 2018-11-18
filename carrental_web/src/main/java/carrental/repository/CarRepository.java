package carrental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import carrental.model.Car;
import carrental.model.Category;
import carrental.model.Type;

public interface CarRepository extends JpaRepository<Car, Integer> {
	
	List<Car> findByName(String name);
	
	List<Car> findByType(Type type);
	
	@Query("SELECT c FROM Car c WHERE c.type.seatNumber = :seatNumber")
	List<Car> findBySeatNumber(@Param("seatNumber") int seatNumber);
	
	List<Car> findByCategory(Category category);
	
	@Query("SELECT c FROM Car c WHERE c.category.brand = :carBrand")
	List<Car> findByCarBrand(@Param("carBrand") String carBrand);

	List<Car> findByRentalPrice(int rentalPrice);

}