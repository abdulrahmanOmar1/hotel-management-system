package org.example.finalprojectweb.controller;


import org.example.finalprojectweb.DTO.ReservationDTO;
import org.example.finalprojectweb.services.interfaces.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        List<ReservationDTO> reservations = reservationService.getAllReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id) {
        ReservationDTO reservation = reservationService.getReservationById(id);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        ReservationDTO newReservation = reservationService.createReservation(reservationDTO);
        return new ResponseEntity<>(newReservation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(@RequestBody ReservationDTO reservationDTO,
                                                            @PathVariable Long id) {
        ReservationDTO updatedReservation = reservationService.updateReservation(reservationDTO, id);
        return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/searchByName/{customerName}")
    public ResponseEntity<List<ReservationDTO>> searchReservationsBycustomerName(@PathVariable String customerName) {
        List<ReservationDTO> reservations = reservationService.searchReservationsByCustomerName(customerName);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
    @GetMapping("/searchById/{customerId}")
    public ResponseEntity<List<ReservationDTO>> searchReservationsBycustomerId(@PathVariable Long customerId ) {
        List<ReservationDTO> reservations = reservationService.searchReservationsByCustomerId(customerId);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
    @GetMapping("/searchByDate/{date}")
    public ResponseEntity<List<ReservationDTO>> searchReservationsByDate(@PathVariable Date date) {
        List<ReservationDTO> reservations = reservationService.searchReservationsByDate(date);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
}