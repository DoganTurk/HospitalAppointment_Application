package com.example.hospital_application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.hospital_application.databinding.LayoutMainActivity2Binding;

public class MainActivity2 extends AppCompatActivity {
    Button buttonCreateAppointment, buttonMyAppointment;
    LayoutMainActivity2Binding binding;

    String p_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LayoutMainActivity2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        p_id = ((WebApplication)getApplication()).patient_id;

        buttonCreateAppointment = binding.buttonCreateAppointment;
        buttonMyAppointment = binding.buttonMyAppointment;

        buttonCreateAppointment.setOnClickListener(v ->{

            Intent i = new Intent(MainActivity2.this, CreateAppointmentActivity.class);
            startActivity(i);
        });

        buttonMyAppointment.setOnClickListener(v ->{

            Intent i = new Intent(MainActivity2.this, MyAppointmentsActivity.class);
            startActivity(i);

        });
    }
}
