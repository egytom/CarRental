package repository;

import java.util.List;


import model.Client;

public interface ClientRepository {
	Client create(int id, String name);
	
	Client update(Client car);
	
	boolean delete(Client car);
	
	void save(Client car);
	
	List<Client> findByPhoneNumber(String phoneNumber);
	
	List<Client> findByEmail(String emailAddress);
	
	List<Client> findByName(String name);
}
