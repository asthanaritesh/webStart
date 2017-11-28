package com.webstart.dao;

import java.io.Serializable;


public class UserBean implements Serializable{
	private int id;
	private String name;
	private String email;
	private String role;
	private Integer age;
	private String country;
	private String password;
	
	public UserBean() {}
	
	public UserBean(String name, String email, String role, int age, String country, int id){
		this.id=id;
		this.name=name;
		this.email=email;
		this.role=role;
		this.age=age;
		this.country=country;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCountry() {
		return country;
	}	
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPassword() {
		return password;
	}	
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString(){
		return "Name="+this.name+", Email="+this.email+", Country="+this.country;
	}
}