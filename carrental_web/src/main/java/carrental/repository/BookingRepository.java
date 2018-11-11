package carrental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import carrental.model.Booking;
import carrental.model.Car;
import carrental.model.Client;


public interface BookingRepository extends JpaRepository<Booking, Integer> {
	
    List<Booking> findByClient(Client client);
	  
    List<Booking> findByCar(Car car);

}
