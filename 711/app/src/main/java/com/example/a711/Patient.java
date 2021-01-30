package com.example.a711;


public class Patient {

    // Patient informtaions
    private String id;
    private String name;
    private int age;
    private String email;
    private Doctor doctor;

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

}
