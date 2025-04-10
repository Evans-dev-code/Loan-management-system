package com.example.loanmanagement.loans;

import com.example.loanmanagement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@CrossOrigin(origins = "*")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/apply")
    public ResponseEntity<?> applyForLoan(@RequestBody LoanApplication loanApplication) {
        LoanApplication appliedLoan = loanService.applyForLoan(loanApplication);
        return ResponseEntity.ok(appliedLoan);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getLoansByUser(@PathVariable String userId) {
        User user = new User();
        user.setUserId(userId);
        List<LoanApplication> loans = loanService.getLoansByUser(user);
        if (loans.isEmpty()) {
            return ResponseEntity.status(404).body("No loans found for this user.");
        }
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> getLoansByStatus(@PathVariable String status) {
        List<LoanApplication> loans = loanService.getLoansByStatus(status);
        if (loans.isEmpty()) {
            return ResponseEntity.status(404).body("No loans found with this status.");
        }
        return ResponseEntity.ok(loans);
    }

    @PutMapping("/update-status/{loanId}")
    public ResponseEntity<?> updateLoanStatus(@PathVariable Long loanId, @RequestBody String status) {
        LoanApplication updatedLoan = loanService.updateLoanStatus(loanId, status);
        if (updatedLoan == null) {
            return ResponseEntity.status(404).body("Loan not found.");
        }
        return ResponseEntity.ok(updatedLoan);
    }
}
