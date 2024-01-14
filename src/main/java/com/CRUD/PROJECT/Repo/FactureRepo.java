package com.CRUD.PROJECT.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.CRUD.PROJECT.entities.Facture;
import java.util.List;
import com.CRUD.PROJECT.entities.Client;

@Repository
public interface FactureRepo extends MongoRepository<Facture, String>{

	List<Facture> findByClientId(String clientId);

	List<Facture> findByClientId(Client clientId);

	

	

	



}
