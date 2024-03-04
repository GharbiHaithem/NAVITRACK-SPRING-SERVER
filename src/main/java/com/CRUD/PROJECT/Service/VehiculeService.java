package com.CRUD.PROJECT.Service;




import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.CRUD.PROJECT.Response;
import com.CRUD.PROJECT.Repo.AppareilRepo;
import com.CRUD.PROJECT.Repo.ArchiveVehiculeRepo;
import com.CRUD.PROJECT.Repo.ClientRepo;
import com.CRUD.PROJECT.Repo.FactureRepo;
import com.CRUD.PROJECT.Repo.VehiculeRepo;
import com.CRUD.PROJECT.entities.Appareil;
import com.CRUD.PROJECT.entities.ArchiveVehicule;
import com.CRUD.PROJECT.entities.Client;
import com.CRUD.PROJECT.entities.Facture;
import com.CRUD.PROJECT.entities.Vehicule;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;

@Service
public class VehiculeService {
	@Autowired
	private VehiculeRepo repo;
	private AppareilRepo repo1;
	private ArchiveVehiculeRepo repo2;
	private FactureRepo repo3;
	private ClientRepo repo4;
	  @Autowired
	    public VehiculeService(VehiculeRepo vehiculeRepo,ClientRepo clientRepo , AppareilRepo appareilRepo ,ArchiveVehiculeRepo archiveVehiculeRepo , FactureRepo factureRepo) {
	        this.repo = vehiculeRepo;
	        this.repo1 = appareilRepo;
	        this.repo2 = archiveVehiculeRepo;
	        this.repo3 = factureRepo;
	        this.repo4 = clientRepo;
	    }
	  public ResponseEntity<Response> saveorUpdate(Vehicule vehicule, String appareilId) throws ParseException {
	        Vehicule existingVehicule = repo.findByMatricule(vehicule.getMatricule());
	        List<Appareil> maListe = new ArrayList<>();
	        Date formattedDateDebut = null;
	        Double priceTotal = null;
	        Facture facture = new Facture(); 
	        if (existingVehicule != null) {
	            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Response("L'immatriculation existe déjà.", null, null, null, null));
	        }
	        vehicule.setSaveDate(new Date());
            Vehicule createdVehicule = repo.save(vehicule);
            String clientId = (createdVehicule.getClient().getid());
            
            Optional<Appareil> findAppareil = repo1.findById(appareilId);
            if (findAppareil.isPresent()) {
                Appareil appareil = findAppareil.get();
                appareil.setAffected(true);
				repo1.save(appareil);
             }
            List<Facture> findFactures = repo3.findByClientId(clientId);

