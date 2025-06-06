package com.example.Shopito.Entities;

import com.example.Shopito.Entities.Enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@Entity
@EntityListeners(AuditingEntityListener.class)

@Data
@NoArgsConstructor
@AllArgsConstructor
public class users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

    @Column(nullable = false)
   private String username;

   @Column(unique = true,nullable = false)
   private String email;

    @Column(nullable = false)
   private String password;

    @CreatedDate
    @Column(nullable = false, updatable = false,name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false,updatable = true,name = "updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    /// this means default Role is User unless you say hey i'm an admin
    private Role role = Role.USER;
}
