package com.humber.SpringSecurityApp.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable=false, unique = true)
    private String username;

    @Column(nullable=false, length = 64)
    private String password;

    @Column(nullable=false)
    private String role;


}
