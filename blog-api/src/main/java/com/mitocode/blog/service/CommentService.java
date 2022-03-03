package com.mitocode.blog.service;

import java.util.List;

import com.mitocode.blog.payload.CommentDto;

public interface CommentService { // extends CrudService<Comment, Long> {
	CommentDto createComment(Long postId, CommentDto commentDto);

	List<CommentDto> getCommentsByPostId(Long postId);

	CommentDto getCommentById(Long postId, Long commentId);

	CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest);

	void deleteComment(Long postId, Long commentId);

}
