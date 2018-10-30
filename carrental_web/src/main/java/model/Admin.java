package model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Admin {
    private int id;
    private String password;

    private ArrayList<Booking> booking;
}