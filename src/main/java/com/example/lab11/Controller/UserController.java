package com.example.lab11.Controller;

import com.example.lab11.Model.User;
import com.example.lab11.Service.UserService;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/add-user")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors) {

        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return errors.hasErrors() ? ResponseEntity.badRequest().body(message) : ResponseEntity.ok().build();
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body("user added successfully");
    }

    @GetMapping("/get-all-users")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @DeleteMapping("/delete-user/{userid}")
    public ResponseEntity deleteUser(@PathVariable int userid) {
        userService.deleteUser(userid);
        return ResponseEntity.status(200).body("user deleted successfully");
    }

    @PutMapping("/update-user/{userid}")
    public ResponseEntity updateUser(@PathVariable int userid,@Valid @RequestBody User user,Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.badRequest().body(message);
        }
        userService.updateUser(userid,user);
        return ResponseEntity.status(200).body("user updated successfully");
    }

    @GetMapping("/find-user-by-register-date")
    public ResponseEntity findUserRegisterDate(@RequestBody @JsonFormat(pattern = "yyyy-mm-dd") LocalDate registerDate) {
        return ResponseEntity.status(200).body(userService.findUserByRegistrationDate(registerDate));
    }

    @GetMapping("/find-user-by-email")
    public ResponseEntity findUserByEmail(@RequestBody String email) {
        return ResponseEntity.status(200).body(userService.getUserByEmail(email));
    }

}
