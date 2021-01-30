package com.example.a711;
import java.util.ArrayList;
import java.util.HashMap;

public class Patient {

    // Patient informtaions
    private String id;
    private String name;
    private int age;
    private String email;
    private Doctor doctor;
    // Current Patient Condition
    private ArrayList<Prescription> prescriptions = new ArrayList();
    private ArrayList<String> condition = new ArrayList();
    private ArrayList<String> symptoms = new ArrayList();
    // Patient Notes to be viewed by the Doctor
    private HashMap<String,String> patientNotes= new HashMap();

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

    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public Doctor getDoctor(){
        return this.doctor;
    }
    public void setDoctor(Doctor doctor){
        this.doctor = doctor;
    }

    public ArrayList<Prescription> getPrescriptions(){
        return prescriptions;
    }
    public void setPrescriptions(ArrayList<Prescription> prescriptions){
        this.prescriptions = prescriptions;
    }
    public void addPrescription (Prescription prescription){
        this.prescriptions.add(prescription);
    }
    public void removePrescription (Prescription prescription){
        this.prescriptions.remove(prescription);
    }

    public ArrayList<String> getCondition(){
        return condition;
    }
    public void setCondition(ArrayList<String> condition){
        this.condition = condition;
    }
    public void addCondition(String condition) {
        this.condition.add(condition);
    }
    public void removeCondition(String condition){
        this.condition.remove(condition);
    }

    public ArrayList<String> getSymptoms(){
        return symptoms;
    }
    public void setSymptoms(ArrayList<String> symptoms){
        this.symptoms = symptoms;
    }
    public void addSymptoms(String symptom) {
        this.symptoms.add(symptom);
    }
    public void removeSymptoms(String symptom){
        this.symptoms.remove(symptom);
    }


}
