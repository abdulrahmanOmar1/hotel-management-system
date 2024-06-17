package org.example.finalprojectweb.services.interfaces;


import org.example.finalprojectweb.DTO.BillDTO;

import java.util.List;

public interface BillService {
    BillDTO createBill(BillDTO billDTO);
    BillDTO getBillById(Long id);
    BillDTO updateBill(BillDTO billDTO, Long id);
    void deleteBill(Long id);
    List<BillDTO> getAllBills();
}