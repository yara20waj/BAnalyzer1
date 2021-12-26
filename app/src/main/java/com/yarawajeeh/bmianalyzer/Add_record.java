package com.yarawajeeh.bmianalyzer;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.DatePicker;
import java.util.Calendar;
import java.util.Timer;

import android.widget.ImageView;
import android.widget.TimePicker;

import static android.text.format.DateFormat.is24HourFormat;

public class Add_record extends AppCompatActivity implements DatePickerDialog.OnDateSetListener , TimePickerDialog.OnTimeSetListener {

    Timer timer;
    EditText width,length,date,time;
    Button width_minus,length_minus,width_Plus,length_Plus,savedata;
    String dateTime;
    ImageView imageButton;

    int dayOfMonth, month, year, hour, minute;
    int myday, myMonth, myYear, myHour, myMinute;
    int num=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        width=findViewById(R.id.width);
        length=findViewById(R.id.length);
        date=findViewById(R.id.dateview);
        time=findViewById(R.id.time);
        width_minus=findViewById(R.id.w_minus);
        length_minus=findViewById(R.id.l_minus);
        width_Plus=findViewById(R.id.w_Plus);
        length_Plus=findViewById(R.id.l_Plus);
        Calendar calendar = Calendar.getInstance();
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR);
        imageButton=findViewById(R.id.imageButton);
        minute = c.get(Calendar.MINUTE);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        savedata=findViewById(R.id.savedata);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(com.yarawajeeh.bmianalyzer.Add_record.this, Add_record.this, year, month, dayOfMonth);
                datePickerDialog.show();


            }

        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add_record.this, MainHome.class);
                // Sending Email to Dashboard Activity using intent.
//            intent.putExtra(userEmail"",email);
                startActivity(intent);


            }

        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(Add_record.this, Add_record.this, hour, minute,DateFormat.is24HourFormat(Add_record.this));
                timePickerDialog.show();

            }

        });



        savedata.setOnClickListener(view -> {

            startActivity(new Intent(Add_record.this,MainHome.class));
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
        /*timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Add_record.this,Addfooddetails.class);
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

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        myHour = hourOfDay;
        myMinute = minute;
        String etime = myHour+":"+myMinute;
        time.setText(etime );
    }
}