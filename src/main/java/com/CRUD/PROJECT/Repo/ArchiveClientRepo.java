package com.CRUD.PROJECT.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.CRUD.PROJECT.entities.ArchiveClient;
@Repository
public interface ArchiveClientRepo extends MongoRepository<ArchiveClient, String>{

}
