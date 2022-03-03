package com.mitocode.blog.repository;

import java.util.List;

import com.mitocode.blog.entity.Comment;

public interface CommentRepository extends GenericRepository<Comment, Long>{
	List<Comment> findByPostId(long postId);
}
