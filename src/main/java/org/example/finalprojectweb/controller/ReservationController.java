package org.example.finalprojectweb.controller;

import org.example.finalprojectweb.DTO.ReservationDTO;
import org.example.finalprojectweb.services.interfaces.ReservationService;
import org.springframework.format.annotation.DateTimeFormat;
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
    public ResponseEntity<ReservationDTO> updateReservation(@RequestBody ReservationDTO reservationDTO, @PathVariable Long id) {
        ReservationDTO updatedReservation = reservationService.updateReservation(reservationDTO, id);
        return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/searchByName/{customerName}")
    public ResponseEntity<List<ReservationDTO>> searchReservationsByCustomerName(@PathVariable String customerName) {
        List<ReservationDTO> reservations = reservationService.searchReservationsByCustomerName(customerName);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/searchById/{customerId}")
    public ResponseEntity<List<ReservationDTO>> searchReservationsByCustomerId(@PathVariable Long customerId) {
        List<ReservationDTO> reservations = reservationService.searchReservationsByCustomerId(customerId);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/searchByDate/{date}")
    public ResponseEntity<List<ReservationDTO>> searchReservationsByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<ReservationDTO> reservations = reservationService.searchReservationsByDate(date);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<Void> approveReservation(@PathVariable Long id) {
        reservationService.approveReservation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<Void> rejectReservation(@PathVariable Long id) {
        reservationService.rejectReservation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
