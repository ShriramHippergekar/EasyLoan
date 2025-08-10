package com.easyloan.repository;

import com.easyloan.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByStatus(String status);
    List<Loan> findByApplicantId(Long userId);
}
