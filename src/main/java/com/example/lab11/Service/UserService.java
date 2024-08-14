package com.example.lab11.Service;

import com.example.lab11.Api.ApiException;
import com.example.lab11.Model.User;
import com.example.lab11.Repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Integer userID) {
        User user = userRepository.searchUserByID(userID);
        if (user==null) {
            throw new ApiException("User not found");
        }
        userRepository.delete(user);
    }

    public void updateUser(Integer userID,User user) {
        User u = userRepository.searchUserByID(userID);
        if (u == null) {
            throw new ApiException("User not found");
        }
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        userRepository.save(u);
    }

    public List<User> findUserByRegistrationDate(@JsonFormat(pattern = "yyyy-mm-dd") LocalDate registrationDate){
        List<User> users = userRepository.getUserByRegistrationDate(registrationDate);
        if (users.isEmpty()) {
            throw new ApiException("User not found");
        }
        return users;
    }

    public User getUserByEmail(String email) {
        User user = userRepository.searchUserByEmail(email);
        if (user == null) {
            throw new ApiException("User not found");
        }
        return user;
    }
}
