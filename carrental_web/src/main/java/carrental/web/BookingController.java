package carrental.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import carrental.model.Booking;
import carrental.model.Car;
import carrental.model.Client;
import carrental.model.Payment;
import carrental.repository.BookingRepository;
import carrental.repository.CarRepository;
import carrental.repository.ClientRepository;
import carrental.repository.PaymentRepository;
import carrental.service.BookingService;

@Controller
public class BookingController {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CarRepository carRepository;
    
    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    BookingService bookingService;

    @GetMapping("/booking")
    public String getAllBookings(Map<String, Object> model) {
        List<Booking> allBookings = bookingRepository.findAll();
        model.put("bookings", allBookings);
        model.put("car", new Car());
        model.put("book", new Booking());
        model.put("client", new Client());
        
        return "booking";
    }

    @PostMapping("/booking")
    public String createBooking(Map<String, Object> model) {  	
        return "redirect:/booking";
    }

    @PostMapping("/booking/book")
    public String bookCarById(Map<String, Object> model, Car car, Booking booking, Client client) {
        try {
        	client = createClientIfNotExsists(client);
            car = carRepository.findById(car.getId()).get();

            saveNewBooking(car, booking, client);
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n" + e.getStackTrace());
        }
        
        return getAllBookings(model);
    }

	private void saveNewBooking(Car car, Booking booking, Client client) {
		booking = bookingService.createBooking(client, car, booking.getFromDate(), booking.getToDate());
		Payment price = booking.getPrice();
		price.setBooking(booking);
		bookingRepository.save(booking);
		paymentRepository.save(price);
	}

	private Client createClientIfNotExsists(Client client) throws Exception {
		String name = client.getName();
		List<Client> clients = clientRepository.findByName(name);
		
		if (!clients.isEmpty()) {
			client = clients.get(0);
		} else {
			Client newClient = new Client();
			newClient.setName(name);
			client = newClient;
		}
		
		return clientRepository.save(client);
	}

}
