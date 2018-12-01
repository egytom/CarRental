package carrental.web;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import carrental.model.Booking;
import carrental.model.Car;
import carrental.model.Client;
import carrental.model.Payment;
import carrental.repository.BookingRepository;
import carrental.repository.CarRepository;
import carrental.repository.ClientRepository;
import carrental.repository.PaymentRepository;
import carrental.service.BookingService;
import carrental.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminBookingController {

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

	@Autowired
	SearchService searchService;

	@GetMapping("/adminbooking")
	public String getAllBookings(Map<String, Object> model) {
		List<Booking> allBookings = bookingRepository.findAll();
		model.put("bookings", allBookings);
		model.put("car", new Car());
		model.put("book", new Booking());
		model.put("client", new Client());

		return "adminbooking";
	}

	@PostMapping("/adminbooking")
	public String createBooking(Map<String, Object> model) {
		return "redirect:/adminbooking";
	}

	@PostMapping("/adminbooking/book")
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

	@PostMapping("/adminbooking/find")
	public String findBookingById(Map<String, Object> model, Booking booking) {
		try {
			Booking foundBooking = bookingRepository.findById(booking.getId()).get();
			model.put("foundbooking",foundBooking);
		} catch (Exception e) {
			System.out.println(e.getMessage() + "\n" + e.getStackTrace());
		}

		return getAllBookings(model);
	}

	@PostMapping("/adminbooking/update")
	public String updateBooking(Map<String, Object> model, Booking booking) {

		try {
			Optional<Booking> foundedBooking = bookingRepository.findById(booking.getId());
			updateExistingBooking(booking, foundedBooking);
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

	private void updateExistingBooking(Booking newBooking, Optional<Booking> oldBooking){
		oldBooking.get().setPrice(newBooking.getPrice());
		oldBooking.get().setClient(newBooking.getClient());
		oldBooking.get().setFromDate(newBooking.getFromDate());
		oldBooking.get().setToDate(newBooking.getToDate());

		bookingRepository.save(oldBooking.get());
	}
}
