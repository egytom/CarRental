package carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import carrental.model.Payment;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Payment create(int amount);

    Payment update(Payment payment);

    List<Payment> findById(int id);
}
