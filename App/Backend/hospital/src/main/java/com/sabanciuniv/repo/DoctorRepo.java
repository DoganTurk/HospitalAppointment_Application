package com.sabanciuniv.repo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sabanciuniv.model.Doctor;


@Repository
public interface DoctorRepo extends MongoRepository<Doctor, String>{

    List<Doctor> findByProfession(String profession);
    List<Doctor> findAll();
    List<Doctor> findByHospital(String hospital);
 	
}
