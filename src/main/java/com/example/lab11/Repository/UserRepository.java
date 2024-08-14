package com.example.lab11.Repository;

import com.example.lab11.Model.Comment;
import com.example.lab11.Model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.user_Id=?1")
    User searchUserByID(int user_Id);

    @Query("select u from User u where u.registration_date=?1")
    List<User> getUserByRegistrationDate(@JsonFormat(pattern = "yyyy-mm-dd") LocalDate establishDate);

    User searchUserByEmail(String email);
}
