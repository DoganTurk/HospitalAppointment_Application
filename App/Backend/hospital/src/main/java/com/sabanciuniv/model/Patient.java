package com.sabanciuniv.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Document
public class Patient{

	@Id
	private String p_id;
	private String name, surname, gender, password, contact;
	
	public Patient(){
		
	}

	public Patient(String name, String surname, String gender, String password, String contact) {
		super();
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.password = password;
		this.contact = contact;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getP_id() {
		return p_id;
	}

	public void setP_id(String p_id) {
		this.p_id = p_id;
	}

	@Override
	public String toString() {
		return "Patient [p_id=" + p_id + ", name=" + name + ", surname=" + surname + ", gender=" + gender
				+ ", password=" + password + ", contact=" + contact + "]";
	}

}