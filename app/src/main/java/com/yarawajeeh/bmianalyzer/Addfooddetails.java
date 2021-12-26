package com.yarawajeeh.bmianalyzer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class Addfooddetails extends AppCompatActivity {
    EditText f_name,f_calory;
    Button uploadphoto,f_save;
    private String Date,Time;
    String name,category,calory,i;
    private static final int myfile=1;
    private Uri myImag;
    private String dateandtime,downloadImageUrl;
    private StorageReference imgref;
    private DatabaseReference Ref;
    private ProgressDialog loade;
    private FirebaseDatabase database;
    Timer timer;
    ImageView arrow;

    FirebaseAuth mAuth;
    ImageView f_img;
    String[] type={"Meat and poultry","Fish and seafood","Vegetables","Fruits"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfooddetails);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        Ref = FirebaseDatabase.getInstance().getReference("BMI");
        loade = new ProgressDialog(this);

        // Spinner spin =  findViewById(R.id.spinner_category);

        f_name = findViewById(R.id.name);
        f_calory = findViewById(R.id.calory);
        f_save = findViewById(R.id.save);
        arrow.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), MainHome.class)));

        Spinner spin = findViewById(R.id.dropdown_category);


        spin.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) Addfooddetails.this);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = spin.getItemAtPosition(position).toString();
      /*  timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Addfooddetails.this,Food_List.class);
                startActivity(intent);
                finish();
            }
        },5000);*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter aaadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Collections.singletonList(category));
        aaadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aaadapter);

        f_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateProductData();
            }
        });
    }
    private void ValidateProductData() {
        name =  f_name.getText().toString();
        calory = f_calory.getText().toString();

          if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "name:", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(category)) {
            Toast.makeText(this, "category food:", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(calory)) {
            Toast.makeText(this, "food:", Toast.LENGTH_SHORT).show();
        } else {
            StoreProductInformation();
        }
    }

    private void StoreProductInformation() {
        loade.setMessage("waitting.");
        loade.setCanceledOnTouchOutside(false);
        loade.show();

        Calendar c = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        Date = currentDate.format(c.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        Time = currentTime.format(c.getTime());

        dateandtime = Date + " " + Time;
    }

    private void SaveProductInfoToDatabase() {
        HashMap<String, Object> ChaletMap = new HashMap<>();
        ChaletMap.put("name", name);
        ChaletMap.put("calory", calory+" cal /g");
         ChaletMap.put("category", category);
        ChaletMap.put("date", Date);
        ChaletMap.put("time", Time);
        ChaletMap.put("key",i);



        i = FirebaseDatabase.getInstance().getReference("Users").push().getKey();
        Ref=database.getReference("BMI");
        Ref.child("Food").child(i).updateChildren(ChaletMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            loade.dismiss();
                            Toast.makeText(Addfooddetails.this, "successfull", Toast.LENGTH_SHORT).show();

                        } else {
                            loade.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(Addfooddetails.this, "Error " + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}