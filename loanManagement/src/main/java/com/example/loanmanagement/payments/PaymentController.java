package com.example.loanmanagement.payments;

import com.example.loanmanagement.loans.LoanApplication;
import com.example.loanmanagement.security.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/make")
    public ResponseEntity<?> makePayment(@RequestBody Payment payment) {
        Payment madePayment = paymentService.makePayment(payment);
        return ResponseEntity.ok(madePayment);
    }

    @GetMapping("/loan/{loanId}")
    public ResponseEntity<?> getPaymentsByLoanApplication(@PathVariable Long loanId) {
        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setId(loanId);
        List<Payment> payments = paymentService.getPaymentsByLoanApplication(loanApplication);
        if (payments.isEmpty()) {
            return ResponseEntity.status(404).body("No payments found for this loan.");
        }
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getPaymentsByUser(@PathVariable String userId) {
        User user = new User();
        user.setUserId(userId);
        List<Payment> payments = paymentService.getPaymentsByUser(user);
        if (payments.isEmpty()) {
            return ResponseEntity.status(404).body("No payments found for this user.");
        }
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> getPaymentsByStatus(@PathVariable String status) {
        List<Payment> payments = paymentService.getPaymentsByStatus(status);
        if (payments.isEmpty()) {
            return ResponseEntity.status(404).body("No payments found with this status.");
        }
        return ResponseEntity.ok(payments);
    }
}
