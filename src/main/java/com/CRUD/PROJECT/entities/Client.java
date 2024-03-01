package com.CRUD.PROJECT.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "clients")
public class Client {
  @Id
  @JsonProperty("_id")
  private String _id;
  private String raisonSociale;
  private String matriculeSociale;
  private String siegeSociale;
  private String email;
  private String telephone;
  private String secteurActivite;
  private String nomComplet;
  private Boolean affected = false;
 




public Client(String _id, String raisonSociale, String matriculeSociale, String siegeSociale, String email,
		String telephone, String secteurActivite, String nomComplet, Boolean affected) {
	super();
	this._id = _id;
	this.raisonSociale = raisonSociale;
	this.matriculeSociale = matriculeSociale;
	this.siegeSociale = siegeSociale;
	this.email = email;
	this.telephone = telephone;
	this.secteurActivite = secteurActivite;
	this.nomComplet = nomComplet;
	this.affected = affected;
	
}

public Client() {
super();
}



public Boolean getAffected() {
	return affected;
}
public void setAffected(Boolean affected) {
	this.affected = affected;
}
@Override
public String toString() {
	return "Client [_id=" + _id + ", raisonSociale=" + raisonSociale + ", matriculeSociale=" + matriculeSociale
			+ ", siegeSociale=" + siegeSociale + ", email=" + email + ", telephone=" + telephone + ", secteurActivite="
			+ secteurActivite + ", nomComplet=" + nomComplet +  "]";
}
public String get_id() {
	return _id;
}
public void set_id(String _id) {
	this._id = _id;
}
public String getRaisonSociale() {
	return raisonSociale;
}
public void setRaisonSociale(String raisonSociale) {
	this.raisonSociale = raisonSociale;
}
public String getMatriculeSociale() {
	return matriculeSociale;
}
public void setMatriculeSociale(String matriculeSociale) {
	this.matriculeSociale = matriculeSociale;
}
public String getSiegeSociale() {
	return siegeSociale;
}
public void setSiegeSociale(String siegeSociale) {
	this.siegeSociale = siegeSociale;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getTelephone() {
	return telephone;
}
public void setTelephone(String telephone) {
	this.telephone = telephone;
}
public String getSecteurActivite() {
	return secteurActivite;
}
public void setSecteurActivite(String secteurActivite) {
	this.secteurActivite = secteurActivite;
}
public String getNomComplet() {
	return nomComplet;
}
public void setNomComplet(String nomComplet) {
	this.nomComplet = nomComplet;
}


public Client(String id) {
	this._id = id;
}








  
}
