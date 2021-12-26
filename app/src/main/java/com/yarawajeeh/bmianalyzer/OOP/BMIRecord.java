package com.yarawajeeh.bmianalyzer.OOP;

public class BMIRecord {
    String date;
    String status;
    String id;
    int w;
    int l;
    public BMIRecord(String date, String status, int w, int l) {
        this.date= date;
        this.status= status;
        this.w= w;
        this.l= l;
    }
    public BMIRecord(){}
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
}
