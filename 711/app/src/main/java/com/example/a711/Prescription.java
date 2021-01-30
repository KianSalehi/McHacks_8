package com.example.a711;

import java.util.Date;

public class Prescription {
    private String drug;
    private Date beginDate;
    private Date endDate;
    private Doctor doctor;

    public Prescription(String drug, Date beginDate, Date endDate, Doctor doctor){
        this.setDrug(drug);
        this.setBeginDate(beginDate);
        this.setEndDate(endDate);
        this.setDoctor(doctor);
    }
    public String getDrug(){
        return this.drug;
    }
    public Date getBeginDate(){
        return this.beginDate;
    }
    public Date getEndDate(){
        return this.endDate;
    }
    public Doctor getDoctor(){
        return this.doctor;
    }
    public void setDrug(String drug){
        this.drug=drug;
    }
    public void setBeginDate(Date beginDate){
        this.beginDate=beginDate;
    }
    public void setEndDate(Date endDate){
        this.endDate=endDate;
    }
    public void setDoctor(Doctor doctor){
        this.doctor=doctor;
    }
}
