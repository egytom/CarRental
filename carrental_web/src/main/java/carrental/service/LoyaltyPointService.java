package carrental.service;

import carrental.model.Client;
import carrental.repository.CarRepository;
import carrental.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;

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
        client.setLoyaltyPoint(client.getLoyaltyPoint()+5);
    }
}
