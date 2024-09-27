package com.CRUD.PROJECT.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.CRUD.PROJECT.entities.Appareil;

import com.CRUD.PROJECT.entities.User;

@Repository
public interface UserAccountRepo extends MongoRepository<User, String> {

   
   List<User> findAll(); // Cette méthode est déjà fournie par MongoRepository
}
