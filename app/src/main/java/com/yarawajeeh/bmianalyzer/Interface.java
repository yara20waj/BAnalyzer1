package com.yarawajeeh.bmianalyzer;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Interface extends AppCompatActivity {
    Button nextBtn;
   Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface);

        nextBtn =findViewById(R.id.button_next);
        nextBtn.setOnClickListener(view -> {
            startActivity(new Intent(Interface.this,Loginpage.class));
           // finish();
        });
        //nextBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),Loginpage.class)));
        /*timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Interface.this,Loginpage.class);
                startActivity(intent);
                finish();
            }
        },5000);*/
    }
}