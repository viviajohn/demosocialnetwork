package com.example.demosocialnetwork.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demosocialnetwork.dtos.FriendDto;
import com.example.demosocialnetwork.dtos.UserDto;
import com.example.demosocialnetwork.entities.FriendEntity;
import com.example.demosocialnetwork.entities.UserEntity;
import com.example.demosocialnetwork.respositories.FriendRepo;

@RestController
@RequestMapping(path = "/user")
public class FriendController {
	
	@Autowired
	private FriendRepo friendRepo;
	
	@PostMapping
	public ResponseEntity <FriendDto> createFriend(@RequestBody FriendDto friendDto) {
		FriendEntity friendEntity = new FriendEntity();
		friendEntity.setUserId(friendDto.getUserId());
		friendEntity.setFriendId(friendDto.getFriendId());
		friendRepo.save(friendEntity);
		
		friendDto.setId(friendEntity.getId());
		friendDto.setUserId(friendEntity.getUserId());
		friendDto.setFriendId(friendEntity.getFriendId());
		
		return new ResponseEntity<FriendDto>(friendDto, HttpStatus.CREATED);
	}
	
	@GetMapping("/{friendId}")
	public ResponseEntity<FriendDto> getFriend(@PathVariable("friendId") Integer friendId) {
		
		Optional<FriendEntity> optionalEntity = friendRepo.findById(friendId);
		if(optionalEntity.isPresent()) {
			
			FriendDto friendDto = new FriendDto();
			FriendEntity friendEntity = optionalEntity.get();
			
			friendDto.setId(friendEntity.getId());
			friendDto.setUserId(friendEntity.getUserId());
			friendDto.setFriendId(friendEntity.getFriendId());
						
			return new ResponseEntity<FriendDto>(friendDto, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<FriendDto>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping
	public ResponseEntity<List<FriendDto>> getFriend() {
		
		List<FriendEntity> friendEntities = friendRepo.findAll();
		List<FriendDto> friendDtos = new ArrayList<FriendDto>();
		
		for(FriendEntity friendEntity : friendEntities) {
			
			FriendDto friendDto = new FriendDto();
			friendDto.setId(friendEntity.getId());
			friendDto.setUserId(friendEntity.getUserId());
			friendDto.setFriendId(friendEntity.getFriendId());
			friendDtos.add(friendDto);
		}
		
		return new ResponseEntity<List<FriendDto>>(friendDtos, HttpStatus.OK);
		
	}
	
	@PutMapping("/{friendId}")
	public ResponseEntity<FriendDto> updateFriend(@PathVariable("friendId") Integer friendId, @RequestBody FriendDto friendDto) {
		friendDto.setId(friendId);
		FriendEntity friendEntity = new FriendEntity();
		
		friendEntity.setId(friendDto.getId());
		friendEntity.setUserId(friendDto.getUserId());
		friendEntity.setFriendId(friendDto.getFriendId());
		friendRepo.save(friendEntity);
		
		friendDto.setId(friendEntity.getId());
		friendDto.setUserId(friendEntity.getUserId());
		friendDto.setFriendId(friendEntity.getFriendId());
		
		
		return new ResponseEntity<FriendDto>(friendDto, HttpStatus.ACCEPTED);
	}
	
	@PatchMapping("/{friendId}")
	public ResponseEntity<FriendDto> updateFriendPatch(@PathVariable("friendId") Integer friendId, @RequestBody FriendDto friendDto) {
		
		Optional<FriendEntity> optionalEntity = friendRepo.findById(friendId);
		if(optionalEntity.isPresent()) {
			
			FriendEntity friendEntity = optionalEntity.get();
			
			if(friendDto.getUserId() != null)
				friendEntity.setUserId(friendDto.getUserId());
			
			if(friendDto.getFriendId() != null)
				friendEntity.setFriendId(friendDto.getFriendId());
			
			friendRepo.save(friendEntity);
			
			return new ResponseEntity<FriendDto>(friendDto, HttpStatus.ACCEPTED);
		}
		
		else
			
			return new ResponseEntity<FriendDto>(HttpStatus.NOT_FOUND);
		
	}

}
