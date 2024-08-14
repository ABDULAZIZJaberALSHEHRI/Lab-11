package com.example.lab11.Controller;

import com.example.lab11.Model.Post;
import com.example.lab11.Service.PostService;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor

public class PostController {
    private final PostService postService;

    @GetMapping("/get-all-posts")
    public ResponseEntity GetAllPosts() {
        return ResponseEntity.status(200).body(postService.getAllPosts());
    }

    @PostMapping("/add-post")
    public ResponseEntity AddPost(@Valid @RequestBody Post post, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        postService.addPost(post);
        return ResponseEntity.status(201).body("post added successfully");
    }

    @DeleteMapping("/delete-post/{postid}")
    public ResponseEntity DeletePost(@PathVariable int postid) {
        postService.deletePost(postid);
        return ResponseEntity.status(200).body("post deleted successfully");
    }

    @PutMapping("/update-post/{postid}")
    public ResponseEntity UpdatePost(@PathVariable int postid,@Valid @RequestBody Post post, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        postService.updatePost(postid,post);
        return ResponseEntity.status(201).body("post updated successfully");
    }

    @GetMapping("/get-post-by-userid/{userid}")
    public ResponseEntity GetPostByUserid(@PathVariable int userid) {
        return ResponseEntity.status(200).body(postService.getPostsByUserID(userid));
    }

    //the user enter date so the server will return posts that before user input date
    @GetMapping("/get-posts-by-after-date")
    public ResponseEntity GetPostsByAfterDate(@RequestBody @JsonFormat(pattern = "yyyy-mm-dd") LocalDate checkDate) {
        return ResponseEntity.status(200).body(postService.getPostsByBeforeDate(checkDate));
    }
}
