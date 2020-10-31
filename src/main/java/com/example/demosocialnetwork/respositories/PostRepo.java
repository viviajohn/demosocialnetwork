package com.example.demosocialnetwork.respositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demosocialnetwork.entities.PostEntity;

public interface PostRepo extends JpaRepository<PostEntity, Integer>{

}
