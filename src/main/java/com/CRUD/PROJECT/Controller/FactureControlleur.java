package com.CRUD.PROJECT.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CRUD.PROJECT.Response;
import com.CRUD.PROJECT.Repo.VehiculeRepo;
import com.CRUD.PROJECT.Service.FactureService;
import com.CRUD.PROJECT.entities.Client;
import com.CRUD.PROJECT.entities.Facture;
import com.CRUD.PROJECT.entities.Vehicule;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/factures")
public class FactureControlleur {
  @Autowired
   private VehiculeRepo vehiculeRepo;
  @Autowired
  private FactureService factureService;
  @GetMapping("/generate/{id}")
  public Facture searchVehiculesForClient(@PathVariable(name="id") String clientid) {
   
      List<Vehicule> vehicules = vehiculeRepo.findByClient(clientid);
      return  factureService.generateMonthlyInvoice(vehicules);
  }

@GetMapping("/facture/{id}")
public List<Facture> getFacture( @PathVariable(name="id") String clientId){
	return factureService.getFact(clientId);
	
}
  @Operation(hidden = true) 
@GetMapping("/")
public List<Facture> allFactures() {
	return factureService.getAllFactures();
}
@Operation(hidden = true) 
@PostMapping("/genererMensuelle")
public ResponseEntity<String>  genererNouvelleFactureMensuelle(){
	factureService.genererNouvelleFacture();
	return ResponseEntity.ok("Nouvelle facture generer avec succees");
}
@Operation(hidden = true) 
@PutMapping("/update-etat/{id}")
public ResponseEntity<Response> updateEtatPayment(@PathVariable(name="id") String factureId){
	return factureService.updateEtatFact(factureId);
	  
}
}