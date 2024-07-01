package com.anand.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.anand.dto.CommentDTO;
import com.anand.exception.ResourceNotFoundException;
import com.anand.model.Comment;
import com.anand.repository.CommentRepo;
import com.anand.service.CommnetService;

@Service
public class CommnetServiceImpl implements CommnetService {

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDTO saveComment(CommentDTO commentDto) {
		Comment commentEntity = this.modelMapper.map(commentDto, Comment.class);
		commentEntity.setDateOfComment(LocalDate.now());
		Comment savedComment = this.commentRepo.save(commentEntity);
		return this.modelMapper.map(savedComment, CommentDTO.class);
	}

	@Override
	public CommentDTO updateComment(CommentDTO commentDetails, Long id) {
		Comment comment = commentRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id));
		comment.setUser(commentDetails.getUser());
		comment.setText(commentDetails.getText());
		comment.setDateOfComment(LocalDate.now());

		Comment updatedComment = commentRepo.save(comment);
		return modelMapper.map(updatedComment, CommentDTO.class);
	}

	@Override
	public void deleteComment(Long id) {
		Comment com = this.commentRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentId", id));
		this.commentRepo.delete(com);
	}

	@Override
	public List<CommentDTO> getAllComments() {
		List<CommentDTO> findAll = commentRepo.findAll().stream()
				.map(comment -> modelMapper.map(comment, CommentDTO.class)).collect(Collectors.toList());
		return findAll;
	}

	@Override
	public List<CommentDTO> getCommentsByUsername(String username) {
		List<CommentDTO> findBy = commentRepo.findByUser(username).stream()
				.map(comment -> modelMapper.map(comment, CommentDTO.class)).collect(Collectors.toList());
		return findBy;
	}

	@Override
	public List<CommentDTO> getCommentsByDate(LocalDate date) {
		List<CommentDTO> findByDateOfComment = commentRepo.findByDateOfComment(date).stream()
				.map(comment -> modelMapper.map(comment, CommentDTO.class)).collect(Collectors.toList());
		return findByDateOfComment;
	}

}
