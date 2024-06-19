package org.example.finalprojectweb.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customerID")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "roomID")
    private Room room;

    private Date checkInDate;
    private Date checkOutDate;

    private int numberOfDays;
    private double price;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        PENDING,
        APPROVED,
        REJECTED,
        RESERVED,
        CANCELLED
    }
}
