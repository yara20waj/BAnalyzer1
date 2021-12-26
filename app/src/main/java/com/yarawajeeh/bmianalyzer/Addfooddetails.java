package com.yarawajeeh.bmianalyzer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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
    FirebaseAuth mAuth;
    ImageView f_img;
    String[] type={"Meat and poultry","Fish and seafood","Vegetables","Fruits"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfooddetails);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

       // Spinner spin =  findViewById(R.id.spinner_category);

        f_name=findViewById(R.id.name);
        f_calory=findViewById(R.id.calory);
        f_save= findViewById(R.id.save);


        f_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateProductData();
            }
        });
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

    private void ValidateProductData() {
        name =  f_name.getText().toString();
        calory = f_calory.getText().toString();



          if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please write name...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(category)) {
            Toast.makeText(this, "Please write category food...", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(calory)) {
            Toast.makeText(this, "Please write calory food...", Toast.LENGTH_SHORT).show();
        } else {
            StoreProductInformation();
        }
    }

    private void StoreProductInformation() {

        loade.setMessage("Dear user, please wait while we are adding the new Food.");
        loade.setCanceledOnTouchOutside(false);
        loade.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        Date = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        Time = currentTime.format(calendar.getTime());

        dateandtime = Date + " " + Time;


        final StorageReference filePath = imgref.child(myImag.getLastPathSegment() + dateandtime + ".jpg");

        final UploadTask uploadTask = filePath.putFile(myImag);


        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message = e.toString();
                Toast.makeText(Addfooddetails.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                loade.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(Addfooddetails.this, "Food Image uploaded Successfully...", Toast.LENGTH_SHORT).show();

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            throw task.getException();
                        }

                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            downloadImageUrl = task.getResult().toString();

                            Toast.makeText(Addfooddetails.this, "got the Product image Url Successfully...", Toast.LENGTH_SHORT).show();

                            SaveProductInfoToDatabase();
                        }
                    }

                     
                });
            }
        });
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