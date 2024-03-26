package com.CRUD.PROJECT.Controller;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CRUD.PROJECT.Response;
import com.CRUD.PROJECT.Repo.VehiculeRepo;
import com.CRUD.PROJECT.Service.FactureService;
import com.CRUD.PROJECT.Service.VehiculeService;
import com.CRUD.PROJECT.entities.Client;
import com.CRUD.PROJECT.entities.Vehicule;

import io.swagger.v3.oas.annotations.Operation;




@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/car")
public class VehiculeController {
	
	
	
	 private  VehiculeRepo vehiculeRepo;
    @Autowired
    public VehiculeService vehiculeService;
    @Autowired
    public FactureService factureService ;
    @Operation(hidden = true) 
    @PostMapping(value="/save")
    public ResponseEntity<Response>  saveVehicule(@RequestBody Vehicule vehicule ) throws ParseException {
    	String appareilId = vehicule.getAppareil().getId();
        System.out.println("IID"+vehicule.getAppareil().getId());
        factureService.createFacture(vehicule);
    	return   vehiculeService.saveorUpdate(vehicule,appareilId);
    	
    }
      @Operation(hidden = true) 
    @GetMapping("/test")
    public String testEndpoint() {
        return "Ceci est un point de terminaison de test";
    }
   
    @GetMapping(value="/getAll")
	public List<Vehicule>getallUsers() {
	    return vehiculeService.listVehicules();
	}
  
    @PutMapping("/edit/{id}")
    public Vehicule modifierVehicule(@PathVariable(name="id") String _id, @RequestBody Vehicule vehiculeModifie) {
        return vehiculeService.modifierVehicule(_id, vehiculeModifie);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteVehicule(@PathVariable(name="id") String vehiculeId){
		return vehiculeService.deleteVehicule(vehiculeId);
    	
    }
    @GetMapping("/search")
    public List<Vehicule> searchVehicule(
    	   
    	    @RequestParam(name = "matricule", required = true) String matricule
    	) {
        List<Vehicule> vehicules = vehiculeService.searchVehicule(matricule);
        return vehicules;
    }
    
    @GetMapping("/vehicule/{id}")
    public Optional<Vehicule> getVehicule(@PathVariable(name="id") String vehiculeId){ 
    return vehiculeService.getOneVehicule(vehiculeId) ; 
    }
  @GetMapping("/vehicule/companie/{id}")
  public List<Vehicule> getVehiculeClient(@PathVariable(name="id") String clientId){
	  return vehiculeService.getVehiculesClient(clientId);
  }
 
  @GetMapping("/search1")
  public List<Vehicule> getVehiculesByNomClient(@RequestParam(name = "nomComplet", required = true) String nomClient) {
      return vehiculeService.searchVehiculesByClientName(nomClient);
  }

  @GetMapping("/vehicule/matricule/{matricule}")
  public ResponseEntity<Vehicule> getVehiculeByMatricule(@PathVariable String matricule){
    Vehicule vehicule = vehiculeService.getVehiculeByMatricule(matricule);
    if (vehicule != null) {
        return ResponseEntity.ok(vehicule); // Retourne le véhicule trouvé
    } else {
        return ResponseEntity.notFound().build(); // Aucun véhicule trouvé
    }
  }
}

