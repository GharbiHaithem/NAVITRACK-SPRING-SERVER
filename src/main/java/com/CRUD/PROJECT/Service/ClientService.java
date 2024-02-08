package com.CRUD.PROJECT.Service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.CRUD.PROJECT.Response;
import com.CRUD.PROJECT.Repo.ArchiveClientRepo;
import com.CRUD.PROJECT.Repo.ClientRepo;
import com.CRUD.PROJECT.entities.ArchiveClient;
import com.CRUD.PROJECT.entities.Client;
@Service
public class ClientService {
	@Autowired
	private ClientRepo clientRepo;
	private ArchiveClientRepo archiveClientRepo;
	@Autowired
	public ClientService(ClientRepo clientRepo , ArchiveClientRepo archiveClientRepo) {
	    this.clientRepo = clientRepo;
	    this.archiveClientRepo=archiveClientRepo;
	}
	public ResponseEntity<Response> savedClient(Client client) {
		 String clientId = client.get_id();
		   System.out.println(client); 
		   
		Client savedClient = clientRepo.save(client);
		 return ResponseEntity.ok(new Response("client créé avec succès",savedClient));
	}
	public List<Client> getAllClients() {
		
		return clientRepo.findAll();
	}
	public Optional<Client> getOneClient(String clientId) {
		// TODO Auto-generated method stub
		return clientRepo.findById(clientId);
	}
	public List<Client> rechercher(String termeRecherche) {
		
		 List<Client> result = clientRepo.findByMatriculeSocialeContaining(termeRecherche);
		  System.out.println("Résultat de la recherche : " + result);
		 return result;
	}
	
	public ResponseEntity<Response> updateClient(String id, Client dataClient) {
		 System.out.println("id de client "+id);
		 Client findClient = clientRepo.findById(id).orElse(null);
		 if(findClient != null) {

			 findClient.setEmail(dataClient.getEmail());
			 findClient.setMatriculeSociale(dataClient.getMatriculeSociale());
			 findClient.setNomComplet(dataClient.getNomComplet());
			 findClient.setRaisonSociale(dataClient.getRaisonSociale());
			 findClient.setSecteurActivite(dataClient.getSecteurActivite());
			 findClient.setSiegeSociale(dataClient.getSiegeSociale());
			 findClient.setTelephone(dataClient.getTelephone());
			Client savedClient = clientRepo.save(findClient);
			  return ResponseEntity.ok(new Response("companie updated", savedClient));
			 
		 }
		 else {
			 System.out.println("client not exist");
			  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("companie non trouvé", null, null, null, null));
		 }
		
	}
	public ResponseEntity<Response> deleteArchiveClient(String _id) {
		ArchiveClient archiveClient = new ArchiveClient();
		Optional<Client> findClient = clientRepo.findById(_id);
		if(findClient.isPresent()) {
			archiveClient.set_id(findClient.get().get_id());
			archiveClient.setEmail(findClient.get().getEmail());
			archiveClient.setMatriculeSociale(findClient.get().getMatriculeSociale());
			archiveClient.setNomComplet(findClient.get().getNomComplet());
			archiveClient.setRaisonSociale(findClient.get().getRaisonSociale());
			archiveClient.setSecteurActivite(findClient.get().getSecteurActivite());
			archiveClient.setSiegeSociale(findClient.get().getSiegeSociale());
			archiveClient.setTelephone(findClient.get().getTelephone());
			archiveClientRepo.save(archiveClient);
			clientRepo.deleteById(_id);
		  return ResponseEntity.ok(new Response("Companie supprimé", findClient.get(), null, null, null));
		}else {
			
			        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Companie non trouvé", null, null, null, null));
			  
		}
		
	}
  

	
	
}
