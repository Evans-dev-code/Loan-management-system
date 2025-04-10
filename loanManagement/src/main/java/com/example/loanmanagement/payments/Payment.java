package com.example.loanmanagement.payments;

import com.example.loanmanagement.loans.LoanApplication;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    private String status;

    // Relationship
    @ManyToOne
    @JoinColumn(name = "loan_application")
    private LoanApplication loanApplication;

    public Payment() {}
}
