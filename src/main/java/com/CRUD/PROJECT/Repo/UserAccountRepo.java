package com.CRUD.PROJECT.Repo;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import com.CRUD.PROJECT.entities.User;
@Repository
public interface UserAccountRepo extends MongoRepository<User, String>{



}
