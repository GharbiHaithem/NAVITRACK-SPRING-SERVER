package com.CRUD.PROJECT.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.CRUD.PROJECT.entities.ArchiveVehicule;
@Repository
public interface ArchiveVehiculeRepo extends MongoRepository<ArchiveVehicule, String> {



}
