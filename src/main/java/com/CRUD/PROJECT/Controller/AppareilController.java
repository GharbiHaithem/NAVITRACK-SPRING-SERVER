package com.CRUD.PROJECT.Controller;


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
import com.CRUD.PROJECT.Repo.AppareilRepo;
import com.CRUD.PROJECT.Service.AppareilService;

import com.CRUD.PROJECT.entities.Appareil;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/appareil")
public class AppareilController {
	
	@Autowired
	    public AppareilController(AppareilService appareilService) {
	        this.appareilService = appareilService;
	    }
	
	 @Autowired
	    public AppareilService appareilService  ;
	 @PostMapping("/save")
	    public ResponseEntity<Response> saveAppareil(@RequestBody Appareil appareil) {
		 System.out.println(appareil);
	        return appareilService.save(appareil);
	    }
	  @GetMapping("/test")
	  public String justTest() {
		  return "This is Test";
	  }
	  @GetMapping("/withCategories")
	    public List<Appareil> getAllAppareilsWithCategories() {
	        return appareilService.getAll();
	    }
	  
	  @DeleteMapping("/delete/{id}")
	  public ResponseEntity<Response> deleteAppareil(@PathVariable(name="id") String appareilId ) {
		
		  return appareilService.deleteAppareil(appareilId);
		
		  
	  }
	  @GetMapping("/search")
	  public List<Appareil> searchAppareil(@RequestParam("query") String terme){
		return appareilService.searchAppareil(terme);
		  
	  }
	  
	  @GetMapping("/{id}")
	  public Optional<Appareil> appareil(@PathVariable(name="id") String appareilId){
		return appareilService.getappareil(appareilId);
		  
	  }
	  @PutMapping("/update/quantity/{id}")
	  public ResponseEntity<Response> updateAppareilQuantity(@PathVariable(name="id") String _id , @RequestBody Appareil qtyStock){
		  return appareilService.updateQuantity(_id,qtyStock);
	  }
	  @PutMapping("/edit/{id}")
	  public ResponseEntity<Response> updateAppareil(@PathVariable(name="id") String _id , @RequestBody Appareil dataAppareil){
		  return appareilService.updateAppareil(_id,dataAppareil);
	  }
}
