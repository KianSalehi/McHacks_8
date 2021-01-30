package com.example.a711;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Prescription {
    private String drug;
    private LocalDate beginDate;
    private LocalDate endDate;
    private Doctor doctor;

    public Prescription(String drug, LocalDate beginDate, LocalDate endDate, Doctor doctor){
        this.setDrug(drug);
        this.setBeginDate(beginDate);
        this.setEndDate(endDate);
        this.setDoctor(doctor);
    }
    public String getDrug(){
        return this.drug;
    }
    public LocalDate getBeginDate(){
        return this.beginDate;
    }
    public LocalDate getEndDate(){
        return this.endDate;
    }
    public Doctor getDoctor(){
        return this.doctor;
    }
    public void setDrug(String drug){
        this.drug=drug;
    }
    public void setBeginDate(LocalDate beginDate){
        this.beginDate=beginDate;
    }
    public void setEndDate(LocalDate endDate){
        this.endDate=endDate;
    }
    public void setDoctor(Doctor doctor){
        this.doctor=doctor;
    }
}
