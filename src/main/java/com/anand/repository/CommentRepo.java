package com.anand.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anand.model.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long>{
	
	List<Comment> findByUser(String user);
    List<Comment> findByDateOfComment(LocalDate dateOfComment);

}
