package com.anand.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anand.dto.CommentDTO;
import com.anand.exception.ApiResponse;
import com.anand.service.CommnetService;

@RestController
@RequestMapping("/api/v2/comments")
public class CommentController {

	@Autowired
	private CommnetService commnetService;

	@PostMapping
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {
		CommentDTO saveComment = commnetService.saveComment(commentDTO);
		return new ResponseEntity<CommentDTO>(saveComment, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CommentDTO> updateComment(@PathVariable Long id, @RequestBody CommentDTO commentDetails) {
		CommentDTO updatedComment = commnetService.updateComment(commentDetails, id);
		return ResponseEntity.ok(updatedComment);

	}

	@DeleteMapping("/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long commentId) {
		commnetService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully !!", true), HttpStatus.OK);

	}

	@GetMapping("/search")
	public ResponseEntity<List<CommentDTO>> searchComments(@RequestParam(required = false) String username,
			@RequestParam(required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date) {

		List<CommentDTO> comments;
		if (username != null) {
			comments = commnetService.getCommentsByUsername(username);
		} else if (date != null) {
			comments = commnetService.getCommentsByDate(date);
		} else {
			comments = commnetService.getAllComments();
		}

		return ResponseEntity.ok(comments);
	}

	@GetMapping
	public ResponseEntity<List<CommentDTO>> getAllComments() {
		List<CommentDTO> comments = commnetService.getAllComments();
		return ResponseEntity.ok(comments);
	}

}
