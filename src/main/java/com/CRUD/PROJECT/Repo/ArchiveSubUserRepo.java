package com.CRUD.PROJECT.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.CRUD.PROJECT.entities.ArchiveSubUser;
@Repository
public interface ArchiveSubUserRepo extends MongoRepository<ArchiveSubUser, String>{

}