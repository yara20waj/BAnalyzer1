package com.yarawajeeh.bmianalyzer.OOP;

import java.util.ArrayList;

public class User {
    private final ArrayList<BMIRecord> records;

    public User(){
        this.records= new ArrayList<>();

    }
    public ArrayList<BMIRecord> getRecords(){
        return records;
    }
}
