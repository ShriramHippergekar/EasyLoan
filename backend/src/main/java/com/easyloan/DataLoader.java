package com.easyloan;

import com.easyloan.model.Loan;
import com.easyloan.model.User;
import com.easyloan.repository.LoanRepository;
import com.easyloan.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepo;
    private final LoanRepository loanRepo;
    public DataLoader(UserRepository ur, LoanRepository lr){ this.userRepo = ur; this.loanRepo = lr; }

    @Override
    public void run(String... args) throws Exception {
        User u1 = userRepo.save(new User(null, "Ramesh", "ramesh@example.com", "USER"));
        User u2 = userRepo.save(new User(null, "Admin", "admin@example.com", "ADMIN"));
        loanRepo.save(new Loan(null, u1, 50000.0, 12, "PERSONAL", "PENDING"));
        loanRepo.save(new Loan(null, u1, 200000.0, 60, "HOME", "APPROVED"));
    }
}
