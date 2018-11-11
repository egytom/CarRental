package carrental.service;

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
    public void discountCarsInCategory(String categoryName, int percent) {

        for (Car car : carRepository.findAll()) 
           car.setRentalPrice((int) (car.getRentalPrice() * (1 - percent / 100.0)));
              
    }

}

