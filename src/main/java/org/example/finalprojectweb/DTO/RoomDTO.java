package org.example.finalprojectweb.DTO;

import lombok.Data;

@Data
public class RoomDTO {
    private Long id;
    private String roomNo;
    private String type;
    private double price;
    private int capacity;
    private double size;
    private String status;

}