package com.example.hospital_application;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class Repo {
    String localIP = "10.51.16.176";

    public void getPatientById(ExecutorService srv, Handler uiHandler, String patient_id, String pass) {
        srv.execute(() -> {
            try {
                URL url = new URL("http://" + localIP + ":8080/HospitalApplication/patients/infobypatientID/" + patient_id);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedInputStream reader = new BufferedInputStream(conn.getInputStream());
                StringBuilder buffer = new StringBuilder();
                int chr = 0;
                while ((chr = reader.read()) != -1) {
                    buffer.append((char) chr);
                }

                String response = buffer.toString();
                List<Patient> patient= new ArrayList<>();
                if (response != null && !response.equals("null")){

                    JSONObject obj = new JSONObject(buffer.toString());
                    Patient p = new Patient(obj.getString("p_id"),
                            obj.getString("name"), obj.getString("surname"),
                            obj.getString("gender"), obj.getString("password"),
                            obj.getString("contact"));
                    patient.add(p);
                }

                Message msg = new Message();
                msg.obj = patient;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void getHospital(ExecutorService srv, Handler uiHandler) {
        srv.execute(() -> {
            try {
                URL url = new URL("http://" + localIP + ":8080/HospitalApplication/hospitals");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedInputStream reader = new BufferedInputStream(conn.getInputStream());
                StringBuilder buffer = new StringBuilder();
                int chr = 0;
                while ((chr = reader.read()) != -1) {
                    buffer.append((char) chr);
                }

                JSONArray arr = new JSONArray(buffer.toString());
                List<String> data = new ArrayList<>();

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject currentObj = arr.getJSONObject(i);
                    String name = currentObj.getString("h_id");
                    data.add(name);
                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }


    public void getDoctorByHospitalId(ExecutorService srv, Handler uiHandler, String h_id) {
        srv.execute(() -> {
            try {
                URL url = new URL("http://" + localIP + ":8080/HospitalApplication/hospitals/doctor_list/" + h_id);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                BufferedInputStream reader = new BufferedInputStream(conn.getInputStream());
                StringBuilder buffer = new StringBuilder();
                int chr = 0;
                while ((chr = reader.read()) != -1) {
                    buffer.append((char) chr);
                }

                JSONArray arr = new JSONArray(buffer.toString());
                List<Doctor> data = new ArrayList<>();

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject currentObj = arr.getJSONObject(i);
                    Doctor doctor = new Doctor(currentObj.getString("doctor_id"),
                            currentObj.getString("h_id"), currentObj.getString("name"),
                            currentObj.getString("surname"), currentObj.getString("gender"),
                            currentObj.getString("profession"), currentObj.getString("title"), currentObj.getString("hospital"));
                    data.add(doctor);
                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        });
    }

    public void getAppointmentbyPatient(ExecutorService srv, Handler uiHandler, String p_id) {
        srv.execute(() -> {
            try {
                URL url = new URL("http://" + localIP + ":8080/HospitalApplication/appointments/patient_appointments/" + p_id);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedInputStream reader = new BufferedInputStream(conn.getInputStream());
                StringBuilder buffer = new StringBuilder();
                int chr = 0;
                while ((chr = reader.read()) != -1) {
                    buffer.append((char) chr);
                }

                JSONArray arr = new JSONArray(buffer.toString());
                List<Entry> data = new ArrayList<>();

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject currentObj = arr.getJSONObject(i);

                    if (currentObj.has("doctor")) {
                        JSONObject doctorObj = currentObj.getJSONObject("doctor");

                        String doctorName = "";
                        if (doctorObj.has("name")) {
                            doctorName += doctorObj.getString("name");
                        }
                        if (doctorObj.has("surname")) {
                            doctorName += " " + doctorObj.getString("surname");
                        }

                        String hospital = doctorObj.has("hospital") ? doctorObj.getString("hospital") : "";
                        String datetime = currentObj.has("datetime") ? currentObj.getString("datetime") : "";

                        data.add(new Entry(doctorName.trim(), hospital, datetime));
                    } else {
                        // Handle the case where "Doctor" key is missing.
                        // For example, you might want to log it or add a default value.
                        System.err.println("No value for 'Doctor' in the JSON object at index " + i);
                    }
                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        });
    }

    public void deleteAppointmentbya_id(ExecutorService srv, Handler uiHandler, String a_id) {
        srv.execute(() -> {
            try {
                URL url = new URL("http://" + localIP + ":8080/HospitalApplication/appointments/delete/{" + a_id + "}" );
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                BufferedInputStream reader = new BufferedInputStream(conn.getInputStream());
                StringBuilder buffer = new StringBuilder();
                int chr = 0;
                while ((chr = reader.read()) != -1) {
                    buffer.append((char) chr);
                }

                JSONArray arr = new JSONArray(buffer.toString());
                List<Appointment> data = new ArrayList<>();
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject currentObj = arr.getJSONObject(i);
                    data.add(new Appointment(currentObj.getString("a_id"),
                            currentObj.getString("patient"), currentObj.getString("doctor"),
                            currentObj.getString("datetime")));
                }

                Message msg = new Message();
                msg.obj = data;
                uiHandler.sendMessage(msg);

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        });
    }

    public void saveAppointment(ExecutorService srv, Handler uiHandler, String patient, Doctor doctor, String datetime){
        srv.execute(()->{

            try {

                URL url = new URL("http://" + localIP + ":8080/HospitalApplication/appointments/save");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/JSON");

                JSONObject objtoSend = new JSONObject();
                objtoSend.put("a_id", patient+doctor.getName()+doctor.getSurname()+datetime); // Assuming a_id is hardcoded or generated elsewhere


                JSONObject patientObj = new JSONObject();
                patientObj.put("p_id", patient);
                patientObj.put("name", "");
                patientObj.put("surname", "");
                patientObj.put("gender", "");
                patientObj.put("password", "");
                patientObj.put("contact", "");
                objtoSend.put("patient", patientObj);

                JSONObject doctorObj = new JSONObject();
                doctorObj.put("doctor_id", doctor.getDoctor_id());
                doctorObj.put("h_id", doctor.getH_id());
                doctorObj.put("name", doctor.getName());
                doctorObj.put("surname", doctor.getSurname());
                doctorObj.put("gender", doctor.getGender());
                doctorObj.put("profession", doctor.getProfession());
                doctorObj.put("title", doctor.getTitle());
                doctorObj.put("hospital", doctor.getHospital());

                objtoSend.put("doctor", doctorObj);
                objtoSend.put("datetime", datetime);

                BufferedOutputStream writer = new BufferedOutputStream(conn.getOutputStream());

                writer.write(objtoSend.toString().getBytes(StandardCharsets.UTF_8));
                writer.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder buffer = new StringBuilder();
                String line = "";

                while((line=reader.readLine())!=null)
                {
                    buffer.append(line);
                }

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        });

    }

}