package com.CRUD.PROJECT.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.CRUD.PROJECT.Response;
import com.CRUD.PROJECT.Repo.AppareilRepo;
import com.CRUD.PROJECT.Repo.ArchiveAppareilRepo;
import com.CRUD.PROJECT.Repo.VehiculeRepo;
import com.CRUD.PROJECT.entities.Appareil;
import com.CRUD.PROJECT.entities.ArchiveAppareil;

import com.CRUD.PROJECT.entities.Vehicule;


@Service
public class AppareilService {
@Autowired
private AppareilRepo appareilRepo;
private ArchiveAppareilRepo archiveAppareilRepo ;
private VehiculeRepo vehiculeRepo;
@Autowired
public AppareilService(AppareilRepo appareilRepo , ArchiveAppareilRepo archiveAppareilRepo ,VehiculeRepo vehiculeRepo  ) {
    this.appareilRepo = appareilRepo;
    this.archiveAppareilRepo = archiveAppareilRepo;
    this.vehiculeRepo = vehiculeRepo;
}

public ResponseEntity<Response> save(Appareil appareil) {
    String serialNumber = appareil.getSerialNumber();
    Optional<Appareil> findSerialAppareil = appareilRepo.findBySerialNumber(serialNumber);

    if (findSerialAppareil.isPresent()) {
        // L'appareil avec le même numéro de série existe déjà
        Response response = new Response("Erreur : Appareil avec le même numéro de série existe déjà", null, null, null, null, null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    } else {
        // Aucun appareil trouvé avec le même numéro de série, on peut l'ajouter
        Appareil createdAppareil = appareilRepo.save(appareil);
        Response response = new Response("Appareil créé avec succès", null, createdAppareil, null, null, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

public List<Appareil> getAll() {
	// TODO Auto-generated method stub
	return appareilRepo.findAll();
}
public ResponseEntity<Response> deleteAppareil(String appareilId) {
	ArchiveAppareil archiveAppareil = new ArchiveAppareil();
	 Optional<Appareil> findAppareil = appareilRepo.findById(appareilId);
	 if(findAppareil.isPresent()) {
		 System.out.println(findAppareil.get().toString());
		 appareilRepo.deleteById(findAppareil.get().get_id());
		 archiveAppareil.set_id(findAppareil.get().get_id());
		 archiveAppareil.setAccessoire(findAppareil.get().getAccessoire());
		 archiveAppareil.setCategory(findAppareil.get().getCategory());
		 archiveAppareil.setPriceUnite(findAppareil.get().getPriceUnite());
		 archiveAppareil.setQtyStock(findAppareil.get().getQtyStock());
		 archiveAppareil.setSerialNumber(findAppareil.get().getSerialNumber());
		 
		 archiveAppareilRepo.save(archiveAppareil);
		 Vehicule findVehicule =  vehiculeRepo.findByAppareil(appareilId);
		 System.out.println(findVehicule);
		 findVehicule.setAppareil(null);
		 vehiculeRepo.save(findVehicule);
		   return ResponseEntity.ok(new Response("Appareil supprimé", null,findAppareil.get(),null, null, null)); 
		 
		 }else {
			  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Appareil non trouvé", null, null, null, null));
	 }
}

public List<Appareil> searchAppareil(String terme) {
	
	return appareilRepo.findBySerialNumberContaining(terme);
}

public ResponseEntity<Response> updateQuantity(String _id,Appareil qtyStock) {
	Optional<Appareil> findAppareil = appareilRepo.findById(_id);
	System.out.println(findAppareil.get().toString());
	
	
	 if (findAppareil.isPresent()) {
		  Appareil existingAppareil = findAppareil.get();
	        Integer qty = (Integer) existingAppareil.getQtyStock();
	        Integer qtyToAdd = (Integer) qtyStock.getQtyStock();

	       
	        existingAppareil.setQtyStock(qty + qtyToAdd);

	        appareilRepo.save(existingAppareil);

	     

	        return ResponseEntity.ok(new Response("Quantité mise à jour avec succès"));
	    }
	return null;
}

public Optional<Appareil> getappareil(String appareilId) {
	// TODO Auto-generated method stub
	return appareilRepo.findById(appareilId);
}

public ResponseEntity<Response> updateAppareil(String id, Appareil dataAppareil) {
	Appareil existAppareil = appareilRepo.findById(id).orElse(null);
	if(existAppareil != null ) {
		existAppareil.setAccessoire(dataAppareil.getAccessoire());
		existAppareil.setCategory(dataAppareil.getCategory());
		existAppareil.setPriceUnite(dataAppareil.getPriceUnite());
		existAppareil.setQtyStock(dataAppareil.getQtyStock());
		existAppareil.setSerialNumber(dataAppareil.getSerialNumber());
		 appareilRepo.save(existAppareil);
		return ResponseEntity.ok(new Response("appareiiI updated", null, existAppareil, null, null, null));
	}
	return null;
}






}
