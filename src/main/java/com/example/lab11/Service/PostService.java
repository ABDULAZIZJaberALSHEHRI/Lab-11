package com.example.lab11.Service;

import com.example.lab11.Api.ApiException;
import com.example.lab11.Model.Category;
import com.example.lab11.Model.Post;
import com.example.lab11.Model.User;
import com.example.lab11.Repository.CategoryRepository;
import com.example.lab11.Repository.PostRepository;
import com.example.lab11.Repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class PostService {
    private final UserRepository userRepository;
    private PostRepository postRepository;
    private CategoryRepository CategoryRepository;
    private UserRepository UserRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void addPost(Post post) {
        User u = userRepository.searchUserByID(post.getUser_id());
        Category c = CategoryRepository.searchCategoryByID(post.getCategory_id());
        if (u==null){
            throw new ApiException("user not found");
        }
        if (c==null){
            throw new ApiException("category not found");
        }

        postRepository.save(post);
    }

    public void deletePost(int post_id) {
        Post p = postRepository.searchByPost_ID(post_id);
        if (p ==null) {
            throw new ApiException("post not found");
        }
        postRepository.delete(p);
        }

    public void updatePost(int post_id, Post post) {
        Post p = postRepository.searchByPost_ID(post_id);
        User u = userRepository.searchUserByID(post.getUser_id());
        Category c = CategoryRepository.searchCategoryByID(post.getCategory_id());

        if (p == null) {
            throw new ApiException("post not found");
        }
        if (u==null){
            throw new ApiException("user not found");
        }
        if (c==null){
            throw new ApiException("category not found");
        }
        p.setTitle(post.getTitle());
        p.setContent(post.getContent());
        p.setCategory_id(post.getCategory_id());
        p.setUser_id(post.getUser_id());
        postRepository.save(p);
    }

    public List<Post> getPostsByUserID(int user_id) {
        List<Post> posts = postRepository.findByAuthor_Id(user_id);
        if (posts.isEmpty()) {
            throw new ApiException("post not found");
        }
        return posts;
    }
    //the user enter date so the server will return posts that before user input date
    public List<Post> getPostsByBeforeDate(@JsonFormat(pattern = "yyyy-mm-dd") LocalDate checkDate) {
        List<Post> p = postRepository.getPostsAboveDate(checkDate);
        if (p.isEmpty()){
            throw new ApiException("Post not found");
        }
        return p;
    }
}
