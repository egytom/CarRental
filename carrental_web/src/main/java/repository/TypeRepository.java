package repository;

import model.Type;

public interface TypeRepository {	
	Type create();
	
	Type update(Type type);
	
	boolean delete(Type type);
	
	void save(Type type);
}
