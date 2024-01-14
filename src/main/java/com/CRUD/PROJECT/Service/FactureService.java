package com.CRUD.PROJECT.Service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.CRUD.PROJECT.Response;
import com.CRUD.PROJECT.Repo.FactureRepo;
import com.CRUD.PROJECT.entities.Appareil;
import com.CRUD.PROJECT.entities.Client;
import com.CRUD.PROJECT.entities.Facture;
import com.CRUD.PROJECT.entities.Vehicule;
@Service
public class FactureService {
	@Autowired
    private FactureRepo factureRepo;

   
	@Scheduled(cron = "0 0 15 1 * ?") 
    public Facture generateMonthlyInvoice(List<Vehicule> vehicules) {
		Period oneMonth = Period.ofMonths(1);
		  List<Appareil> maListe = new ArrayList<>();
		 Facture facture = new Facture();
	
	     Vehicule premierVehicule = vehicules.get(0);
	     
    	
    		
    		 Client clientId = (premierVehicule.getClient());
    		
    		 java.util.Date currentDate =  premierVehicule.getSaveDate();
    		 ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault());

    	
    		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    		String formattedDate = zonedDateTime.format(outputFormatter);
    		
    		
    		String dateDebut = (formattedDate);
    		LocalDate localDate = LocalDate.parse(formattedDate, outputFormatter);

    		
    		LocalDate dateWithOneMonthAdded = localDate.plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

    		
    		String newFormattedDate = dateWithOneMonthAdded.format(outputFormatter);
            String dateFin =(newFormattedDate);
            System.out.println(clientId);
            System.out.println(dateDebut);
            System.out.println(dateFin);
            Random random = new Random();
            int number = random.nextInt(90000000) + 10000000;
           String  numberString = String.format("%08d", number);
        
           
            facture.setClient(clientId);
            
            facture.setDateDebut(dateDebut);
            facture.setDateFin(dateFin);
            facture.setNumeroFacture(numberString);
            Float priceTotal = null;
            for(int i =0 ;i< vehicules.size() ; i++) {
            	 Vehicule vehicule = vehicules.get(i);
            	  maListe.add(vehicule.getAppareil());
                 facture.setAppareilId( maListe); 
                  priceTotal = facture.getPriceUnitaire() * vehicules.size();  
                 
            }
           facture.setTotal(priceTotal);
         
