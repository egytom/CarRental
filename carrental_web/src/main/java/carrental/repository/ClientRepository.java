package carrental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import carrental.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

	List<Client> findByPhoneNumber(String phoneNumber);
	
	List<Client> findByEmailAddress(String emailAddress);
	
	List<Client> findByName(String name);
	
}