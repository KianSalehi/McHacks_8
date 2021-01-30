package com.example.a711;


public class Appointment {

    // Appointment informations
    private Patient patient;
    private Doctor doctor;
    private String date;
    private String time;
    private String notes;
    // Constructor
    public Appointment(Patient patient, Doctor doctor, String date, String time, String notes) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
        this.notes = notes;
    }

    // Getter Setter
    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
}

