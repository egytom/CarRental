package carrental.service;

import carrental.model.Category;
import org.springframework.transaction.annotation.Transactional;

import carrental.model.Car;
import carrental.repository.CarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {
	
	@Autowired
    CarRepository carRepository;
	
	@Transactional
    public void discountCarsInCategory(Category category, int percent) {

        for (Car car : carRepository.findByCategory(category))
           car.setRentalPrice((int) (car.getRentalPrice() * (1 - percent / 100.0)));
              
    }

}

