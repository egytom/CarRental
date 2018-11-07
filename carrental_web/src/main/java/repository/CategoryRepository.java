package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
    List<Category> findByBrand(String brand);

    List<Category> findByName(String name);

    Category create(String name, String brand);

    Category update(Category category);

}