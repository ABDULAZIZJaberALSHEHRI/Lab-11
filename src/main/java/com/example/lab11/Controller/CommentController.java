package com.example.lab11.Controller;

import com.example.lab11.Model.Comment;
import com.example.lab11.Service.CommentService;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/comment")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/get-all-comments")
    public ResponseEntity GetAllComments() {
        return ResponseEntity.status(200).body(commentService.getAllComments());
    }

    @PostMapping("/add-comment")
    public ResponseEntity AddComment(@Valid @RequestBody Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        commentService.addComment(comment);
        return ResponseEntity.status(201).body("comment added successfully");
    }

    @PutMapping("/update-comment/{commentid}")
    public ResponseEntity UpdateComment(@PathVariable Integer commentid,@Valid @RequestBody Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        commentService.updateComment(commentid, comment);
        return ResponseEntity.status(201).body("comment updated successfully");
    }

    @DeleteMapping("delete-comment/{commentid}")
    public ResponseEntity DeleteComment(@PathVariable Integer commentid) {
        commentService.deleteComment(commentid);
        return ResponseEntity.status(201).body("comment deleted successfully");
    }

    @GetMapping("/search-for-keyword/{keyword}")
    public ResponseEntity SearchForComment(@PathVariable String keyword) {
        return ResponseEntity.status(200).body(commentService.searchForComment(keyword));
    }

    @GetMapping("/search-by-dates")
    public ResponseEntity SearchByDate(@RequestBody @JsonFormat(pattern = "yyyy-mm-dd") LocalDate date) {

        return ResponseEntity.status(201).body(commentService.searchForCommentByAboveDate(date));
    }

    @GetMapping("find-comments-by-user-id/{userid}")
    public ResponseEntity FindCommentsByUserId(@PathVariable Integer userid) {
        return ResponseEntity.status(200).body(commentService.findCommentsByUser_ID(userid));
    }
}
