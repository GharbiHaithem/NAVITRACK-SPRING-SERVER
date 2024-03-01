package com.CRUD.PROJECT.entities;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Document(collection= "vehicule")
public class Vehicule {

	@Id
	private String _id;
	private String matricule;
	private String marque;
	private String model;
	private String type;
	private int puisanceFiscale;
	private Date anneeMiseEnCirculation;
	private int qtyCarburantLitre;
	@DBRef
	private Appareil appareil;
	@DBRef
	private Client client;
	   @Temporal(TemporalType.TIMESTAMP)
	   private Date saveDate;
	public String get_id() {
		return _id;
	}
	
	public Date getSaveDate() {
		return saveDate;
	}

	public void setSaveDate(Date saveDate) {
		this.saveDate = saveDate;
	}


	public Vehicule(String _id, String matricule, String marque, String model, String type, int puisanceFiscale,
			Date anneeMiseEnCirculation, int qtyCarburantLitre, Appareil appareil,Client client) {
		super();
		this._id = _id;
		this.matricule = matricule;
		this.marque = marque;
		this.model = model;
		this.type = type;
		this.puisanceFiscale = puisanceFiscale;
		this.anneeMiseEnCirculation = anneeMiseEnCirculation;
		this.qtyCarburantLitre = qtyCarburantLitre;
		this.appareil = appareil;
		this.client = client;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Appareil getAppareil() {
		return appareil;
	}

	public void setAppareil(Appareil appareil) {
		this.appareil = appareil;
	}

	public void set_id(String _id) {
		this._id = _id;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPuisanceFiscale() {
		return puisanceFiscale;
	}
	public void setPuisanceFiscale(int puisanceFiscale) {
		this.puisanceFiscale = puisanceFiscale;
	}
	public Date getAnneeMiseEnCirculation() {
		return anneeMiseEnCirculation;
	}
	public void setAnneeMiseEnCirculation(Date anneeMiseEnCirculation) {
		this.anneeMiseEnCirculation = anneeMiseEnCirculation;
	}
	public int getQtyCarburantLitre() {
		return qtyCarburantLitre;
	}
	public void setQtyCarburantLitre(int qtyCarburantLitre) {
		this.qtyCarburantLitre = qtyCarburantLitre;
	}
	
	public Vehicule() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Vehicule [_id=" + _id + ", matricule=" + matricule + ", marque=" + marque + ", model=" + model
				+ ", type=" + type + ", puisanceFiscale=" + puisanceFiscale + ", anneeMiseEnCirculation="
				+ anneeMiseEnCirculation + ", qtyCarburantLitre=" + qtyCarburantLitre + ", appareil=" + appareil + "]";
	}

	public Vehicule get(int i) {
		// TODO Auto-generated method stub
		return null;
	}




	
}
