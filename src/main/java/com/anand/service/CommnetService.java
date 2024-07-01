package com.anand.service;

import java.time.LocalDate;
import java.util.List;
import com.anand.dto.CommentDTO;



public interface CommnetService {
	CommentDTO saveComment(CommentDTO commentDto);
	CommentDTO updateComment(CommentDTO commentDetails,Long id);
	void deleteComment(Long id);
	List<CommentDTO> getAllComments();
	List<CommentDTO> getCommentsByUsername(String username);
	List<CommentDTO> getCommentsByDate(LocalDate date);

}
