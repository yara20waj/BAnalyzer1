package com.yarawajeeh.bmianalyzer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.yarawajeeh.bmianalyzer.OOP.Adapter;
import com.yarawajeeh.bmianalyzer.OOP.BMIAdapter;
import com.yarawajeeh.bmianalyzer.OOP.BMIRecord;


import java.util.ArrayList;
import java.util.Timer;

public class MainHome extends AppCompatActivity {
    Adapter recoAd;
    RecyclerView mainhome;
    Timer timer;
    TextView logout;
    Button addfood;
    Button addrecord;
    Button Viewfood;
    RecyclerView recyclerView ;

    FirebaseAuth mAuth;
    ArrayList<BMIRecord> records;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        addfood = findViewById(R.id.addfood);
        addrecord = findViewById(R.id.addrecord);
        Viewfood = findViewById(R.id.Viewfood);
        logout = findViewById(R.id.logout);
        recyclerView = findViewById(R.id.recyclerView);
        records = new ArrayList<>();
        records.add(new BMIRecord("22/3/2001", "Normal",165,160));
        records.add(new BMIRecord("22/3/2001", "Normal",165,160));
        records.add(new BMIRecord("22/3/2001", "Normal",165,160));
        records.add(new BMIRecord("22/3/2001", "Normal",165,160));

        recoAd = new Adapter(MainHome.this , records);
        recyclerView.setAdapter(recoAd);

        addfood.setOnClickListener(view -> {

            startActivity(new Intent(MainHome.this,Addfooddetails.class));
        });

        addrecord.setOnClickListener(view -> {

            startActivity(new Intent(MainHome.this,Add_record.class));
        });




        mAuth = FirebaseAuth.getInstance();
        logout.setOnClickListener( view -> {
            mAuth.signOut();
            startActivity(new Intent(MainHome.this,Loginpage.class));

        });




    }
}