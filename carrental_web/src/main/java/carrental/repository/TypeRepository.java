package carrental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import carrental.model.Type;

public interface TypeRepository extends JpaRepository<Type, Integer> {	

	List<Type> findByName(String name);
	
}