package carrental.service;

import carrental.model.Car;
import carrental.model.Category;
import carrental.model.Type;
import carrental.repository.CarRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class DiscountServiceIT {

    @Autowired
    DiscountService discountService;

    @Autowired
    CarRepository carRepository;

    @Test
    public void testDiscountByCategory() throws Exception {

        //Arrange
        Category bugatti = new Category("Bugatti");
        Category ferrari = new Category("Ferrari");

        Car car1 = new Car(5, "Chiron", new Type(), bugatti);
        Car car2 = new Car(6, "LaFerrari", new Type(), ferrari);

        car1.setRentalPrice(10000);
        car2.setRentalPrice(15000);

        car1 = carRepository.save(car1);
        car2 = carRepository.save(car2);

        //ACT
        discountService.discountCarsInCategory(bugatti, 10);

        //ASSERT
        car1 = carRepository.findByName("Chiron").get(0);
        car2 = carRepository.findByName("LaFerrari").get(0);

        double delta = 0.00001;

        assertThat((double)car1.getRentalPrice(), closeTo(9000.0, delta));
        assertThat((double)car2.getRentalPrice(), closeTo(15000, delta));
    }
}
