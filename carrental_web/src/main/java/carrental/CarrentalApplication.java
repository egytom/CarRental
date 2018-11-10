package carrental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import carrental.model.Car;
import carrental.repository.CarRepository;

@SpringBootApplication
public class CarrentalApplication  implements CommandLineRunner {
	
	@Autowired
	CarRepository carRepository;

	public static void main(String[] args) {
		SpringApplication.run(CarrentalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//carRepository.save(new Car());
	}
		
}