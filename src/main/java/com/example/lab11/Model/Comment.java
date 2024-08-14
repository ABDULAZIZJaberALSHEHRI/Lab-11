package com.example.lab11.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer comment_id;

    @NotNull(message = "User id cannot be empty!")
    @Positive(message = "User id should be positive!")
    @Column(columnDefinition = "int not null")
    private int user_id;

    @NotNull(message = "Post id cannot be empty!")
    @Positive(message = "Post id should be positive!")
    @Column(columnDefinition = "int not null")
    private int post_id;


    @NotEmpty(message = "Content cannot be empty!")
    @Column(columnDefinition = "varchar(50) not null")
    private String content;


    @Column(columnDefinition = "date default CURRENT_DATE")
    private LocalDate comment_date= LocalDate.now();

}
