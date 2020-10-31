package com.example.demosocialnetwork.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.demosocialnetwork.models.PostModel;

@Entity
@Table(name = "post")
public class PostEntity extends PostModel{

}
