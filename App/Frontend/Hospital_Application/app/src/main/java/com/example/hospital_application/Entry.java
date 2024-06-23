package com.example.hospital_application;

public class Entry {

    String doctor_name, hospital_name, date;

    public void Entry(){}

    public Entry(String doctor_name, String hospital_name, String date) {
        this.doctor_name = doctor_name;
        this.hospital_name = hospital_name;
        this.date = date;

    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public String getDate() {
        return date;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "doctor_name='" + doctor_name + '\'' +
                ", hospital_name='" + hospital_name + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
