package com.example.hospital_application;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hospital_application.databinding.ActivityMainBinding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.concurrent.ExecutorService;


public class MainActivity extends AppCompatActivity {

    List<Patient> data;
    EntryAdapter adp;
    String pass, patient_id;
    String output = "";
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            List <Patient> patient = (List<Patient>) msg.obj;

            // Use equals() method for string comparison
            if (patient.isEmpty()) {

                output = "Id does not exist, Please enter again";
            }
            else {

                String P = patient.get(0).getP_id();
                String password = patient.get(0).getPassword();
                if(password.equals(pass)){

                    ((WebApplication)getApplication()).patient_id = P;
                    Intent i = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(i);
                    output = "You entered successfully";

                }
                else {
                    output = "Password is wrong, please enter again";
                }


            }
            Toast toast = Toast.makeText(MainActivity.this, output, Toast.LENGTH_SHORT);

            // Ensure the toast view is valid before manipulating it
            View toastView = toast.getView();
            if (toastView instanceof ViewGroup) {
                ViewGroup group = (ViewGroup) toastView;
                if (group.getChildCount() > 0) {
                    TextView message = (TextView) group.getChildAt(0);
                    message.setTextSize(25);
                    message.setGravity(Gravity.CENTER);
                    // Move this line here to ensure it's part of the check
                }
            }

            toast.show();
            return true;
        }
    });

    ActivityMainBinding binding;
    EditText p_id, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Button button_sign_in = binding.buttonSignIn;
        p_id = binding.editTextId;
        password = binding.editTextPassword;
        button_sign_in.setOnClickListener(v ->{

             patient_id = p_id.getText().toString();
             pass = password.getText().toString();
            Repo repo = new Repo();
            ExecutorService srv = ((WebApplication)getApplication()).srv;
            repo.getPatientById(srv, handler, patient_id,pass);

        });

    }
}
