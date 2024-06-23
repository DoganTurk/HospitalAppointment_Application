package com.example.hospital_application;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class appointmentviewmodel extends ViewModel {

    MutableLiveData<List<Entry>> entryData = new MutableLiveData<>();

    public appointmentviewmodel(){

        List<Entry> appointments = new ArrayList<>();

    }
    public MutableLiveData<List<Entry>> getAppointmentData() {
        return entryData;
    }

    public void setAppointmentData(List<Entry> a){
        entryData.setValue(a);
    }
}