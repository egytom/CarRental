package service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Car;
import repository.CarRepository;
import repository.CategoryRepository;

@Service
public class DiscountService {
	
	@Autowired
    CarRepository carRepository;
    
	@Autowired
	CategoryRepository categoryRepository;
	
	@Transactional
    public void discountCarsInCategory(String categoryName, int percent) {

        for (Car car : carRepository.findAll()) 
           car.setRentalPrice((int) (car.getRentalPrice() * (1 - percent / 100.0)));
              
    }

}