        List<Facture>  findFacture = factureRepo.findByClientId(clientId);
        System.out.println(findFacture);
        if (findFacture.isEmpty()) {
        	  System.out.println("empty facture");
        	  
            return facture = factureRepo.save(facture); 
           
         }else {
        	  findFacture.sort(Comparator.comparing(Facture::getDateFin, Collections.reverseOrder()));

        	 
        	    Facture latestFacture = findFacture.get(0);

        	    System.out.println("Latest Facture DateFin: " + latestFacture.getDateFin());
        	 System.out.println(latestFacture.getDateFin());
                 LocalDateTime currentDateTime = LocalDateTime.now();

                
                 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                
                 String formattedDateTime = currentDateTime.format(formatter);
                 System.out.println(formattedDateTime);
                 int comparaison = latestFacture.getDateFin().compareTo(formattedDateTime);
                 System.out.println(comparaison);
                 System.out.println(dateFin);
                 
                 if (comparaison < 0) {
                	    System.out.println("I'm here");
                	   

                	    System.out.println("Latest Facture DateFin: " + latestFacture.getDateFin());
                	    
                	    Facture facture2 = new Facture();

                	    
                	    facture2.setDateDebut( latestFacture.getDateFin());
                	    facture2.setClient(clientId);
                	    facture2.setTotal(priceTotal);
                	  
                	
                	        LocalDate _dateFin = LocalDate.parse( latestFacture.getDateFin(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                	        LocalDate newDateFin = _dateFin.plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());;

                	        facture2.setNumeroFacture(numberString);
                	        facture2.setDateFin(newDateFin.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                	        
                	    System.out.println("date fin" + newDateFin);
                	 
                	   

                	    facture2.setNumeroFacture(numberString);
                	  
                	    
                	    for(int j =0 ;j< vehicules.size() ; j++) {
                       	 Vehicule vehicule = vehicules.get(j);
                       	  maListe.add(vehicule.getAppareil());
                            facture2.setAppareilId( maListe); 
                             priceTotal = facture2.getPriceUnitaire() * vehicules.size();  
                            
                       }
                      facture2.setTotal(priceTotal);
                	  
                	  return  facture = factureRepo.save(facture2);
                	}else {
                		System.out.println("null heare");
                		facture = null;}
                 
                 return facture;
	 
         }
      
        
      
        
        
    	

         
    }


	 private boolean estMemeMois(String dateStr1, String date) {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	        try {
	            Date date1 = dateFormat.parse(dateStr1);
	            Date date2 = dateFormat.parse(date);

	            LocalDate localDate1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	            LocalDate localDate2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

	            return  localDate1.getMonth() == localDate2.getMonth();
	        } catch (ParseException e) {
	         
	            e.printStackTrace();
	            return false;
	        }
	    }

	 @Scheduled(cron = "0 0 0 15 * ?")
	 public void genererNouvelleFacture() {
	     List<Facture> factureExistantes = factureRepo.findAll();
	     Date date = new Date();

	     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	     String dateString = dateFormat.format(date);

	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	    
	     Map<Client, List<Facture>> facturesParClient = factureExistantes.stream()
	             .collect(Collectors.groupingBy(Facture::getClientId));

	   
	     facturesParClient.forEach((clientId, facturesDuClient) -> {
	       
	         java.util.Optional<Facture> derniereFactureOpt = facturesDuClient.stream()
	                 .max(Comparator.comparing(facture -> LocalDate.parse(facture.getDateFin(), formatter)));

	         derniereFactureOpt.ifPresent(derniereFacture -> {
	             LocalDate lastFactureDate = LocalDate.parse(derniereFacture.getDateFin(), formatter);

	             if (lastFactureDate.isBefore(LocalDate.now())) {
	                 System.out.println("La date de fin de la dernière facture du client " + clientId +
	                         " est antérieure à la date actuelle.");

	                 LocalDate nouvelleDate = lastFactureDate.plusMonths(1);
	                 System.out.println("Nouvelle date : " + nouvelleDate);

	                 Facture nouvelleFacture = new Facture();
	                 nouvelleFacture.setAppareilId(derniereFacture.getAppareilId());
	                 nouvelleFacture.setDateFin(nouvelleDate.format(formatter));
	                 nouvelleFacture.setDateDebut(derniereFacture.getDateFin());
	                 nouvelleFacture.setClientId(clientId);
	                 nouvelleFacture.setPriceUnitaire(derniereFacture.getPriceUnitaire());
	                 nouvelleFacture.setEtat(derniereFacture.getEtat());

	                 factureRepo.save(nouvelleFacture);
	             } else {
	                 System.out.println("La date de fin de la dernière facture du client " + clientId +
	                         " est postérieure ou égale à la date actuelle.");
	             }
	         });
	     });
	 }








	public List<Facture> getFact(String clientId) {
		System.out.println(clientId);
		List<Facture> findFactures =   factureRepo.findByClientId(clientId); 
		return   findFactures;
	}









	public List<Facture> getAllFactures() {
		
		return factureRepo.findAll();
	}









	public void createFacture(Vehicule vehicule) {
		
		System.out.println(vehicule);
	}


	public ResponseEntity<Response> updateEtatFact(String factureId) {
		 Facture FactureExistant = factureRepo.findById(factureId).orElse(null);
      
        if(FactureExistant !=null) {
        	
        	FactureExistant.setEtat("payer");
         factureRepo.save(FactureExistant);
        	 return ResponseEntity.ok(new Response("facture modifier"));
        }
        else  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("facture non trouvé", null, null, null, null));
	}


	
}
