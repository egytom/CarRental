package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    
	List<Admin> findById(int id);

    Admin create(int id, String password);

    Admin update(Admin admin);
}