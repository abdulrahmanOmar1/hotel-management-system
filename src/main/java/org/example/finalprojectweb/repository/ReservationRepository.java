package org.example.finalprojectweb.repository;

import org.example.finalprojectweb.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByCustomerId(Long customerId);

    List<Reservation> findByCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(Date checkInDate, Date checkOutDate);

    @Query("SELECT r FROM Reservation r WHERE r.room.id = :roomId AND r.status != 'REJECTED' AND " +
            "((r.checkInDate <= :checkOutDate AND r.checkOutDate >= :checkInDate) OR " +
            "(r.checkInDate <= :checkInDate AND r.checkOutDate >= :checkOutDate) OR " +
            "(r.checkInDate >= :checkInDate AND r.checkOutDate <= :checkOutDate))")
    List<Reservation> findOverlappingReservations(@Param("roomId") Long roomId,
                                                  @Param("checkInDate") Date checkInDate,
                                                  @Param("checkOutDate") Date checkOutDate);
}