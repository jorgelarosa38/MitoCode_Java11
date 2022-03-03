package com.mitocode.blog.repository;

import org.springframework.stereotype.Repository;

import com.mitocode.blog.entity.Post;

@Repository
public interface PostRepository extends GenericRepository<Post, Long>{

}
