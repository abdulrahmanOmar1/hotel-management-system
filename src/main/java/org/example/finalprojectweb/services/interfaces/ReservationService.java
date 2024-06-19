package org.example.finalprojectweb.services.interfaces;

import org.example.finalprojectweb.DTO.ReservationDTO;
import org.example.finalprojectweb.DTO.RoomInfoDTO;

import java.util.Date;
import java.util.List;

public interface ReservationService {
    ReservationDTO createReservation(ReservationDTO reservationDTO);
    ReservationDTO getReservationById(Long id);
    ReservationDTO updateReservation(ReservationDTO reservationDTO, Long id);
    void deleteReservation(Long id);
    List<ReservationDTO> getAllReservations();
    List<ReservationDTO> searchReservationsByCustomerName(String customerName);
    List<ReservationDTO> searchReservationsByCustomerId(Long customerId);
    List<ReservationDTO> searchReservationsByDate(Date date);
    void approveReservation(Long reservationId);
    void rejectReservation(Long reservationId);
}
