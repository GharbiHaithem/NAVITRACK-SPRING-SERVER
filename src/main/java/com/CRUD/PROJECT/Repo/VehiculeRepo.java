package com.CRUD.PROJECT.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.CRUD.PROJECT.entities.Appareil;
import com.CRUD.PROJECT.entities.Category;
import com.CRUD.PROJECT.entities.Vehicule;
@Repository
public interface VehiculeRepo extends MongoRepository<Vehicule, String> {
	List<Vehicule> findByClient(String clientId);
	

	Vehicule findByMatricule(String matricule);

	@Repository
	public interface AppareilRepo  extends MongoRepository<Appareil, String> {
		
	}

	Vehicule findByAppareil(String appareilId);



	List<Vehicule> findByMatriculeContaining(String matricule);




	 







	



	

	
}
