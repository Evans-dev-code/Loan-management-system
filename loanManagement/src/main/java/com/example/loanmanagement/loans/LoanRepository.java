package com.example.loanmanagement.loans;

import com.example.loanmanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<LoanApplication, Long> {

    List<LoanApplication> findByUser(User user);
    List<LoanApplication> findByStatus(String status);
    List<LoanApplication> findByLoanType(String loanType);
}
