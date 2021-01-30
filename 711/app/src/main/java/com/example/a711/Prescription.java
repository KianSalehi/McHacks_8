package com.example.a711;

import java.time.LocalTime;
import java.util.Date;

public class Prescription {
    private String drug;
    private LocalTime beginDate;
    private LocalTime endDate;
    private Doctor doctor;

    public Prescription(String drug, LocalTime beginDate, LocalTime endDate, Doctor doctor){
        this.setDrug(drug);
        this.setBeginDate(beginDate);
        this.setEndDate(endDate);
        this.setDoctor(doctor);
    }
    public String getDrug(){
        return this.drug;
    }
    public LocalTime getBeginDate(){
        return this.beginDate;
    }
    public LocalTime getEndDate(){
        return this.endDate;
    }
    public Doctor getDoctor(){
        return this.doctor;
    }
    public void setDrug(String drug){
        this.drug=drug;
    }
    public void setBeginDate(LocalTime beginDate){
        this.beginDate=beginDate;
    }
    public void setEndDate(LocalTime endDate){
        this.endDate=endDate;
    }
    public void setDoctor(Doctor doctor){
        this.doctor=doctor;
    }
}
