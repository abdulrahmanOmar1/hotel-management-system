package org.example.finalprojectweb.services.interfaces;


import org.example.finalprojectweb.DTO.RoomDTO;
import org.example.finalprojectweb.DTO.RoomInfoDTO;

import java.util.List;

public interface RoomService {
    RoomDTO createRoom(RoomDTO roomDTO);
    RoomDTO getRoomById(Long id);
    RoomDTO updateRoom(RoomDTO roomDTO, Long id);
    void deleteRoom(Long id);
    List<RoomDTO> getAllRooms();
    public List<RoomDTO> getAvailableRooms() ;

    List<RoomDTO> getreservedRooms();
    List<RoomInfoDTO> getAvailableRoomsInfo();
    List<RoomInfoDTO> getReservedRoomsInfo();
}