package com.example.demosocialnetwork.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demosocialnetwork.entities.FriendEntity;

public interface FriendRepo extends JpaRepository<FriendEntity, Integer>{

}
