package com.example.loanmanagement.payments;

import com.example.loanmanagement.loans.LoanApplication;
import com.example.loanmanagement.security.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment makePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> getPaymentsByLoanApplication(LoanApplication loanApplication) {
        return paymentRepository.findByLoanApplication(loanApplication);
    }

    public List<Payment> getPaymentsByUser(User user) {
        return paymentRepository.findByLoanApplication_User(user);
    }

    public List<Payment> getPaymentsByStatus(String status) {
        return paymentRepository.findByStatus(status);
    }
}
