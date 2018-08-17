package com.medicine.main.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
public class User {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int uid;
	
	String username;
	String password;
/*	
	@OneToMany(cascade= {CascadeType.ALL})
	@JoinColumn(name="uid")
	Set<Medicine> m;
*/
	
	

	public User(User users) {
		super();
		this.uid = users.getUid();
		this.username = users.getUsername();
		this.password = users.getPassword();
		/*this.m = users.getM();*/
	}

	/*public Set<Medicine> getM() {
		return m;
	}

	public void setM(Set<Medicine> m) {
		this.m = m;
	}
*/
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	
	

}
