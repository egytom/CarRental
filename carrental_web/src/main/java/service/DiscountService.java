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

        //FIXME: a k�ls� ciklus a kateg�ri�kon iter�l, de nem is haszn�lja a category v�ltoz�t
        for (Category category : categories) {
            for (Car car : carRepository.getAll()) {
                car.setRentalPrice((int) (car.getRentalPrice() * (1 - percent / 100.0)));
                carRepository.save(car);
            }
        }
    }

}

