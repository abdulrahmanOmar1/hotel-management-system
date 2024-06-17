package org.example.finalprojectweb.services.interfaces;


import org.example.finalprojectweb.DTO.RoomDTO;

import java.util.List;

public interface RoomService {
    RoomDTO createRoom(RoomDTO roomDTO);
    RoomDTO getRoomById(Long id);
    RoomDTO updateRoom(RoomDTO roomDTO, Long id);
    void deleteRoom(Long id);
    List<RoomDTO> getAllRooms();
}