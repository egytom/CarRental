package model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Admin {
    private int id;
    private String password;

    //FIXME: mit jelent, hogy az adminnak van egy feedbackje?
    private Feedback feedback;

    private ArrayList<Booking> booking;
}