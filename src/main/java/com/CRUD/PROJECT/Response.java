package com.CRUD.PROJECT;

import java.util.List;

import com.CRUD.PROJECT.entities.Appareil;
import com.CRUD.PROJECT.entities.Client;
import com.CRUD.PROJECT.entities.Facture;
import com.CRUD.PROJECT.entities.User;

import com.CRUD.PROJECT.entities.Vehicule;

public class Response {
private String message;
private Vehicule vehicule;
private Appareil appareil;
private Client client;
private Facture facture;
private User userAccount;

public User getUserAccount() {
	return userAccount;
}

public Facture getFacture() {
	return facture;
}

public void setFacture(Facture facture) {
	this.facture = facture;
}

public void setUserAccount(User userAccount) {
	this.userAccount = userAccount;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public Vehicule getVehicule() {
	return vehicule;
}
public Appareil getAppareil() {
	return appareil;
}

public void setAppareil(Appareil appareil) {
	this.appareil = appareil;
}
public void setVehicule(Vehicule vehicule) {
	this.vehicule = vehicule;
}
public Response(String message,  Vehicule vehicule , Appareil appareil , Client client ,Facture facture , User userAccount) {
	super();
	this.message = message;
	this.vehicule = vehicule;
	this.client= client;
	this.appareil = appareil;
	this.facture = facture;
	this.userAccount = userAccount;
}



public Response(String message, Client client, User createsubuser , Facture facture , User userAccount  ) {
	// TODO Auto-generated constructor stub
	super();
	this.message = message;
    this.facture=facture;
	this.client= client;
	this.userAccount = userAccount;
	
}
public Response(String message, Client client) {
	// TODO Auto-generated constructor stub
	super();
	this.message = message;
	this.client = client;
	
}
public Response(String message, Facture facture) {
	// TODO Auto-generated constructor stub
	super();
	this.message = message;
	this.facture = facture;
	
}

public Response(String string2, User savedUser) {
	// TODO Auto-generated constructor stub
	this.message = string2;
	this.userAccount = savedUser;
}
public Response(String string2, List<User> savedUser) {
	// TODO Auto-generated constructor stub
}
public Response(String string2) {
	// TODO Auto-generated constructor stub
	this.message = string2;
}

public Response(String string, String string2) {
	// TODO Auto-generated constructor stub

}
public Client getClient() {
	return client;
}
public void setClient(Client client) {
	this.client = client;
}





}
