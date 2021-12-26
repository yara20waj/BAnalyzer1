package com.yarawajeeh.bmianalyzer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class Food_List extends AppCompatActivity {


    Timer timer;
    ImageView imageButton;
    Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food__list);
        imageButton=findViewById(R.id.imageButton);
        edit=findViewById(R.id.edit);
      /*  timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Food_List.this, editfood.class);
                startActivity(intent);
                finish();
            }
        },5000);*/
        edit.setOnClickListener(v -> {
            Intent intent= new Intent(Food_List.this,MainHome.class);
            startActivity(intent);
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Food_List.this, MainHome.class);
                // Sending Email to Dashboard Activity using intent.
//            intent.putExtra(userEmail"",email);
                startActivity(intent);


            }

        });
    }
}