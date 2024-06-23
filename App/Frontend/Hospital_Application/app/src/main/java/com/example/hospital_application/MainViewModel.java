package com.example.hospital_application;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    MutableLiveData<List<String>> Data = new MutableLiveData<>();

    public MainViewModel(){

        List<String> appointments = new ArrayList<>();
        appointments.add("a");
        Data.setValue(appointments);

    }
    public MutableLiveData<List<String>> getData() {
        return Data;
    }

    public void setData(List<String> a){
        Data.setValue(a);
    }
}