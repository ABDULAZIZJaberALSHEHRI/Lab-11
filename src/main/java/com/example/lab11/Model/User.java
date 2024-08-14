package com.example.lab11.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_Id;

    @NotEmpty(message = "username should not be empty!")
    @Size(min = 4,message = "username should be more than 4 chars!")
    @Column(columnDefinition = "varchar(15) not null unique")
    private String username;

    @NotEmpty(message = "password should not be empty!")
    @Size(min = 8, message = "password should be more than 8 chars!")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;

    @NotEmpty(message = "Email should not be empty!")
    @Email(message = "Email not valid, please try another")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String email;


    @Column(columnDefinition = "date default CURRENT_DATE")
    private LocalDate registration_date= LocalDate.now();
}
