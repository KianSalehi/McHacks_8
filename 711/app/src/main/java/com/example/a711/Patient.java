package com.example.a711;

public class Patient {

    public String id;
    public String name;
    public String email;
    public Doctor doctor;


    public Patient(String id, String name, String email){
        this.setId(id);
        this.setName(name);
        this.setEmail(email);
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


}
