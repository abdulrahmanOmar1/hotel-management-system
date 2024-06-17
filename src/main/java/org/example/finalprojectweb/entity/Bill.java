package org.example.finalprojectweb.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reservationID")
    private Reservation reservation;

    private Date date;

    private Date dueDate;

    private double amount;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        PAID,
        NOT_PAID
    }
}
