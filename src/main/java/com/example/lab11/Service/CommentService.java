package com.example.lab11.Service;

import com.example.lab11.Api.ApiException;
import com.example.lab11.Model.Comment;
import com.example.lab11.Model.Post;
import com.example.lab11.Model.User;
import com.example.lab11.Repository.CommentRepository;
import com.example.lab11.Repository.PostRepository;
import com.example.lab11.Repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private PostRepository postRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public void addComment(Comment comment) {
        User u = userRepository.searchUserByID(comment.getUser_id());
        Post p = postRepository.searchByPost_ID(comment.getPost_id());

        if (u==null){
            throw new ApiException("user not found");
        }
        if (p==null){
            throw new ApiException("post not found");
        }

        commentRepository.save(comment);
    }

    public void deleteComment(int comment_id) {
        Comment c = commentRepository.searchCommentByCommentId(comment_id);

        if (c==null){
            throw new ApiException("comment not found");
        }
        commentRepository.delete(c);
    }

    public void updateComment(int comment_id, Comment comment) {
        Comment c = commentRepository.searchCommentByCommentId(comment_id);
        User u = userRepository.searchUserByID(comment.getUser_id());
        Post p = postRepository.searchByPost_ID(comment.getPost_id());
        if (u==null){
            throw new ApiException("user not found");
        }
        if (p==null){
            throw new ApiException("post not found");
        }
        if (c==null){
            throw new ApiException("comment not found");
        }
        c.setContent(comment.getContent());
        c.setUser_id(comment.getUser_id());
        c.setPost_id(comment.getPost_id());
        commentRepository.save(c);
    }

    //input a keyword so the repository will return all comments that contain the same keyword
    public List<Comment> searchForComment(String keyword) {
        List<Comment> foundComments = new ArrayList<>();

        if (commentRepository.findAll().isEmpty()){
            throw new ApiException("comment not found");
        }
        for (int i = 0; i < commentRepository.findAll().size(); i++) {
            if (commentRepository.findAll().get(i).getContent().contains(keyword)) {
                foundComments.add(commentRepository.findAll().get(i));
            }
        }
        return foundComments;
    }

    //input date and server will return comments that initialize after user entered date
    public List<Comment> searchForCommentByAboveDate(@JsonFormat(pattern = "yyyy-mm-dd") LocalDate date) {
        List<Comment> c = commentRepository.getCommentsAboveDate(date);
        if (c.isEmpty()){
            throw new ApiException("comment not found");
        }
        return c;
    }

    public List<Comment> findCommentsByUser_ID(int user_id) {
        List<Comment> comments = commentRepository.getCommentByUser_id(user_id);

        if (comments.isEmpty()){
            throw new ApiException("user comment not found");
        }
        return comments;
    }


}
