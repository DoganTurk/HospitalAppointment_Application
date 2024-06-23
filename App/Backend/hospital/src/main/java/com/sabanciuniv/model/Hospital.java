package com.sabanciuniv.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Hospital {

	@Id
	private String h_id;
	
	public Hospital(){
		
	}
	
	public Hospital(String x){
		this.h_id=x;
		
	}

	public String getH_id() {
		return h_id;
	}

	public void setH_id(String h_id) {
		this.h_id = h_id;
	}


	@Override
	public String toString() {
		return "Hospital [h_id=" + h_id + "]";
	}

	
}
