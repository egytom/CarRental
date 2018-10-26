package repository;

import model.Booking;
import model.Car;
import model.Client;

import java.util.Date;
import java.util.List;


public interface BookingRepository {
    Booking create(int id, Date fromDate, Date toDate);

    Booking update(Booking booking);

    boolean delete(Booking booking);

    void save(Booking booking);

    List<Booking> findById(int id);

    List<Booking> findByClient(Client client);

    List<Booking> findByCar(Car car);

}
