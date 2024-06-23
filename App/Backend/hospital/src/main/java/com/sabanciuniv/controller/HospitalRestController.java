package com.sabanciuniv.controller;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sabanciuniv.model.Appointment;
import com.sabanciuniv.model.Doctor;
import com.sabanciuniv.model.Hospital;
import com.sabanciuniv.model.Patient;
import com.sabanciuniv.repo.AppointmentRepo;
import com.sabanciuniv.repo.DoctorRepo;
import com.sabanciuniv.repo.HospitalRepo;
import com.sabanciuniv.repo.PatientRepo;
import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/HospitalApplication")
//@EnableMongoRepositories(basePackages = {"com.sabanciuniv.repo"})
public class HospitalRestController {

	
	@Autowired private DoctorRepo doctorrepo;
	@Autowired private PatientRepo patientrepo;
	@Autowired private HospitalRepo hospitalrepo;
	@Autowired private AppointmentRepo appointmentrepo;
	
	private static final Logger logger = LoggerFactory.getLogger(HospitalRestController.class);
	
	@PostConstruct
	public void init() {
		logger.info("\"Initializing");
		
		if(patientrepo.count()==0) {
				
				logger.info("\"Initializing Patients...\"");
				
				Patient patient1 = new Patient("John", "Doe", "Male", "password1", "123456789");
				Patient patient2 = new Patient("Jane", "Smith", "Female", "password2", "987654321");
				Patient patient3 = new Patient("Michael", "Johnson", "Male", "password3", "111222333");
				Patient patient4 = new Patient("Emily", "Williams", "Female", "password4", "444555666");
				Patient patient5 = new Patient("James", "Brown", "Male", "password5", "777888999");
				Patient patient6 = new Patient("Olivia", "Miller", "Female", "password6", "111222333");
				Patient patient7 = new Patient("Robert", "Taylor", "Male", "password7", "444555666");
				Patient patient8 = new Patient("Emma", "Clark", "Female", "password8", "777888999");
				Patient patient9 = new Patient("William", "Hill", "Male", "password9", "111222333");
				Patient patient10 = new Patient("Sophia", "Allen", "Female", "password10", "444555666");
				logger.info("\" Patients...\"");
				patientrepo.save(patient1);
				patientrepo.save(patient2);
				patientrepo.save(patient3);
				patientrepo.save(patient4);
				patientrepo.save(patient5);
				patientrepo.save(patient6);
				patientrepo.save(patient7);
				patientrepo.save(patient8);
				patientrepo.save(patient9);
				patientrepo.save(patient10);
				
				logger.info("All Patients are saved!");

		}
		
		if(doctorrepo.count()==0) {
			
			logger.info("\"Initializing Doctors...\"");
			
			Doctor doctor1 = new Doctor("hosp001", "John", "Smith", "Male", "Cardiologist", "Dr.", "ANKARA HOSPITAL");
			Doctor doctor2 = new Doctor("hosp001", "Emily", "Johnson", "Female", "Pediatrician", "Dr.", "ISTANBUL HOSPITAL");
			Doctor doctor3 = new Doctor("hosp002", "Michael", "Williams", "Male", "Neurologist", "Dr.", "ISTANBUL HOSPITAL");
			Doctor doctor4 = new Doctor("hosp002", "Sophia", "Brown", "Female", "Oncologist", "Dr.", "ANKARA HOSPITAL");
			Doctor doctor5 = new Doctor("hosp003", "James", "Jones", "Male", "Dermatologist", "Dr.", "IZMIR HOSPITAL");
			Doctor doctor6 = new Doctor("hosp003", "Olivia", "Davis", "Female", "Orthopedic Surgeon", "Dr.", "ISTANBUL HOSPITAL");
			Doctor doctor7 = new Doctor("hosp004", "Robert", "Miller", "Male", "Psychiatrist", "Dr.", "ANKARA HOSPITAL");
			Doctor doctor8 = new Doctor("hosp004", "Emma", "Wilson", "Female", "ENT Specialist", "Dr.", "ISTANBUL HOSPITAL");
			Doctor doctor9 = new Doctor("hosp005", "William", "Taylor", "Male", "Urologist", "Dr.", "IZMIR HOSPITAL");
			Doctor doctor10 = new Doctor("hosp005", "Ava", "Anderson", "Female", "Gynecologist", "Dr.", "IZMIR HOSPITAL");

			doctorrepo.save(doctor1);
			doctorrepo.save(doctor2);
			doctorrepo.save(doctor3);
			doctorrepo.save(doctor4);
			doctorrepo.save(doctor5);
			doctorrepo.save(doctor6);
			doctorrepo.save(doctor7);
			doctorrepo.save(doctor8);
			doctorrepo.save(doctor9);
			doctorrepo.save(doctor10);
			
			logger.info("All Doctors are saved!");
		}
		
	
		if(hospitalrepo.count()==0) {
		
			logger.info("\"Initializing Hospitals...\"");			
			for (int i = 0; i < 5;i++) 
			{
				for(Doctor x: doctorrepo.findAll()) {
					boolean check=false;
					for(Hospital y: hospitalrepo.findAll()) {
						if(y.getH_id().equals(x.getHospital())) check=true;
					}
					if(!check) {
						Hospital h =new Hospital(x.getHospital());	
						hospitalrepo.save(h);
					}
				}
			}
	
			logger.info("\"All Hospitals are saved");

		}
	
		if(appointmentrepo.count()==0) {
			
			logger.info("\"Initializing Appointments...\"");
	
			// Create Appointment instances
			Random random = new Random();
			List<LocalDateTime> fixedAppointmentTimes = new ArrayList<>();
	        fixedAppointmentTimes.add(LocalDateTime.of(2024, 4, 17, 9, 0));
	        fixedAppointmentTimes.add(LocalDateTime.of(2024, 4, 17, 9, 30));
	        fixedAppointmentTimes.add(LocalDateTime.of(2024, 4, 17, 10, 0));
	        fixedAppointmentTimes.add(LocalDateTime.of(2024, 4, 17, 10, 30));
	        fixedAppointmentTimes.add(LocalDateTime.of(2024, 4, 17, 11, 0));
	        fixedAppointmentTimes.add(LocalDateTime.of(2024, 4, 17, 11, 30));
	        fixedAppointmentTimes.add(LocalDateTime.of(2024, 4, 17, 12, 0));
	        fixedAppointmentTimes.add(LocalDateTime.of(2024, 4, 17, 12, 30));
	        fixedAppointmentTimes.add(LocalDateTime.of(2024, 4, 17, 14, 0));
	        fixedAppointmentTimes.add(LocalDateTime.of(2024, 4, 17, 14, 30));
	        fixedAppointmentTimes.add(LocalDateTime.of(2024, 4, 17, 15, 0));
	        fixedAppointmentTimes.add(LocalDateTime.of(2024, 4, 17, 15, 30));
	        fixedAppointmentTimes.add(LocalDateTime.of(2024, 4, 17, 16, 0));
	        
	        for (int i = 0; i < 10;i++) 
			{
		        
		        // Choose a random appointment time from the list
		        LocalDateTime randomDateTime = fixedAppointmentTimes.get(
		                ThreadLocalRandom.current().nextInt(fixedAppointmentTimes.size())
		        );			
	
				Patient patient = patientrepo.findAll().get(random.nextInt(patientrepo.findAll().size()));
				Doctor doctor = doctorrepo.findAll().get(random.nextInt(doctorrepo.findAll().size()));
				
				if(hasAppointmentConflict(randomDateTime, doctor) == true) {
					Appointment appointment= new Appointment(patient, doctor, randomDateTime);
					appointmentrepo.save(appointment);
				}
				
				else {}
				
			}
			
			logger.info("All appointments are saved!");
		}
	
}
	
	
	@GetMapping("/appointments")
	public List<Appointment> appointments(){
		
		return appointmentrepo.findAll();
	}
	
	
	@PostMapping("/appointments/save")
	public Appointment saveDoctor(@RequestBody Appointment appointment) {
		
		Appointment appointmentsaved = appointmentrepo.save(appointment);
		
		return appointmentsaved;
	}
	
	
	@GetMapping("/appointments/doctor_appointments/{doctor_ID}")
	public List<Appointment> getAppointmentBydoctorID(@PathVariable("doctor_ID") String doctor_ID) {
		
		List<Appointment> myList = new ArrayList<>();
		for(Doctor d: doctorrepo.findAll()) {
			if(d.getDoctor_id().equals(doctor_ID)) {
				myList = appointmentrepo.findByDoctor(d);
			}
		}
		return myList;
	}
	
	
	@GetMapping("/appointments/patient_appointments/{patient_ID}")
	public List<Appointment> getAppointmentBypatientID(@PathVariable("patient_ID") String patient_ID) {
		
		List<Appointment> myList = new ArrayList<>();
		for(Patient p: patientrepo.findAll()) {
			if(p.getP_id().equals(patient_ID)) {
				myList = appointmentrepo.findByPatient(p);
			}
		}
		return myList;
	}
	
	
	 @GetMapping("/appointments/doctor_appointments_at_date/{doctor_id}/{appointmentDate}")
	    public List<Appointment> getAppointmentByTimeAndDoctor(
	            @PathVariable("doctor_id") String doctor_id,
	            @PathVariable("appointmentDate") LocalDateTime appointmentDate) {
		 List<Appointment> myList = new ArrayList<>();
	        for(Appointment a: appointmentrepo.findAll()) {
	        	if(doctor_id.equals(a.getDoctor().getDoctor_id())) {
	        		LocalDate date1 = appointmentDate.toLocalDate();
	        		LocalDate date2 = a.getDatetime().toLocalDate();
	        		if(date1.equals(date2)) {
	        			myList.add(a);
	        		}
;	        	}
	        }
	       
	        return myList;
	    }  
	
	
	@PostMapping("/appointments/delete/{a_id}")
	public String deleteAppointment(@PathVariable("a_id") String a_id) {
        for(Appointment appointment : appointmentrepo.findAll()) {
    		if (appointment.getA_id().equals(a_id)) {
    			appointmentrepo.delete(appointment);
    			return "Appointment is deleted!";
    		}
        }
        return "Appointment is not deleted";
    }

		
	@PostMapping("/appointments/check")
    public boolean hasAppointmentConflict(@RequestBody LocalDateTime newAppointmenttime, Doctor doctor) {
     // Implement logic to check for conflicts with existing appointments
    	
    	List<Appointment> p_appointment = appointmentrepo.findByDatetime(newAppointmenttime);
   
    	for(Appointment appointment : p_appointment) {
    		if (appointment.getDoctor().equals(doctor)) {
    			return false; // it has a conflict
    		}
    	}
    	return true;
	}
	 
	
	
