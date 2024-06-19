package org.example.finalprojectweb.services.implementations;


import org.example.finalprojectweb.DTO.RoomDTO;
import org.example.finalprojectweb.DTO.RoomInfoDTO;
import org.example.finalprojectweb.entity.Room;
import org.example.finalprojectweb.exceptions.ResourceNotFoundException;
import org.example.finalprojectweb.repository.RoomRepository;
import org.example.finalprojectweb.services.interfaces.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public RoomDTO createRoom(RoomDTO roomDTO) {
        Room room = convertToEntity(roomDTO);
        Room newRoom = roomRepository.save(room);
        return convertToDto(newRoom);
    }

    @Override
    public RoomDTO getRoomById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", id));
        return convertToDto(room);
    }

    @Override
    public RoomDTO updateRoom(RoomDTO roomDTO, Long id) {
        Room existingRoom = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", id));

        existingRoom.setRoomNo(roomDTO.getRoomNo());
        existingRoom.setType(roomDTO.getType());
        existingRoom.setPrice(roomDTO.getPrice());
        existingRoom.setSize(roomDTO.getSize());
        existingRoom.setCapacity(roomDTO.getCapacity());
        existingRoom.setStatus(Room.Status.valueOf(roomDTO.getStatus()));

        Room updatedRoom = roomRepository.save(existingRoom);
        return convertToDto(updatedRoom);
    }

    @Override
    public void deleteRoom(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", id));
        roomRepository.delete(room);
    }

    @Override
    public List<RoomDTO> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<RoomDTO> getAvailableRooms() {
        List<Room> rooms = roomRepository.findByStatus(Room.Status.AVAILABLE);
        return rooms.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDTO> getreservedRooms() {

        List<Room> rooms = roomRepository.findByStatus(Room.Status.RESERVED);
        return rooms.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

    }
    @Override
    public List<RoomInfoDTO> getAvailableRoomsInfo() {
        List<Room> rooms = roomRepository.findByStatus(Room.Status.AVAILABLE);
        return rooms.stream()
                .map(this::convertToInfoDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomInfoDTO> getReservedRoomsInfo() {
        List<Room> rooms = roomRepository.findByStatus(Room.Status.RESERVED);
        return rooms.stream()
                .map(this::convertToInfoDto)
                .collect(Collectors.toList());
    }
    private RoomInfoDTO convertToInfoDto(Room room) {
        RoomInfoDTO roomInfoDTO = new RoomInfoDTO();
        roomInfoDTO.setRoomNo(room.getRoomNo());
        roomInfoDTO.setType(room.getType());
        roomInfoDTO.setPrice(room.getPrice());
        roomInfoDTO.setStatus(room.getStatus().name());
        roomInfoDTO.setCapacity(room.getCapacity());
        roomInfoDTO.setSize(room.getSize());
        return roomInfoDTO;
    }

    private RoomDTO convertToDto(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setRoomNo(room.getRoomNo());
        roomDTO.setType(room.getType());
        roomDTO.setPrice(room.getPrice());
        roomDTO.setStatus(room.getStatus().name());
        roomDTO.setCapacity(room.getCapacity());
        roomDTO.setSize(room.getSize());
        return roomDTO;
    }

    private Room convertToEntity(RoomDTO roomDTO) {
        Room room = new Room();
        room.setRoomNo(roomDTO.getRoomNo());
        room.setType(roomDTO.getType());
        room.setPrice(roomDTO.getPrice());
        room.setStatus(Room.Status.valueOf(roomDTO.getStatus()));
        room.setCapacity(roomDTO.getCapacity());
        room.setSize(roomDTO.getSize());
        return room;
    }
}