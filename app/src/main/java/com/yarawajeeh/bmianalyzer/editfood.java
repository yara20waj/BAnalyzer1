package com.yarawajeeh.bmianalyzer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class editfood extends AppCompatActivity {
    Button save ;
    ImageView arrow;
    EditText f_name,f_calory,f_category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editfood);
        save = findViewById(R.id.save);
        arrow = findViewById(R.id.arrow);
        f_name = findViewById(R.id.name);
        f_calory= findViewById(R.id.calory);


        save.setOnClickListener(v -> {
            Intent intent= new Intent(editfood.this,Food_List.class);
            startActivity(intent);
        });

    }
}