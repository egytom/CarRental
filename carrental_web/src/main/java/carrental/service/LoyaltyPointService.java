package carrental.service;

import carrental.model.Client;
import carrental.repository.CarRepository;
import carrental.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.Calendar;
import java.util.Date;

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
        if(differenceBetweenDates(client, date) <= 0) {
            client.setLoyaltyPoint(client.getLoyaltyPoint()+5);
        }
    }

    private int differenceBetweenDates(Client client, Date date) {
        return (int)( (date.getTime() - client.getBooking().get(0).getToDate().getTime()) / (1000 * 60 * 60 * 24));
    }

}
