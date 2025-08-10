package com.easyloan.controller;

import com.easyloan.model.Loan;
import com.easyloan.model.User;
import com.easyloan.repository.LoanRepository;
import com.easyloan.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
    private final LoanRepository loanRepo;
    private final UserRepository userRepo;
    public LoanController(LoanRepository loanRepo, UserRepository userRepo){
        this.loanRepo = loanRepo; this.userRepo = userRepo;
    }

    @PostMapping
    public ResponseEntity<?> applyLoan(@Valid @RequestBody Loan loan){
        if(loan.getApplicant() == null || loan.getApplicant().getId() == null){
            return ResponseEntity.badRequest().body("Applicant id required");
        }
        User u = userRepo.findById(loan.getApplicant().getId()).orElse(null);
        if(u==null) return ResponseEntity.badRequest().body("Applicant not found");
        loan.setApplicant(u);
        loan.setStatus("PENDING");
        Loan saved = loanRepo.save(loan);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<Loan> all(@RequestParam(required = false) String status, @RequestParam(required = false) Long userId){
        if(status!=null) return loanRepo.findByStatus(status.toUpperCase());
        if(userId!=null) return loanRepo.findByApplicantId(userId);
        return loanRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> get(@PathVariable Long id){
        return loanRepo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Admin endpoints
    @PostMapping("/{id}/approve")
    public ResponseEntity<?> approve(@PathVariable Long id){
        return loanRepo.findById(id).map(l -> {
            l.setStatus("APPROVED");
            loanRepo.save(l);
            return ResponseEntity.ok(l);
        }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<?> reject(@PathVariable Long id){
        return loanRepo.findById(id).map(l -> {
            l.setStatus("REJECTED");
            loanRepo.save(l);
            return ResponseEntity.ok(l);
        }).orElse(ResponseEntity.notFound().build());
    }
}
