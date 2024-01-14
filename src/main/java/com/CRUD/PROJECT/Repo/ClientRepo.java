package com.CRUD.PROJECT.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.CRUD.PROJECT.Response;
import com.CRUD.PROJECT.entities.Client;
@Repository
public interface ClientRepo extends MongoRepository<Client, String>{
	
   @Repository
	public interface UserAccount extends MongoRepository<UserAccount, String> {
	   
   }
public List<Client> findByMatriculeSocialeContaining(String termeRecherche);


Optional<Client> findByNomCompletContaining(String nomComplet);
}
