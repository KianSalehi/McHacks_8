package com.example.a711;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Prescription {
    private String drug;
    private String beginDate;
    private String endDate;
    private String doctor;

    public Prescription(String drug, String beginDate, String endDate, String doctor){
        this.setDrug(drug);
        this.setBeginDate(beginDate);
        this.setEndDate(endDate);
        this.setDoctor(doctor);
    }
    public String getDrug(){
        return this.drug;
    }
    public String getBeginDate(){
        return this.beginDate;
    }
    public String getEndDate(){
        return this.endDate;
    }
    public String getDoctor(){
        return this.doctor;
    }
    public void setDrug(String drug){
        this.drug=drug;
    }
    public void setBeginDate(String beginDate){
        this.beginDate=beginDate;
    }
    public void setEndDate(String endDate){
        this.endDate=endDate;
    }
    public void setDoctor(String doctor){
        this.doctor=doctor;
    }
}
