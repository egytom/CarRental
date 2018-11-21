package carrental.web;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import carrental.model.Booking;
import carrental.model.Car;
import carrental.model.Client;
import carrental.repository.BookingRepository;
import carrental.repository.CarRepository;
import carrental.repository.ClientRepository;
import carrental.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    BookingService bookingService;

    @GetMapping("/booking")
    public String getAllBookings(Map<String, Object> model) {

        List<Booking> allBookings = bookingRepository.findAll();
        model.put("bookings", allBookings);
        model.put("booking", new Booking());
        return "booking";

    }

    @PostMapping("/booking")
    public String createBooking(Map<String, Object> model) {

        return "redirect:/booking";

    }

    @PostMapping("/booking/book")
    public String bookCarById(Map<String, Object> model, Booking booking) {
        try {
            // Client client = clientRepository.findByName(booking.getClient().getName());
            // Car car = carRepository.findById(booking.getCar().getId()).get();

            Client client = new Client(1, "Teszt Kliens"); // teszteléshez
            Car car = carRepository.findById(1).get();

            bookingService.createBooking(client, car, booking.getFromDate(), booking.getToDate());
            model.put("result", "Foglalás kész!");
        } catch (Exception e) {
            model.put("result", "Foglalás sikertelen!");
            System.out.println(e.getMessage() + "\n" + e.getStackTrace());
        }
        return getAllBookings(model);
    }

}
