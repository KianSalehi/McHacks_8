package com.example.a711;
import java.util.ArrayList;
import java.util.HashMap;


public class MedicalRecord {

    private Patient patient;

    // Current Patient Condition
    private ArrayList<Prescription> prescriptions;
    private ArrayList<String> condition;
    private ArrayList<String> symptoms;

    // Patient Notes to be viewed by the Doctor
    private HashMap<String,String> patientNotes;
    // Doctor Notes to keep track of patient
    private HashMap<String,String> doctorNotes;

    // Constructor
    public MedicalRecord(Patient patient) {
        this.patient = patient;
        this.prescriptions = new ArrayList();
        this.condition = new ArrayList();
        this.symptoms = new ArrayList();
        this.patientNotes= new HashMap();
        this.doctorNotes= new HashMap();
    }

    // Getter Setter
    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
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

    public HashMap<String, String> getPatientNotes() {
        return patientNotes;
    }
    public void setPatientNotes(HashMap<String, String> patientNotes) {
        this.patientNotes = patientNotes;
    }

    public void addPatientNotes(String date, String note) {
        this.patientNotes.put(date,note);
    }
    public void removePatientNotes(String date){
        this.patientNotes.remove(date);
    }

    public HashMap<String, String> getDoctorNotes() {
        return doctorNotes;
    }
    public void setDoctorNotes(HashMap<String, String> doctorNotes) {
        this.doctorNotes = doctorNotes;
    }

    public void addDoctorNotes(String date, String note) {
        this.doctorNotes.put(date,note);
    }
    public void removeDoctorNotes(String date){
        this.doctorNotes.remove(date);
    }


}