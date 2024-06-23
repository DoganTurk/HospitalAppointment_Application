package com.example.hospital_application;

public class Hospital {

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
