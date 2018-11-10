package carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import carrental.model.Type;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type, Integer> {	
	
	Type create();

	List<Type> findById(int id);
	
}