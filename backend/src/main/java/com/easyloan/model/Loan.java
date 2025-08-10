package com.easyloan.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "loans")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private User applicant;

    @Min(1)
    private Double amount;

    // term in months
    @Min(1)
    private Integer termMonths;

    @NotNull
    private String type; // PERSONAL, HOME, AUTO, etc.

    @NotNull
    private String status = "PENDING"; // PENDING / APPROVED / REJECTED
}
