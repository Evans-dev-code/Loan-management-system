package com.example.loanmanagement.loans;

import com.example.loanmanagement.payments.Payment;
import com.example.loanmanagement.security.users.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "loan_applications")
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String phone;
    private Double amount;
    private Integer duration;
    private String purpose;
    private String loanType;
    private Double salary;
    private String personalLoanInfo;
    private Double mortgagePropertyValue;

    @Column(name = "interest_rate")
    private Double interestRate;

    @Column(name = "total_repayment")
    private Double totalRepayment;

    @Column(nullable = false)
    private String status = "PENDING"; // Default status

    // Relationships
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Payment> payments;

    public LoanApplication() {}
}
