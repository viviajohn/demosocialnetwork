package com.example.demosocialnetwork.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demosocialnetwork.dtos.PostDto;
import com.example.demosocialnetwork.entities.PostEntity;
import com.example.demosocialnetwork.respositories.PostRepo;

@RestController
@RequestMapping(path = "/user")
public class PostController {
	
	@Autowired
	private PostRepo postRepo;
	
	@PostMapping
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
		PostEntity postEntity = new PostEntity();
		postEntity.setUserId(postDto.getUserId());
		postEntity.setText(postDto.getText());
		postRepo.save(postEntity);
		
		postDto.setId(postEntity.getId());
		postDto.setUserId(postEntity.getUserId());
		postDto.setText(postEntity.getText());
		
		return new ResponseEntity<PostDto>(postDto, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getPost(@PathVariable("postId") Integer postId) {
		
		Optional<PostEntity> optionalEntity = postRepo.findById(postId);
		if(optionalEntity.isPresent()) {
			
			PostDto postDto = new PostDto();
			PostEntity postEntity = optionalEntity.get();
			
			postDto.setId(postEntity.getId());
			postDto.setUserId(postEntity.getUserId());
			postDto.setText(postEntity.getText());
			
			return new ResponseEntity<PostDto> (postDto, HttpStatus.OK);
		}
		
		else
			return new ResponseEntity<PostDto>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping
	public ResponseEntity<List<PostDto>> getPost() {
		
		List<PostEntity> postEntities = postRepo.findAll();
		List<PostDto> postDtos = new ArrayList<PostDto>();
		
		for(PostEntity postEntity : postEntities) {
			
			PostDto postDto = new PostDto();
			postDto.setId(postEntity.getId());
			postDto.setUserId(postEntity.getUserId());
			postDto.setText(postEntity.getText());
			postDtos.add(postDto);
		}
		
		return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
	}
	
	@PutMapping("/{postId}")
	public ResponseEntity<PostDto> updateUser(@PathVariable("postId") Integer postId, @RequestBody PostDto postDto) {
		
		postDto.setId(postId);
		PostEntity postEntity = new PostEntity();
		
		postEntity.setId(postDto.getId());
		postEntity.setUserId(postDto.getUserId());
		postEntity.setText(postDto.getText());
		postRepo.save(postEntity);
		
		postDto.setId(postEntity.getId());
		postDto.setUserId(postEntity.getUserId());
		postDto.setText(postEntity.getText());
		
		return new ResponseEntity<PostDto>(postDto, HttpStatus.ACCEPTED);
	}
	
	@PatchMapping("/{postId}")
	public ResponseEntity<PostDto> updateUserPatch(@PathVariable("postId") Integer postId, @RequestBody PostDto postDto) {
		
		Optional<PostEntity> optionalEntity = postRepo.findById(postId);
		if(optionalEntity.isPresent()) {
			
			PostEntity postEntity = optionalEntity.get();
			
			if(postEntity.getUserId() != null)
				postEntity.setUserId(postDto.getUserId());
			
			if(postEntity.getText() != null)
				postEntity.setText(postDto.getText());
			
			postRepo.save(postEntity);
			
			return new ResponseEntity<PostDto>(postDto, HttpStatus.ACCEPTED);			
		}
		else
			return new ResponseEntity<PostDto>(HttpStatus.NOT_FOUND);		
	}
	
	@DeleteMapping("/{postId}")
	public ResponseEntity<PostDto> deletePost(@PathVariable("postId") Integer postId) {
		postRepo.deleteById(postId);
		
		return new ResponseEntity<PostDto>(HttpStatus.NO_CONTENT);
	}

}
