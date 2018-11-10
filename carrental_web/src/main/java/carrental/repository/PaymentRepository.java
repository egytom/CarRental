package carrental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import carrental.model.Booking;
import carrental.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    List<Payment> findByBooking(Booking booking);
    
}