package com.example.a711;

import java.util.ArrayList;
import java.util.Date;

public class Doctor {

    private String id;
    private String name;
    private ArrayList<Patient> patientList;

    private Doctor(String id, String name){
        this.setId(id);
        this.setName(name);
        this.patientList = new ArrayList<>();
    };

    public String getId(){
        return id;
    }
    public void setId(String x){
        this.id = x;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Patient> getPatientList() {
        return patientList;
    }

    public void addPatient(Patient patient){
        patientList.add(patient);
    }
    public void removePatient(Patient patient){
        patientList.remove(patient);
    }
    public void renewPrescription(Patient patient, Prescription prescription, Date date){

    }

}