            if (findFactures.isEmpty()) {
             
			
              List<Vehicule> findListVehicule = repo.findByClient(clientId);
              for(int i =0 ;i< findListVehicule.size() ; i++) {
            	  Vehicule vehiculefinded = findListVehicule.get(i);
            	 maListe.add(vehiculefinded.getAppareil());
            	 facture.setAppareilId(maListe);
            	  priceTotal = (double) (facture.getPriceUnitaire() * findListVehicule.size());  
              }
              facture.setTotal(priceTotal);
              facture.setClient(vehicule.getClient());
              Date datedebut = vehicule.getSaveDate();
              SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

          
           String formattedDate = dateFormat.format(datedebut);
            
              
             
              SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
              Date initialDate = inputFormat.parse(formattedDate);
              
           
              Calendar calendar = Calendar.getInstance();
              calendar.setTime(initialDate);
              calendar.set(Calendar.DAY_OF_MONTH, 15);
              Date modifiedDate = calendar.getTime();
              
             
              SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
              String formattedDate2 = outputFormat.format(modifiedDate);
              
              System.out.println(formattedDate2);
              facture.setDateDebut(formattedDate2);
              calendar.add(Calendar.MONTH, 1);

              Date modifiedDatefin = calendar.getTime();
              String formattedDatefin = dateFormat.format(modifiedDatefin);
              System.out.println(formattedDatefin);
              facture.setDateFin(formattedDatefin);
              Random random = new Random();
              int number = random.nextInt(90000000) + 10000000;
             String  numberString = String.format("%08d", number);
             facture.setNumeroFacture(numberString);
             repo3.save(facture);
            } else {
                System.out.println("La liste des factures est vide.");
				int lastIndex = findFactures.size() - 1;
				Facture existingFacture = findFactures.get(lastIndex);
               
                List<Appareil> existingAppareilIds = existingFacture.getAppareilId();
                existingAppareilIds.add(createdVehicule.getAppareil());
                maListe.add(createdVehicule.getAppareil());
                System.out.println(existingFacture.getAppareilId());
                System.out.println(createdVehicule.getAppareil());
                System.out.println(existingFacture.getTotal());
                repo3.save(existingFacture);
             






            }
	        return ResponseEntity.ok(new Response("Véhicule créé avec succès", createdVehicule, null, null, null, null));
	    }
	
	





	public List<Vehicule> listVehicules() {
		
	return this.repo.findAll();
	}

	public Optional<Vehicule> getOneVehicule(String vehiculeId) {
		Optional<Vehicule> vehicule = repo.findById(vehiculeId); 
		System.out.println(vehicule);
		return vehicule;
		
	}

	public Vehicule modifierVehicule(String id, Vehicule vehiculeModifie) {
		 Vehicule vehiculeExistant = repo.findById(id).orElse(null);
	        System.out.println("id de vehicue "+id);
	        if (vehiculeExistant != null) {
	          System.out.println("updating vehicue");
	        	vehiculeExistant.setAnneeMiseEnCirculation(vehiculeModifie.getAnneeMiseEnCirculation());
	        	vehiculeExistant.setMarque(vehiculeModifie.getMarque());
	        	vehiculeExistant.setMatricule(vehiculeModifie.getMatricule());
	        	vehiculeExistant.setModel(vehiculeModifie.getModel());
	        	vehiculeExistant.setPuisanceFiscale(vehiculeModifie.getPuisanceFiscale());
	        	vehiculeExistant.setQtyCarburantLitre(vehiculeModifie.getQtyCarburantLitre());
	        	vehiculeExistant.setType(vehiculeModifie.getType());
	        	vehiculeExistant.setClient(vehiculeModifie.getClient());
	        	vehiculeExistant.setAppareil(vehiculeModifie.getAppareil());
	            
	            return repo.save(vehiculeExistant);
	        } else {
	            
	            return null;
	        }
	    }


public ResponseEntity<Response> deleteVehicule(String vehiculeId) {
	ArchiveVehicule archiveVehicule = new ArchiveVehicule();
    Optional<Vehicule> optionalVehicule = repo.findById(vehiculeId);

    if (optionalVehicule.isPresent()) {
    	archiveVehicule.setid(optionalVehicule.get().getId());
    	archiveVehicule.setAnneeMiseEnCirculation(optionalVehicule.get().getAnneeMiseEnCirculation());
    	archiveVehicule.setAppareil(optionalVehicule.get().getAppareil());
    	archiveVehicule.setClient(optionalVehicule.get().getClient());
    	archiveVehicule.setMarque(optionalVehicule.get().getMarque());
    	archiveVehicule.setMatricule(optionalVehicule.get().getMatricule());
    	archiveVehicule.setModel(optionalVehicule.get().getModel());
    	archiveVehicule.setPuisanceFiscale(optionalVehicule.get().getPuisanceFiscale());
    	archiveVehicule.setQtyCarburantLitre(optionalVehicule.get().getQtyCarburantLitre());
    	archiveVehicule.setSaveDate(optionalVehicule.get().getSaveDate());
    	archiveVehicule.setType(optionalVehicule.get().getType());
    	repo2.save(archiveVehicule);
        repo.deleteById(vehiculeId);
        return ResponseEntity.ok(new Response("Véhicule supprimé", optionalVehicule.get(), null, null, null, null));
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Véhicule non trouvé", null, null, null, null));
    }
}



public List<Vehicule> searchVehicule(String matricule ) {

	        return repo.findByMatriculeContaining(matricule);

}
public List<Vehicule> getVehiculesClient(String clientId) {
	
	return repo.findByClient(clientId);
}
public List<Vehicule> searchVehiculesByClientName(String clientName) {
    Optional<Client> clientOptional = repo4.findByNomCompletContaining(clientName);
    
    if (clientOptional.isPresent()) {
        Client client = clientOptional.get();
        System.out.println(client);
        return repo.findByClient(client.getid());
    } else {
     
        return Collections.emptyList();
    }
}





}	

	


