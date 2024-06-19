package org.example.finalprojectweb.DTO;

import lombok.Data;

@Data
public class RoomInfoDTO {
    private String roomNo;
    private String type;
    private double price;
    private int capacity;
    private double size;
    private String status;
}
