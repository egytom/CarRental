package carrental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import carrental.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    
	List<Admin> findByName(String name);
    
}