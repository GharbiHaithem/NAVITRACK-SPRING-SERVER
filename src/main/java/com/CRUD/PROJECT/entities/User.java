package com.CRUD.PROJECT.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    private String _id;
    private String firstname;
    private String lastname; 
    private String email;
    private String password;
    private String address; 
    private String role;
    private String passwordClaire;
    
    private String client; 
 

    @DBRef 
    private List<User> subUsers ;
    public String getFirstname() {
        return firstname;
    }

    public List<User> getSubUsers() {
		return subUsers;
	}
    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User(String _id, String firstname, String lastname, String email, String password, String address,
			String role, String client, List<User> subUsers) {
		super();
		this._id = _id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.address = address;
		this.role = role;
		this.client = client;
		this.subUsers = subUsers;
	}

	public void setSubUsers(List<User> subUsers) {
		this.subUsers = subUsers;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	

	public User(String _id, String firstname, String lastname, String email, String password, String address,
			String client, List<User> subUsers , String role) {
		super();
		this._id = _id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.address = address;
		this.client=client;
		this.subUsers = subUsers;
		this.role=role;
	}

	

	public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() { 
        return lastname;
    }

    public void setLastName(String lastname) { 
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() { 
        return address;
    }

    public void setAddress(String address) { 
        this.address = address;
    }

    public User(String firstname,String role, String lastname, String email, String password, String address) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
    }

	public User() {
		// TODO Auto-generated constructor stub
	}



    public String getPasswordClaire() {
        return passwordClaire;
    }

    public void setPasswordClaire(String passwordClaire) {
        this.passwordClaire = passwordClaire;
    }

    public String findUserIdInSubUsers(String userId) {
        if (subUsers != null) {
            for (User subUser : subUsers) {
                if (userId.equals(subUser.get_id())) {
                    return subUser.get_id(); // Retourne l'ID du sous-utilisateur correspondant
                }
            }
        }
        return null; // Retourne null si l'ID n'est pas trouvé dans la liste des sous-utilisateurs
    }

    public String getClient() {
        return client;
    }

    public User(String _id, String firstname, String lastname, String email, String password, String address,
            String role, String passwordClaire, String client, List<User> subUsers) {
        this._id = _id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
        this.passwordClaire = passwordClaire;
        this.client = client;
        this.subUsers = subUsers;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
