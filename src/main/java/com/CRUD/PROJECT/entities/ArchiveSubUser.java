package com.CRUD.PROJECT.entities;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
@Document(collection="archiveSubUser")
public class ArchiveSubUser {
     
     	@Id
	@JsonProperty("id")
    private String id;
    private String firstname;
    private String lastname; 
    private String email;
    private String password;
    private String address; 
    private String role;
    private String passwordClaire;
    
 
	@DBRef
    private Client clients;


    public String getid() {
        return id;
    }


    public String getFirstname() {
        return firstname;
    }


    public String getLastname() {
        return lastname;
    }


    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }


    public String getAddress() {
        return address;
    }


    public String getRole() {
        return role;
    }


    public String getPasswordClaire() {
        return passwordClaire;
    }


    public Client getClients() {
        return clients;
    }


    public void setid(String id) {
        this.id = id;
    }


    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public void setRole(String role) {
        this.role = role;
    }


    public void setPasswordClaire(String passwordClaire) {
        this.passwordClaire = passwordClaire;
    }


    public void setClients(Client clients) {
        this.clients = clients;
    }


    public ArchiveSubUser(String firstname, String lastname, String email, String password, String address, String role,
            String passwordClaire, Client clients) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
        this.passwordClaire = passwordClaire;
        this.clients = clients;
    }


    public ArchiveSubUser() {
        //TODO Auto-generated constructor stub
    }


    @Override
    public String toString() {
        return "ArchiveSubUser [firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", password="
                + password + ", address=" + address + ", role=" + role + ", passwordClaire=" + passwordClaire
                + ", clients=" + clients + "]";
    }
  
}
