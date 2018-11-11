package carrental.service;

import carrental.model.Car;
import carrental.model.Category;
import carrental.model.Type;
import carrental.repository.CarRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class DiscountServiceTest {

    @InjectMocks
    DiscountService discountService;

    @Mock
    CarRepository carRepository;

    @Test
    public void testDiscountByCategory() throws Exception {

        //Arrange
        Category bugatti = new Category("Bugatti");
        Category ferrari = new Category("Ferrari");

        Car car1 = new Car(1, "Chiron", new Type(), bugatti);
        Car car3 = new Car(3, "LaFerrari", new Type(), ferrari);

        car1.setRentalPrice(10000);
        car3.setRentalPrice(15000);

        when(carRepository.findByCategory(bugatti)).thenReturn(Arrays.asList(car1));

        //ACT
        discountService.discountCarsInCategory(bugatti, 10);

        //ASSERT
        double delta = 0.00001;

        assertThat((double)car1.getRentalPrice(), closeTo(9000.0, delta));
        assertThat((double)car3.getRentalPrice(), closeTo(15000, delta));

    }

}
