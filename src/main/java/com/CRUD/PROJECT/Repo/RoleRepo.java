package com.CRUD.PROJECT.Repo;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.CRUD.PROJECT.entities.Role;

public interface RoleRepo extends MongoRepository<Role, String> {
}