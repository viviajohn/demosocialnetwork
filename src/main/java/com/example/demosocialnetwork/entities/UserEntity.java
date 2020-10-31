package com.example.demosocialnetwork.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.demosocialnetwork.models.UserModel;

@Entity
@Table(name = "user")
public class UserEntity extends UserModel{

}
