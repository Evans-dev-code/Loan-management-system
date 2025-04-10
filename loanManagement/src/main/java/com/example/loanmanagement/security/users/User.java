package com.example.loanmanagement.security.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    private String kycDocuments;

    private String nextOfKin;

    // Relationships
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LoanApplication> loans;

//    public User() {}
//
//    public User(String fullName, String username, String email, String userId, String password, String role) {
//        this.fullName = fullName;
//        this.username = username;
//        this.email = email;
//        this.userId = userId;
//        this.password = password;
//        this.role = role;
//    }
}
