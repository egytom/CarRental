package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Admin {
    private int id;
    private String password;

    //FIXME: mit jelent, hogy az adminnak van egy feedbackje?
    private Feedback feedback;
    
    //FIXME: mit jelent az admin egyetlen bookingja?
    private Booking booking;
}