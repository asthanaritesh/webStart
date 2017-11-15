package com.webstart.util;

import java.io.Serializable;

public class UserBean implements Serializable{	
	private int id;
	private String name;
	private String email;
	private String country;
	
	public UserBean() {}
	
	public UserBean(String name, String email, String country, int id){
		this.id=id;
		this.name=name;
		this.email=email;
		this.country=country;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public int getId() {
		return id;
	}
	public String getCountry() {
		return country;
	}	
	@Override
	public String toString(){
		return "Name="+this.name+", Email="+this.email+", Country="+this.country;
	}
}