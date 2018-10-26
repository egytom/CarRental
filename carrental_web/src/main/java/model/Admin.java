package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Admin {
    private int id;
    private String password;

    private Feedback feedback;
    private Booking booking;
}