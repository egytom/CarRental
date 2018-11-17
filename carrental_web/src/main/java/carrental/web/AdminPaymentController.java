package carrental.web;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import carrental.model.Booking;
import carrental.model.Client;
import carrental.model.Payment;
import carrental.repository.BookingRepository;
import carrental.repository.ClientRepository;
import carrental.repository.PaymentRepository;
import carrental.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminPaymentController {

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	PaymentService paymentService;

	@PostMapping("/adminpayment")
	public String createAdminPageFromPost(Map<String, Object> model) {

		return "redirect:/adminpayment";

	}

	@GetMapping("/adminpayment")
	public String getAllPayments(Map<String, Object> model) {

		List<Payment> allPayments = paymentRepository.findAll();
		model.put("payments", allPayments);
		model.put("payment", new Payment());
		return "adminpayment";

	}

	@PostMapping("/adminpayment/create")
	public String createPayment(Map<String, Object> model, Payment payment) {

		try {
			Client payingClient = clientRepository.findByName(payment.getClient_name()).get(0);
			Booking booking = bookingRepository.findByClient(payingClient).get(0);
			payment.setBooking(booking);
			paymentRepository.save(payment);
		} catch (Exception e) {
			System.out.println(e.getMessage() + "\n" + e.getStackTrace());
		}
		return "redirect:/adminpayment";

	}

	@PostMapping("/adminpayment/delete")
	public String deletePayment(Map<String, Object> model, Payment payment) {

		try {
			paymentRepository.deleteById(payment.getId());
		} catch (Exception e) {
			System.out.println(e.getMessage() + "\n" + e.getStackTrace());
		}
		return getAllPayments(model);

	}

	@PostMapping("/adminpayment/update")
	public String updatePayment(Map<String, Object> model, Payment payment) {

		try {
			Optional<Payment> opt_payments = paymentRepository.findById(payment.getId());
			Payment paymentFound = opt_payments.get();
			paymentFound.setAmount(payment.getAmount());
			paymentRepository.save(paymentFound);
		} catch (Exception e) {
			System.out.println(e.getMessage() + "\n" + e.getStackTrace());
		}
		return getAllPayments(model);

	}

	@PostMapping("/adminpayment/find")
	public String settlePaymentById(Map<String, Object> model, Payment payment) {
		try {
			Client payingClient = clientRepository.findByName(payment.getClient_name()).get(0);
			Booking booking = bookingRepository.findByClient(payingClient).get(0);

			paymentService.pay(payingClient, booking.getId());
			model.put("result", "Fizetés kész!");
		} catch (Exception e) {
			model.put("result", "Fizetés sikertelen!");
			System.out.println(e.getMessage() + "\n" + e.getStackTrace());
		}

		return getAllPayments(model);
	}

}
