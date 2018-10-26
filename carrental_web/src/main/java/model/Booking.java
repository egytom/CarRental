package model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Booking {
    private int id;
    private Date fromDate;
    private Date toDate;

    private Client client;
    private Car car;
    private Feedback feedback;
    private Payment price;
}