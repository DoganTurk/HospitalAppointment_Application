package com.example.hospital_application;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.hospital_application.databinding.LayoutCreateappointmentActivityBinding;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class CreateAppointmentActivity extends AppCompatActivity {

    LocalDate today = LocalDate.now();
    List<String> hospitalClassList=new ArrayList<>();
    List<String> doctorList = new ArrayList<>();
    List<Doctor> doctor = new ArrayList<>();
    List<String> timeIntervals;
    String p_id;

    {
        timeIntervals = new ArrayList<>();
        timeIntervals.add("10:00");
        timeIntervals.add("10:20");
        timeIntervals.add("10:40");
        timeIntervals.add("11:00");
        timeIntervals.add("11:20");
        timeIntervals.add("11:40");
        timeIntervals.add("13:00");
        timeIntervals.add("13:20");
        timeIntervals.add("13:40");
        timeIntervals.add("14:00");
        timeIntervals.add("14:20");
        timeIntervals.add("14:40");
        timeIntervals.add("15:00");
        timeIntervals.add("15:20");
        timeIntervals.add("15:40");
        timeIntervals.add("16:00");
        timeIntervals.add("16:20");
        timeIntervals.add("16:40");
    }

    String hospital_name;

    public static List<String> getWeekdaysInNext15Days(LocalDate startDate) {
        List<String> weekdays = new ArrayList<>();
        LocalDate currentDate = startDate;
        int daysChecked = 0;
        weekdays.add("---Choose the Date---");

        while (daysChecked < 15) {
            DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
            if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
                weekdays.add(currentDate.toString() + " - " + dayOfWeek.toString());
            }
            currentDate = currentDate.plusDays(1);
            daysChecked++;
        }

        return weekdays;
    }
    List<String> weekdays = getWeekdaysInNext15Days(today);

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            List<String> data = (List<String>) msg.obj;
            hospitalClassList.addAll(data);
            String output = "Hospital is selected";
            return true;
        }
    });

    Handler handler2 = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            List<Doctor> doctor_ = (List<Doctor>) msg.obj;

            doctorList.clear();
            doctor.clear();
            doctorList.add("---Select The Doctor---");
            doctor.addAll(doctor_);

            for (Doctor doc : doctor) {
                String name = doc.getName();
                if (name != null) {
                    Log.i("a", name);
                    String surname = doc.getSurname();
                    if (surname != null) {
                        name += " " + surname;
                        doctorList.add(name);
                    } else {
                        Log.i("a", "Surname is null for Doctor ID: " + doc.getDoctor_id());
                    }
                } else {
                    Log.i("a", "Name is null for Doctor ID: " + doc.getDoctor_id());
                }
            }
            return true;
        }
    });


    Handler handler3 = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {

            return true;
        }
    });

    LayoutCreateappointmentActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = LayoutCreateappointmentActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        p_id = ((WebApplication)getApplication()).patient_id;

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getSupportActionBar().setTitle("Create Appointment");

        Repo repo = new Repo();
        ExecutorService srv = ((WebApplication)getApplication()).srv;
        repo.getHospital(srv, handler);
        hospitalClassList.add("---Select The Hospital---");
        ArrayAdapter<String> adp= new ArrayAdapter<>(this,R.layout.spinner_item, hospitalClassList);
        Spinner spClass = binding.hospitalspinner;

        spClass.setAdapter(adp);
        Spinner spClass2 = binding.Doctorspinner;
        Repo repo1 = new Repo();
        ExecutorService srv1 = ((WebApplication)getApplication()).srv;

        spClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                hospital_name = parent.getItemAtPosition(position).toString();
                repo1.getDoctorByHospitalId(srv1, handler2, hospital_name);
                doctorList.add("---Select The Doctor---");
                ArrayAdapter<String> adp2 = new ArrayAdapter<>(CreateAppointmentActivity.this, R.layout.spinner_item, doctorList);
                spClass2.setAdapter(adp2);
                adp2.notifyDataSetChanged(); // Notify adapter of dataset change

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adp3= new ArrayAdapter<>(this,R.layout.spinner_item, weekdays);
        Spinner spClass3 = binding.Datespinner;
        spClass3.setAdapter(adp3);

        ArrayAdapter<String> adp4= new ArrayAdapter<>(this,R.layout.spinner_item, timeIntervals);
        Spinner spClass4 = binding.timespinner;
        spClass4.setAdapter(adp4);

        Button btn = binding.CreateAppointment;
        btn.setOnClickListener(v->{

            String saveDoctorName = spClass2.getSelectedItem().toString();
            String saveDate = spClass3.getSelectedItem().toString().substring(0,10);
            String saveTime = spClass4.getSelectedItem().toString();
            saveDate += "T" + saveTime + ":00";
            Doctor tempd = new Doctor();

            for (Doctor doc: doctor){
                String temp = doc.getName() + " " + doc.getSurname();
                if (saveDoctorName.equals(temp) ){
                    tempd = doc;
                }
            }

            Repo repo3 = new Repo();
            ExecutorService srv3= ((WebApplication) getApplicationContext()).srv;
            repo.saveAppointment(srv3,handler3, p_id, tempd, saveDate);

            String output = "Saved appointment " + saveDoctorName + " to the given date";
            // Create Toast
            Toast toast = Toast.makeText(CreateAppointmentActivity.this, output, Toast.LENGTH_SHORT);

// Ensure the toast view is valid before manipulating it
            View toastView = toast.getView();
            if (toastView instanceof ViewGroup) {
                ViewGroup group = (ViewGroup) toastView;
                if (group.getChildCount() > 0) {
                    TextView message = (TextView) group.getChildAt(0);
                    message.setTextSize(25);
                    message.setGravity(Gravity.CENTER); // Move this line here to ensure it's part of the check
                }
            }

            toast.show();
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                // Navigate back to MainActivity2
                Intent intent = new Intent(CreateAppointmentActivity.this, MainActivity2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Finish the current activity
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}