package com.CRUD.PROJECT.entities;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "facture")
public class Facture {
@Id
private String _id;
private String numeroFacture;
private String dateDebut;
private String dateFin;
private String pathUrl;
private Boolean statusSend=false;
@DBRef
private List<Appareil> appareilId;
@DBRef
private Client clientId;
private String etat = "impayer";
private Float priceUnitaire = (float) 20.00;
private Double total ;
public String get_id() {
	return _id;
}

public Facture(String _id, String numeroFacture, String dateDebut, String dateFin, List<Appareil> appareilId,
		Client clientId, String etat, Float priceUnitaire , Double total) {
	super();
	this._id = _id;
	this.numeroFacture = numeroFacture;
	this.dateDebut = dateDebut;
	this.dateFin = dateFin;
	this.appareilId = appareilId;
	this.clientId = clientId;
	this.etat = etat;
	this.priceUnitaire = priceUnitaire;
    this.total = total;
}

public String getPathUrl() {
	return pathUrl;
}

public Boolean getStatusSend() {
	return statusSend;
}

public Double getTotal() {
	return total;
}

public void setPathUrl(String pathUrl) {
	this.pathUrl = pathUrl;
}

public void setStatusSend(Boolean statusSend) {
	this.statusSend = statusSend;
}

public void setTotal(Double total) {
	this.total = total;
}

public void setTotal(double d) {
	this.total = d;
}

public Float getPriceUnitaire() {
	return priceUnitaire;
}

public void setPriceUnitaire(Float priceUnitaire) {
	this.priceUnitaire = priceUnitaire;
}

public List<Appareil> getAppareilId() {
	return appareilId;
}

public void setAppareilId(List<Appareil> maListe) {
	this.appareilId = maListe;
}

public void set_id(String _id) {
	this._id = _id;
}
public String getDateDebut() {
	return dateDebut;
}

public Facture(String numeroFacture, String dateDebut, String dateFin, String pathUrl, Boolean statusSend,
		List<Appareil> appareilId, Client clientId, String etat, Float priceUnitaire, Double total) {
	this.numeroFacture = numeroFacture;
	this.dateDebut = dateDebut;
	this.dateFin = dateFin;
	this.pathUrl = pathUrl;
	this.statusSend = statusSend;
	this.appareilId = appareilId;
	this.clientId = clientId;
	this.etat = etat;
	this.priceUnitaire = priceUnitaire;
	this.total = total;
}

public void setDateDebut(String dateDebut) {
	this.dateDebut = dateDebut;
}
public String getDateFin() {
	return dateFin;
}
public void setDateFin(String dateFin) {
	this.dateFin = dateFin;
}





public Facture(String _id,List<Appareil> appareilId, String numeroFacture, String dateDebut, String dateFin, Client clientId, String etat) {
	super();
	this._id = _id;
	this.numeroFacture = numeroFacture;
	this.dateDebut = dateDebut;
	this.dateFin = dateFin;
	this.clientId = clientId;
	this.etat = etat;
	this.appareilId = appareilId;
}
public String getNumeroFacture() {
	return numeroFacture;
}
public void setNumeroFacture(String numeroFacture) {
	this.numeroFacture = numeroFacture;
}

public String getEtat() {
	return etat;
}
public void setEtat(String etat) {
	this.etat = etat;
}








public Client getClientId() {
	return clientId;
}
public void setClientId(Client clientId2) {
	this.clientId = clientId2;
}
public Facture() {
	// TODO Auto-generated constructor stub
}
public void setClient(Client clientId2) {
	// TODO Auto-generated method stub
	this.clientId = clientId2;
}







}
