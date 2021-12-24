package com.yarawajeeh.bmianalyzer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Complete_Information extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener {

    Timer timer;
    EditText width,length,date,time;
    Button width_minus,length_minus,width_Plus,length_Plus,savedata;
    int dayOfMonth, month, year, hour, minute;
    int myday, myMonth, myYear, myHour, myMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete__information);
        width=findViewById(R.id.width);
        length=findViewById(R.id.length);
        date=findViewById(R.id.dateview);
        width_minus=findViewById(R.id.w_minus);
        length_minus=findViewById(R.id.l_minus);
        width_Plus=findViewById(R.id.w_Plus);
        length_Plus=findViewById(R.id.l_Plus);
        Calendar calendar = Calendar.getInstance();
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        savedata=findViewById(R.id.savedata);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(com.yarawajeeh.bmianalyzer.Complete_Information.this,Complete_Information.this, year, month, dayOfMonth);
                datePickerDialog.show();


            }

        });
        savedata.setOnClickListener(view -> {

            startActivity(new Intent(Complete_Information.this,MainHome.class));
        });
        width_Plus.setOnClickListener(view ->{
            increaseInteger();
        });
        length_Plus.setOnClickListener(view ->{
            increaseIntegerlength();
        });
        width_minus.setOnClickListener(view ->{
            decreaseInteger();
        });
        length_minus.setOnClickListener(view ->{
            decreaseIntegerlength();
        });










       /* timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Complete_Information .this, Home.class);
                startActivity(intent);
                finish();
            }
        },5000);*/
    }








    private void decreaseIntegerlength() {
        int x= Integer.parseInt(length.getText().toString().trim()) -1;
        length.setText(""+x);
    }

    private void increaseInteger() {
        int x= Integer.parseInt(width.getText().toString().trim()) +1;
        width.setText(""+x);

    }

    private void increaseIntegerlength() {
        int x= Integer.parseInt(length.getText().toString().trim()) +1;
        length.setText(""+x);

    }

    private void decreaseInteger() {
        int x= Integer.parseInt(width.getText().toString().trim()) -1;
        width.setText(""+x);

    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        myYear = year;
        myday = dayOfMonth;
        myMonth = month;


        String edate = myYear+"/"+myMonth+"/"+myday;
        date.setText( edate );
    }
}