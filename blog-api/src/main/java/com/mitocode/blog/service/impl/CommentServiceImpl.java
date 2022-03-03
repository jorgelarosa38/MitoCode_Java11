package com.mitocode.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.mitocode.blog.entity.Comment;
import com.mitocode.blog.entity.Post;
import com.mitocode.blog.exception.ResourceNotFoundException;
import com.mitocode.blog.payload.CommentDto;
import com.mitocode.blog.repository.CommentRepository;
import com.mitocode.blog.repository.PostRepository;
import com.mitocode.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;
	private PostRepository postRepository;
	private ModelMapper mapper;

	public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
		this.mapper = mapper;
	}

	@Override
	public CommentDto createComment(Long postId, CommentDto commentDto) {
		// TODO Auto-generated method stub
		
		Comment comment=mapToEntity(commentDto);
		
		Post post=postRepository.findById(postId)
				.orElseThrow(
						()->new ResourceNotFoundException("Post","id", postId)
				);
		comment.setPost(post);
		
		Comment newComment=commentRepository.save(comment);
		
		return mapToDTO(newComment);
	}

	@Override
	public List<CommentDto> getCommentsByPostId(Long postId) {
		// TODO Auto-generated method stub
		List<Comment> comments = commentRepository.findByPostId(postId);

		return comments.stream()
				.map(comment->mapToDTO(comment))
				.collect(Collectors.toList());
	}

	@Override
	public CommentDto getCommentById(Long postId, Long commentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteComment(Long postId, Long commentId) {
		// TODO Auto-generated method stub

	}

	private CommentDto mapToDTO(Comment comment) {
		CommentDto commentDto=mapper.map(comment, CommentDto.class);
		return commentDto;
	}

	private Comment mapToEntity(CommentDto commentDto) {
		Comment comment=mapper.map(commentDto, Comment.class);
		return comment;
	}

}
