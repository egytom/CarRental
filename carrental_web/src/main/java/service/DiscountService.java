package service;

import model.Car;
import model.Category;
import repository.CarRepository;
import repository.CategoryRepository;

import java.util.List;

public class DiscountService {
    CarRepository carRepository;
    CategoryRepository categoryRepository;

    public void discountCarsInCategory(String categoryName, int percent) {

        List<Category> categories = categoryRepository.findByName(categoryName);

        //FIXME: a külsõ ciklus a kategóriákon iterál, de nem is használja a category változót
        for (Category category : categories) {
            for (Car car : carRepository.getAll()) {
                car.setRentalPrice((int) (car.getRentalPrice() * (1 - percent / 100.0)));
                carRepository.save(car);
            }
        }
    }

}

