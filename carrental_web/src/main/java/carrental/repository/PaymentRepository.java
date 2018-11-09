package carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import carrental.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Payment create(int amount);

    Payment update(Payment payment);

    Payment findById(int id);
}
