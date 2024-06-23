package com.sabanciuniv.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sabanciuniv.model.Appointment;
import com.sabanciuniv.model.Patient;

@Repository
public interface PatientRepo extends MongoRepository<Patient, String>{

	List<Patient> patients = new ArrayList<>();
	Patient save(Patient patient);
    List<Patient> findAll();
	
}
