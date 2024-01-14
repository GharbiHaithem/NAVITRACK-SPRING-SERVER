package com.CRUD.PROJECT.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.CRUD.PROJECT.entities.Appareil;
import com.CRUD.PROJECT.entities.ArchiveAppareil;
import com.CRUD.PROJECT.entities.Category;
@Repository
public interface ArchiveAppareilRepo extends MongoRepository<ArchiveAppareil, String>{
	@Repository
	public interface CategoryRepo  extends MongoRepository<Category, String> {
	   
	}

	public void save(Appareil appareil);
}
