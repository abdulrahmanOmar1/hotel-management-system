package org.example.finalprojectweb.services.interfaces;


import org.example.finalprojectweb.DTO.ReservationDTO;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReservationService {
    ReservationDTO createReservation(ReservationDTO reservationDTO);
    ReservationDTO getReservationById(Long id);
    ReservationDTO updateReservation(ReservationDTO reservationDTO, Long id);
    void deleteReservation(Long id);
    List<ReservationDTO> getAllReservations();
    List<ReservationDTO> searchReservationsByCustomerName(String customerName);
    List<ReservationDTO> searchReservationsByCustomerId(Long customerId);
    List<ReservationDTO> searchReservationsByDate(Date date);


}