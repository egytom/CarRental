package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Type;

public interface TypeRepository extends JpaRepository<Type, Integer> {	
	
	Type create();
	
	Type update(Type type);
	
}
