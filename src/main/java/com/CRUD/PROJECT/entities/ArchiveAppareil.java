package com.CRUD.PROJECT.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "archiveAppareil")
public class ArchiveAppareil {
	
	 private String _id;
		private String modalTag;
	private String serialNumber;
	private  Double  priceUnite;
	private Number qtyStock;
	private Boolean accessoire = false; 
	@DBRef
	 private Category category ;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getModalTag() {
		return modalTag;
	}
	public void setModalTag(String modalTag) {
		this.modalTag = modalTag;
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
	public Boolean getAccessoire() {
		return accessoire;
	}
	public void setAccessoire(Boolean accessoire) {
		this.accessoire = accessoire;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public ArchiveAppareil(String _id, String modalTag, String serialNumber, Double priceUnite, Number qtyStock,
			Boolean accessoire, Category category) {
		super();
		this._id = _id;
		this.modalTag = modalTag;
		this.serialNumber = serialNumber;
		this.priceUnite = priceUnite;
		this.qtyStock = qtyStock;
		this.accessoire = accessoire;
		this.category = category;
	}
	public ArchiveAppareil() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	
}
