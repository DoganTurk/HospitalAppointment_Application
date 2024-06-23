package com.sabanciuniv.repo;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sabanciuniv.model.Appointment;
import com.sabanciuniv.model.Doctor;
import com.sabanciuniv.model.Patient;


public interface AppointmentRepo extends MongoRepository<Appointment, String> {

	
	Appointment save(Appointment appointment);
	List<Appointment> findByPatient(Patient patient);
    List<Appointment> findByDoctor(Doctor doctor);
    List<Appointment> findByDatetime(LocalDateTime datetime);
    List<Appointment> findAll();
    void delete(Appointment appointment);
	

}


