package com.sabanciuniv.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sabanciuniv.model.Doctor;
import com.sabanciuniv.model.Hospital;

@Repository
public interface HospitalRepo extends MongoRepository<Hospital, String>{

	Hospital save(Hospital hospital);
    List<Hospital> findAll();
}

