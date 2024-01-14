package com.CRUD.PROJECT.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.CRUD.PROJECT.entities.Category;

@Repository
public interface CategoryRepo extends MongoRepository<Category, String> {
	  Category findByName(String name);
}
