package repository;

import java.util.List;

import model.Category;

public interface CategoryRepository {
    List<Category> findByBrand(String brand);

    List<Category> findByName(String name);

    Category create(String name, String brand);

    Category update(Category category);

    boolean delete(Category category);

    void save(Category category);
}