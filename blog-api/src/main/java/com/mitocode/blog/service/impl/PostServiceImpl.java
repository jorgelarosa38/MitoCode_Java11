package com.mitocode.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitocode.blog.entity.Post;
import com.mitocode.blog.exception.ResourceNotFoundException;
import com.mitocode.blog.payload.PostDto;
import com.mitocode.blog.payload.PostResponse;
import com.mitocode.blog.repository.PostRepository;
import com.mitocode.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;
	private ModelMapper mapper;

	public PostServiceImpl(PostRepository postRepository,ModelMapper mapper) {
		this.postRepository = postRepository;
		this.mapper=mapper;
	}

	@Transactional
	@Override
	public PostDto createPost(PostDto postDto) {
		// convert DTO to entity
		Post post = mapToEntity(postDto);
		Post newPost = postRepository.save(post);

		// convert entity to DTO
		PostDto postResponse = mapToDto(newPost);
		return postResponse;
	}


	@Transactional(readOnly = true)
	@Override
	public PostResponse getAllPosts(int pageNo, int pageSize,String sortBy, String sortDir) {
		
		Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
					?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		

		Pageable pageable=PageRequest.of(pageNo, pageSize,sort);
		
		Page<Post> posts=postRepository.findAll(pageable);
		
		List<Post> listOfPosts = posts.getContent();
				
		List<PostDto> content=listOfPosts.stream()
				.map(post->mapToDto(post))
				.collect(Collectors.toList());
			
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLast(posts.isLast());
		
		return postResponse;
	}

	@Transactional(readOnly = true)
	@Override
	public PostDto getPostById(long id) {
		Post post=postRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Post", "id", id));
		return mapToDto(post);
	}

	@Transactional
	@Override
	public PostDto updatePost(PostDto postDto, long id) {
		// get post by id from the database
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

		
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());

		Post updatePost = postRepository.save(post);
		
		//update from table X
		
		return mapToDto(updatePost);
	}

	@Transactional
	@Override
	public void deletePostById(long id) {
		// get post by id from the database
		Post post = postRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Post", "id", id));
		
		postRepository.delete(post);
	}

	// convert Entity to Dto
	private PostDto mapToDto(Post post) {
		PostDto postDto=mapper.map(post, PostDto.class);		
		return postDto;
	}

	// convert DTO to entity
	private Post mapToEntity(PostDto postDto) {
		Post post=mapper.map(postDto, Post.class);
		return post;
	}

}
