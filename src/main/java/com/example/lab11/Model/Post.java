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

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer post_Id;

    @NotNull(message = "Category id should not be empty!")
    @Positive(message = "Category Id should be positive")
    @Column(columnDefinition = "int not null")
    private int category_id;

    @NotEmpty(message = "title should not be empty!")
    @Column(columnDefinition = "varchar(15) not null")
    private String title;

    @NotEmpty(message = "Content cannot be empty!")
    @Size(min = 5,message = "Content should contain more than 5 chars!")
    @Column(columnDefinition = "varchar(50) not null")
    private String content;

    @NotNull(message = "User id cannot be empty!")
    @Positive(message = "User id should be positive!")
    @Column(columnDefinition = "int not null")
    private int user_id;


    @Column(columnDefinition = "date default CURRENT_DATE")
    private LocalDate publish_date= LocalDate.now();
}