	@GetMapping("/patients")
	public List<Patient> patients(){
		
		return patientrepo.findAll();
	}

	
	@PostMapping("/patients/save")
	public Patient savePatient(@RequestBody Patient patient) {
		
		Patient patientsaved = patientrepo.save(patient);
		return patientsaved;
	}
	

	@GetMapping("/patients/infobypatientID/{p_id}")
	public Optional<Patient> seePatientById(@PathVariable("p_id") String p_id){
		return patientrepo.findById(p_id);
	}
	
	
	@GetMapping("/hospitals")
	public List<Hospital> hospitals(){
		
		return hospitalrepo.findAll();
	}
	
	
	@PostMapping("/hospitals/save")
	public Hospital saveDoctor(@RequestBody Hospital hospital) {
		Hospital h1 = hospitalrepo.save(hospital);
		return h1;
		
	}

	@GetMapping("/hospitals/doctor_list/{hospital_ID}")
    public List<Doctor> getdoctor_list(@PathVariable("hospital_ID") String hospital_ID){
		List<Doctor> myList = new ArrayList<>();
		for(Doctor x: doctorrepo.findAll()) {
			if(x.getHospital().equals(hospital_ID)) {
				myList.add(x);
			}
		}
		return myList;
	}
		
	
	@GetMapping("/doctors")
	public List<Doctor> doctors(){
		
		return doctorrepo.findAll();
	}
	
	
	@PostMapping("/doctors/save")
	public Doctor saveDoctor(@RequestBody Doctor doctor) {
		
		Doctor doctorsaved = doctorrepo.save(doctor);
		
			boolean check=false;
			for(Hospital y: hospitalrepo.findAll()) {
				if(y.getH_id().equals(doctor.getHospital())) check=true;
			}
			if(!check) {
				Hospital h =new Hospital(doctorsaved.getHospital());
				hospitalrepo.save(h);
			}
		
		return doctorsaved;
	}

	
}
