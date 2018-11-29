package carrental.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import carrental.model.Client;
import carrental.model.Payment;
import carrental.repository.CarRepository;
import carrental.repository.ClientRepository;

@Service
public class LoyaltyPointService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    DiscountService discountService;

    @Transactional
    public void addLoyaltyPoints(Client client) {
        Date date = Calendar.getInstance().getTime();

        getDiscount(client);

        if(differenceBetweenDates(client, date) <= 0) {
            client.setLoyaltyPoint(client.getLoyaltyPoint()+5);
        }
    }


    private void getDiscount(Client client) {
        if(client.getLoyaltyPoint() > 0) {
            int priceAfterDiscount = (int)(client.getBooking().get(0).getPrice().getAmount() *(1 - client.getLoyaltyPoint() / 100.0));
            Payment payment = new Payment(priceAfterDiscount);
            client.getBooking().get(0).setPrice(payment);
        }
    }

    private int differenceBetweenDates(Client client, Date date) {
        return (int)( (date.getTime() - client.getBooking().get(0).getToDate().getTime()) / (1000 * 60 * 60 * 24));
    }

}
