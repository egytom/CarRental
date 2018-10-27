package repository;

import model.Payment;

import java.util.List;

public interface PaymentRepository {

    Payment create(int id, int amount);

    Payment update(Payment payment);

    boolean delete(Payment payment);

    void save(Payment payment);

    Payment findById(int id);
}
