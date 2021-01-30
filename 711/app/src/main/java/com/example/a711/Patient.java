package com.example.a711;
import java.util.Hashmap;

public class Patient {

    // Patient informtaions
    public String id;
    public String name;
    public int age;
    public String email;
    public Doctor doctor;
    // Current Patient Condition
    public Prescription[] prescriptions = new Prescription[];
    public String[] condition = new String[];
    public String[] symptoms = new String[];
    // Patient Notes to be viewed by the Doctor
    public Hashmap<String,String> patientNotes= new Hashmap<String, String>;

    // Constructor
    public Patient(String id, String name, String email){
        this.setId(id);
        this.setName(name);
        this.setEmail(email);
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

    public String[] getCondition(){
        return condition;
    }
    public void setCondition(String[] condition){
        this.condition = condition;
    }

    public String[] getSymptoms(){
        return symptoms;
    }
    public void setSymptoms(String[] symptoms){
        this.symptoms = symptoms;
    }


}
