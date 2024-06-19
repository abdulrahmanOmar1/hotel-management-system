package org.example.finalprojectweb.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class HouseKeepingTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "roomID")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "employeeID")
    private User employee;

    private String taskDescription;

    private Date taskDate;

    private Date completedDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        COMPLETED,
        PENDING
    }

}