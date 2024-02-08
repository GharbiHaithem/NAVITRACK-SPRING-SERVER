package com.CRUD.PROJECT.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "appareil")
public class Appareil {
	@Id
 private String _id;
	private String modalTag;
private String serialNumber;
private  Double  priceUnite;
private Number qtyStock;
private Boolean accessoire = false; 
private Boolean affected = false;
@DBRef
private Category category;
 
public Appareil() {
	super();
	// TODO Auto-generated constructor stub
}
public Appareil(String _id, String serialNumber,  Category category, Double priceUnite, Number qtyStock , String modalTag) {
	super();
	this._id = _id;
	this.serialNumber = serialNumber;
	   this.category= category;
	this.priceUnite = priceUnite;
	this.qtyStock = qtyStock;
	this.modalTag = modalTag;
}

public Appareil(String _id, String modalTag, String serialNumber, Double priceUnite, Number qtyStock,
		Boolean accessoire, Boolean affected, Category category) {
	this._id = _id;
	this.modalTag = modalTag;
	this.serialNumber = serialNumber;
	this.priceUnite = priceUnite;
	this.qtyStock = qtyStock;
	this.accessoire = accessoire;
	this.affected = affected;
	this.category = category;
}
public Appareil(String _id, String modalTag, String serialNumber, Double priceUnite, Number qtyStock,
		Boolean accessoire, Category category) {
	super();
	this._id = _id;
	this.modalTag = modalTag;
	this.serialNumber = serialNumber;
	this.priceUnite = priceUnite;
	this.qtyStock = qtyStock;
	this.accessoire = accessoire;
	  this.category=category;
}
public Boolean getAccessoire() {
	return accessoire;
}
public void setAccessoire(Boolean accessoire) {
	this.accessoire = accessoire;
}
public String get_id() {
	return _id;
}
public String getModalTag() {
	return modalTag;
}
public void setModalTag(String modalTag) {
	this.modalTag = modalTag;
}
public void set_id(String _id) {
	this._id = _id;
}
public String getSerialNumber() {
	return serialNumber;
}
public void setSerialNumber(String serialNumber) {
	this.serialNumber = serialNumber;
}

public Double getPriceUnite() {
	return priceUnite;
}
public void setPriceUnite(Double priceUnite) {
	this.priceUnite = priceUnite;
}
public Number getQtyStock() {
	return qtyStock;
}
public void setQtyStock(Number qtyStock) {
	this.qtyStock = qtyStock;
}
public Category getCategory() {
	return category;
}
public void setCategory(Category category) {
	this.category = category;
}
@Override
public String toString() {
	return "Appareil [_id=" + _id + ", modalTag=" + modalTag + ", serialNumber=" + serialNumber + ", priceUnite="
			+ priceUnite + ", qtyStock=" + qtyStock + ", accessoire=" + accessoire + ", category=" + category + "]";
}
public Boolean getAffected() {
	return affected;
}
public void setAffected(Boolean affected) {
	this.affected = affected;
}

 
}
