package com.example.Academy.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_users",// This is the join table
            joinColumns = @JoinColumn(name = "user_id"), // Foreign key to User
    inverseJoinColumns = @JoinColumn(name = "role_id")) // Foreign key to Role
    private Set<Role> roles;


}
