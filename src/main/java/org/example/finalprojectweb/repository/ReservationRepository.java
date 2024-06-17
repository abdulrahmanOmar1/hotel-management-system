package org.example.finalprojectweb.repository;

import org.example.finalprojectweb.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByCustomerId(Long customerId);
//    List<Reservation> findByCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(Date checkInDate, Date checkOutDate);
    List<Reservation> findByCheckInDateLessThanEqualAndCheckOutDateGreaterThan(Date checkInDate, Date checkOutDate);
    List<Reservation> findByCheckInDateGreaterThanEqualAndCheckOutDateLessThanEqual(Date checkInDate, Date checkOutDate);
    List<Reservation> findByCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(Date checkInDate, Date checkOutDate);
}
