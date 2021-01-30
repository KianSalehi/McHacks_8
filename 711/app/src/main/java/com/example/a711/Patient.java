package com.example.a711;
import java.util.HashMap;

public class Patient {

    // Patient informtaions
    public String id;
    public String name;
    public int age;
    public String email;
    public Doctor doctor;
    // Current Patient Condition
    public Prescription[] prescriptions = new Prescription[];
    public ArrayList[] condition = new ArrayList;
    public ArrayList[] symptoms = new ArrayList[];
    // Patient Notes to be viewed by the Doctor
    public HashMap<String,String> patientNotes= new HashMap<String, String>;

    // Constructor
    public Patient(String id, String name, int age, String email){
        this.setId(id);
        this.setName(name);
        this.setEmail(email);
        this.setAge(age);
    };

    // Getter Setter
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

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Doctor getDoctor(){
        return this.doctor;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Prescription[] getPrescriptions(){
        return prescriptions;
    }
    public void setPrescriptions(Prescription[] prescriptions){
        this.prescriptions = prescriptions;
    }

    public ArrayList[] getCondition(){
        return condition;
    }
    public void setCondition(ArrayList[] condition){
        this.condition = condition;
    }

    public ArrayList[] getSymptoms(){
        return symptoms;
    }
    public void setSymptoms(ArrayList[] symptoms){
        this.symptoms = symptoms;
    }



}
