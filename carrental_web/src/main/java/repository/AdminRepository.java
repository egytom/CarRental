package repository;

import java.util.List;

import model.Admin;

public interface AdminRepository {
    List<Admin> findById(int id);

    Admin create(int id, String password);

    Admin update(Admin admin);

    boolean delete(Admin admin);

    void save(Admin admin);
}