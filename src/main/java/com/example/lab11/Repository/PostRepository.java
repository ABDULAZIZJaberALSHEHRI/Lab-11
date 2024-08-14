package com.example.lab11.Repository;

import com.example.lab11.Model.Comment;
import com.example.lab11.Model.Post;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("select p from Post p where p.post_Id=?1")
    Post searchByPost_ID(int id);

    @Query("select p from Post p where p.user_id=?1")
    List<Post> findByAuthor_Id(int user_id);

    @Query("select p from Post p where p.publish_date<=?1")
    List<Post> getPostsAboveDate(@JsonFormat(pattern = "yyyy-mm-dd") LocalDate checkDate);
}
