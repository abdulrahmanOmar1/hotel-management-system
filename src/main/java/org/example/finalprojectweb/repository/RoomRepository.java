package org.example.finalprojectweb.repository;

import org.example.finalprojectweb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByStatus(Room.Status status);

}
