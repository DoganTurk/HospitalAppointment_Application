package com.example.hospital_application;


import java.time.LocalDateTime;

public class Appointment {


    private String a_id;


    private Patient patient;

    private Doctor doctor;


    private LocalDateTime datetime;


    public Appointment(String aId, String patient, String doctor, String datetime){

    }


    public Appointment(Patient patient, Doctor doctor, LocalDateTime datetime) {
        super();

        this.patient = patient;
        this.doctor = doctor;
        this.datetime = datetime;
    }


    public String getA_id() {
        return a_id;
    }


    public void setA_id(String a_id) {
        this.a_id = a_id;
    }


    public Patient getPatient() {
        return patient;
    }


    public void setPatient(Patient patient) {
        this.patient = patient;
    }


    public Doctor getDoctor() {
        return doctor;
    }


    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }


    public LocalDateTime getDatetime() {
        return datetime;
    }


    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }


    @Override
    public String toString() {
        return "Appointment [a_id=" + a_id + ", patient=" + patient + ", doctor=" + doctor 	+ ", datetime=" + datetime + "]";
    }

}

