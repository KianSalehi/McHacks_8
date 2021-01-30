package com.example.a711;

public class Doctor {

    public String id;
    public String name;
    public Patient[] patients = new Patient[];


    private Doctor(String id, String name){
        this.setId(id);
        this.setName(name);
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

    public Patient[] getPatients() {
        return patients;
    }
    public void setPatients(Patient[] patients) {
        this.patients = patients;
    }
}
