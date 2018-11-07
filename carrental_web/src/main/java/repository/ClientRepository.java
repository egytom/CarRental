package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	
	Client create(int id, String name);
	
	Client update(Client car);

	List<Client> findByPhoneNumber(String phoneNumber);
	
	List<Client> findByEmail(String emailAddress);
	
	List<Client> findByName(String name);
}
