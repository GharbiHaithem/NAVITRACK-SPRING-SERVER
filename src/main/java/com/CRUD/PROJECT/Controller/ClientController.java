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

import com.CRUD.PROJECT.Service.ClientService;
import com.CRUD.PROJECT.entities.Client;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/client")
public class ClientController {
	@Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

@Autowired
  public ClientService clientService;
  @Operation(hidden = true) 
  @PostMapping("/save")
  public ResponseEntity<Response> saveClient(@RequestBody Client client ){
	return clientService.savedClient(client);
	  
  } 
  @Operation(hidden = true) 
  @GetMapping("/clients")
  public List<Client> getClient(){
	  return clientService.getAllClients();
  } 
  
  @GetMapping("/{id}")
  public Optional<Client> getAClient(@PathVariable(name="id") String clientId) {
	  return clientService.getOneClient(clientId);
  }
  @Operation(hidden = true) 
  @GetMapping("/search")
  public List<Client> rechercher(@RequestParam("query") String termeRecherche) {
      return clientService.rechercher(termeRecherche);
  }
  @Operation(hidden = true) 
  @PutMapping("/updateClient/{id}")
  public ResponseEntity<Response> updateClient(@PathVariable(name="id") String _id , @RequestBody Client dataClient){
	  return clientService.updateClient(_id,  dataClient);
  }
  @Operation(hidden = true) 
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Response> deleteClient(@PathVariable(name="id") String _id){
	  return clientService.deleteArchiveClient(_id);
  }

  

}
