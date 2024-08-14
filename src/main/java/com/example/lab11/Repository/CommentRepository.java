package com.example.lab11.Repository;

import com.example.lab11.Model.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("select c from Comment c where c.comment_id=?1")
    Comment searchCommentByCommentId(int commentId);

    @Query("select d from Comment d where d.comment_date>=?1")
    List<Comment> getCommentsAboveDate(@JsonFormat(pattern = "yyyy-mm-dd") LocalDate date);

    @Query("select u from Comment u where u.user_id=?1")
    List<Comment> getCommentByUser_id(int user_id);
}
