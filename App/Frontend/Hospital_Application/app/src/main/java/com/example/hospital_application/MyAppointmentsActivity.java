package com.example.hospital_application;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.hospital_application.databinding.LayoutMyappointmentsActivityBinding;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class MyAppointmentsActivity extends AppCompatActivity {

    appointmentviewmodel viewModel;
    List<Entry> data;
    EntryAdapter adp;
    String p_id;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            List<Entry> data = (List<Entry>) msg.obj;

            adp = new EntryAdapter(MyAppointmentsActivity.this, data);

            String output = "";
            if(data.size() == 0) {
                output = "There are no entries for this course";}
            else{ output = "Entry Count: " + data.size();}

            binding.listViewAppointment.setAdapter(adp);

            return true;
        }
    });


    LayoutMyappointmentsActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = LayoutMyappointmentsActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        p_id = ((WebApplication)getApplication()).patient_id;

        viewModel = new ViewModelProvider(this).get(appointmentviewmodel.class);
        adp = new EntryAdapter(this, viewModel.getAppointmentData().getValue());

        binding.listViewAppointment.setLayoutManager(new LinearLayoutManager(this));
        getSupportActionBar().setTitle("My Appointments: ");
        Repo repo = new Repo();
        ExecutorService srv = ((WebApplication)getApplication()).srv;
        repo.getAppointmentbyPatient(srv, handler, p_id);
    }
}



