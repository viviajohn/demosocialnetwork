package com.example.demosocialnetwork.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demosocialnetwork.entities.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer> {

}
