package org.example.finalprojectweb.controller;


import org.example.finalprojectweb.DTO.RoomDTO;
import org.example.finalprojectweb.DTO.RoomInfoDTO;
import org.example.finalprojectweb.services.interfaces.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        List<RoomDTO> rooms = roomService.getAllRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable Long id) {
        RoomDTO room = roomService.getRoomById(id);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoomDTO> createRoom(@RequestBody RoomDTO roomDTO) {
        RoomDTO newRoom = roomService.createRoom(roomDTO);
        return new ResponseEntity<>(newRoom, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomDTO> updateRoom(@RequestBody RoomDTO roomDTO, @PathVariable Long id) {
        RoomDTO updatedRoom = roomService.updateRoom(roomDTO, id);
        return new ResponseEntity<>(updatedRoom, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/available")
    public ResponseEntity<List<RoomDTO>> getAvailableRooms() {
        List<RoomDTO> rooms = roomService.getAvailableRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }
    @GetMapping("/reserved")
    public ResponseEntity<List<RoomDTO>> getreservedRooms() {
        List<RoomDTO> rooms = roomService.getreservedRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }
    @GetMapping("/available/info")
    public ResponseEntity<List<RoomInfoDTO>> getAvailableRoomsInfo() {
        List<RoomInfoDTO> rooms = roomService.getAvailableRoomsInfo();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/reserved/info")
    public ResponseEntity<List<RoomInfoDTO>> getReservedRoomsInfo() {
        List<RoomInfoDTO> rooms = roomService.getReservedRoomsInfo();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

}