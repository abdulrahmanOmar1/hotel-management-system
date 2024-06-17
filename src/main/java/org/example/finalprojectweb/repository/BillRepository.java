package org.example.finalprojectweb.repository;

import org.example.finalprojectweb.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
