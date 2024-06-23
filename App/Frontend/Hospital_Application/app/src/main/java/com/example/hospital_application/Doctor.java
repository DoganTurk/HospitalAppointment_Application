package com.example.hospital_application;

public class Doctor {

    private String doctor_id;
    private String h_id, name, surname, gender, profession, title, hospital;

    public Doctor() {}

    public Doctor(String doctorId, String hId, String name, String surname, String gender, String profession, String title, String hospital) {
        this.doctor_id = doctorId;
        this.h_id = hId;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.profession = profession;
        this.title = title;
        this.hospital = hospital;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getH_id() {
        return h_id;
    }

    public void setH_id(String h_id) {
        this.h_id = h_id;
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

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    @Override
    public String toString() {
        return "Doctor [doctor_id=" + doctor_id + ", h_id=" + h_id + ", name=" + name + ", surname=" + surname
                + ", gender=" + gender + ", profession=" + profession + ", title=" + title + ", hospital=" + hospital + "]";
    }
}
