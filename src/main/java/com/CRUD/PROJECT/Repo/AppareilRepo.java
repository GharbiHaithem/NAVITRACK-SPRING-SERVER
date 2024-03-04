package com.CRUD.PROJECT.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.CRUD.PROJECT.entities.Appareil;
import com.CRUD.PROJECT.entities.Category;
@Repository

public interface AppareilRepo extends MongoRepository<Appareil, String> {
	
	@Repository
	public interface CategoryRepo  extends MongoRepository<Category, String> {
	   
	}

	public List<Appareil> findByCodeContaining(String terme);

	public Optional<Appareil> findByCode(String serialNumber);



}

