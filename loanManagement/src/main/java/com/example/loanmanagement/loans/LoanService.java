package com.example.loanmanagement.loans;

import com.example.loanmanagement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public LoanApplication applyForLoan(LoanApplication loanApplication) {
        return loanRepository.save(loanApplication);
    }

    public List<LoanApplication> getLoansByUser(User user) {
        return loanRepository.findByUser(user);
    }

    public List<LoanApplication> getLoansByStatus(String status) {
        return loanRepository.findByStatus(status);
    }

    public List<LoanApplication> getLoansByLoanType(String loanType) {
        return loanRepository.findByLoanType(loanType);
    }

    public LoanApplication updateLoanStatus(Long loanId, String status) {
        LoanApplication loanApplication = loanRepository.findById(loanId).orElse(null);
        if (loanApplication != null) {
            loanApplication.setStatus(status);
            return loanRepository.save(loanApplication);
        }
        return null;
    }
}
