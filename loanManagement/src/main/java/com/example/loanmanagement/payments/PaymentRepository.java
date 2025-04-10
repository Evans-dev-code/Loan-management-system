package com.example.loanmanagement.payments;

import com.example.loanmanagement.loans.LoanApplication;
import com.example.loanmanagement.security.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByLoanApplication(LoanApplication loanApplication);
    List<Payment> findByLoanApplication_User(User user);
    List<Payment> findByStatus(String status);
}
