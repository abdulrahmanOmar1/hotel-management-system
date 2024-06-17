package org.example.finalprojectweb.services.implementations;


import org.example.finalprojectweb.DTO.BillDTO;
import org.example.finalprojectweb.entity.Bill;
import org.example.finalprojectweb.entity.Reservation;
import org.example.finalprojectweb.exceptions.ResourceNotFoundException;
import org.example.finalprojectweb.repository.BillRepository;
import org.example.finalprojectweb.repository.ReservationRepository;
import org.example.finalprojectweb.services.interfaces.BillService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService {

    private final BillRepository billRepository;
    private final ReservationRepository reservationRepository;

    public BillServiceImpl(BillRepository billRepository, ReservationRepository reservationRepository) {
        this.billRepository = billRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public BillDTO createBill(BillDTO billDTO) {
        Bill bill = convertToEntity(billDTO);
        Bill newBill = billRepository.save(bill);
        return convertToDto(newBill);
    }

    @Override
    public BillDTO getBillById(Long id) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bill", "id", id));
        return convertToDto(bill);
    }

    @Override
    public BillDTO updateBill(BillDTO billDTO, Long id) {
        Bill existingBill = billRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bill", "id", id));

        existingBill.setReservation(getReservationById(billDTO.getReservationId()));
        existingBill.setDate(billDTO.getDate());
        existingBill.setDueDate(billDTO.getDueDate());
        existingBill.setAmount(billDTO.getAmount());
        existingBill.setStatus(Bill.Status.valueOf(billDTO.getStatus()));

        Bill updatedBill = billRepository.save(existingBill);
        return convertToDto(updatedBill);
    }

    @Override
    public void deleteBill(Long id) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bill", "id", id));
        billRepository.delete(bill);
    }

    @Override
    public List<BillDTO> getAllBills() {
        List<Bill> bills = billRepository.findAll();
        return bills.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private BillDTO convertToDto(Bill bill) {
        BillDTO billDTO = new BillDTO();
        billDTO.setId(bill.getId());
        billDTO.setReservationId(bill.getReservation().getId());
        billDTO.setDate(bill.getDate());
        billDTO.setDueDate(bill.getDueDate());
        billDTO.setAmount(bill.getAmount());
        billDTO.setStatus(bill.getStatus().name());
        return billDTO;
    }

    private Bill convertToEntity(BillDTO billDTO) {
        Bill bill = new Bill();
        // Assuming there is a method to get Reservation by id
        bill.setReservation(getReservationById(billDTO.getReservationId()));
        bill.setDate(billDTO.getDate());
        bill.setDueDate(billDTO.getDueDate());
        bill.setAmount(billDTO.getAmount());
        bill.setStatus(Bill.Status.valueOf(billDTO.getStatus()));
        return bill;
    }

    private Reservation getReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation", "id", reservationId));
    }
}