package carrental.service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import carrental.model.Booking;
import org.springframework.stereotype.Service;

@Service
public class ChangeBookingDateService {
    void changeBookingDate(Booking booking, Date newToDate){
        booking.setToDate(newToDate);

        Date fromDate = booking.getFromDate();

        long bookingLength = newToDate.getTime() - fromDate.getTime();
        long bookingLengthInDays = TimeUnit.DAYS.convert(bookingLength, TimeUnit.MILLISECONDS);

        int newPrice = booking.getCar().getRentalPrice()* (int)bookingLengthInDays;

        booking.getPrice().setAmount(newPrice);
    }
}
