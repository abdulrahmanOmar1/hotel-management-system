package org.example.finalprojectweb.services.implementations;

import org.example.finalprojectweb.DTO.ReservationDTO;
import org.example.finalprojectweb.entity.Reservation;
import org.example.finalprojectweb.entity.Room;
import org.example.finalprojectweb.entity.User;
import org.example.finalprojectweb.exceptions.ResourceNotFoundException;
import org.example.finalprojectweb.exceptions.RoomAlreadyReservedException;
import org.example.finalprojectweb.repository.ReservationRepository;
import org.example.finalprojectweb.repository.RoomRepository;
import org.example.finalprojectweb.repository.UserRepository;
import org.example.finalprojectweb.services.interfaces.ReservationService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  RoomRepository roomRepository,
                                  UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        Room room = getRoomById(reservationDTO.getRoomId());

        // تحقق من ما إذا كانت الغرفة محجوزة بالفعل في الفترة المحددة
        List<Reservation> overlappingReservations = reservationRepository.findOverlappingReservations(room.getId(), reservationDTO.getCheckInDate(), reservationDTO.getCheckOutDate());
        if (!overlappingReservations.isEmpty()) {
            throw new RoomAlreadyReservedException("Room is already reserved for the selected period.");
        }

        Reservation reservation = convertToEntity(reservationDTO);
        reservation.setStatus(Reservation.Status.PENDING);

        Reservation newReservation = reservationRepository.save(reservation);
        return convertToDto(newReservation);
    }

    @Override
    public ReservationDTO getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", id));
        return convertToDto(reservation);
    }

    @Override
    public ReservationDTO updateReservation(ReservationDTO reservationDTO, Long id) {
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", id));

        existingReservation.setRoom(getRoomById(reservationDTO.getRoomId()));
        existingReservation.setCustomer(getUserById(reservationDTO.getCustomerId()));
        existingReservation.setCheckInDate(reservationDTO.getCheckInDate());
        existingReservation.setCheckOutDate(reservationDTO.getCheckOutDate());

        Reservation updatedReservation = reservationRepository.save(existingReservation);
        return convertToDto(updatedReservation);
    }

    @Override
    public void deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", id));

        reservationRepository.delete(reservation);
    }

    @Override
    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationDTO> searchReservationsByCustomerName(String customerName) {
        List<User> users = userRepository.findByName(customerName);
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("User", "name", 0);
        }

        List<Reservation> reservations = users.stream()
                .flatMap(user -> reservationRepository.findByCustomerId(user.getId()).stream())
                .collect(Collectors.toList());

        return reservations.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationDTO> searchReservationsByCustomerId(Long customerId) {
        Optional<User> user = userRepository.findById(customerId);
        if (!user.isPresent()) {
            throw new ResourceNotFoundException("User", "id", customerId);
        }
        List<Reservation> reservations = reservationRepository.findByCustomerId(customerId);
        if (reservations.isEmpty()) {
            throw new ResourceNotFoundException("Reservation", "customerId", customerId);
        }

        return reservations.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationDTO> searchReservationsByDate(Date date) {
        List<Reservation> reservations = reservationRepository.findByCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(date, date);
        return reservations.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void approveReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", reservationId));
        reservation.setStatus(Reservation.Status.APPROVED);
        reservationRepository.save(reservation);
    }

    @Override
    public void rejectReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", reservationId));
        reservation.setStatus(Reservation.Status.REJECTED);
        reservationRepository.save(reservation);
    }

    private ReservationDTO convertToDto(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setRoomId(reservation.getRoom().getId());
        reservationDTO.setCustomerId(reservation.getCustomer().getId());
        reservationDTO.setCheckInDate(reservation.getCheckInDate());
        reservationDTO.setCheckOutDate(reservation.getCheckOutDate());
        reservationDTO.setNumberOfDays(reservation.getNumberOfDays());
        reservationDTO.setPrice(reservation.getPrice());
        reservationDTO.setStatus(reservation.getStatus().name());
        return reservationDTO;
    }

    private Reservation convertToEntity(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setRoom(getRoomById(reservationDTO.getRoomId()));
        reservation.setCustomer(getUserById(reservationDTO.getCustomerId()));
        reservation.setCheckInDate(reservationDTO.getCheckInDate());
        reservation.setCheckOutDate(reservationDTO.getCheckOutDate());
        reservation.setNumberOfDays(reservationDTO.getNumberOfDays());
        reservation.setPrice(reservationDTO.getPrice());
        if (reservationDTO.getStatus() != null) {
            reservation.setStatus(Reservation.Status.valueOf(reservationDTO.getStatus()));
        } else {
            reservation.setStatus(Reservation.Status.PENDING); // تعيين الحالة إلى PENDING إذا كانت الحالة غير محددة
        }
        return reservation;
    }

    private Room getRoomById(Long roomId) {
        Optional<Room> roomOptional = roomRepository.findById(roomId);
        return roomOptional.orElseThrow(() -> new ResourceNotFoundException("Room", "id", roomId));
    }

    private User getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }
}
