package com.yarawajeeh.bmianalyzer.OOP;

import java.util.ArrayList;

public class User {
    private final ArrayList<BMIRecord> records;

    public User(){
        this.records= new ArrayList<>();
        records.add(new BMIRecord("22/3/2001", "Normal",165,160));
        records.add(new BMIRecord("22/3/2001", "Normal",165,160));
        records.add(new BMIRecord("22/3/2001", "Normal",165,160));
        records.add(new BMIRecord("22/3/2001", "Normal",165,160));

    }
    public ArrayList<BMIRecord> getRecords(){
        return records;
    }
}
