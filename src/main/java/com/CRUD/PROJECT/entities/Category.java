package com.CRUD.PROJECT.entities;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="category")
public class Category {
  @Id
  private String _id;
  private int qtyStock = 0; 
  public String get_id() {
	return _id;
}


public void set_id(String _id) {
	this._id = _id;
}



public Category( int qtyStock, String name) {
	
	this.qtyStock = qtyStock;
	this.name = name;
}



public Category() {
	super();
	// TODO Auto-generated constructor stub
}



private String name;
@Override
public String toString() {
	return "Category [ name=" + name + "]";
}


public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Category( String name) {
	super();

	this.name = name;
}





public Category(String _id, String name) {
	super();
	this._id = _id;
	this.name = name;
}


public int getQtyStock() {
	return qtyStock;
}


public void setQtyStock(int qtyStock) {
	this.qtyStock = qtyStock;
}






}
