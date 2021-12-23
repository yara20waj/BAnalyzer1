package com.yarawajeeh.bmianalyzer;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import java.util.Timer;
import android.widget.Toast;
import java.util.TimerTask;
import android.widget.TextView;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {
    EditText name,email,password,repassword;
    Button create;
    TextView login;
    Timer timer;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        name = findViewById(R.id.Name);
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.TextPassword);
        repassword = findViewById(R.id.TextRePassword);
        login=findViewById(R.id.text_login);
        create = findViewById(R.id.button_signup);
        mAuth = FirebaseAuth.getInstance();

        create.setOnClickListener(view ->{
            createUser();

        });

        login.setOnClickListener(view -> {
            startActivity(new Intent(Registration.this,Loginpage.class));
        });
    }
    private void createUser(){
        String user_name =  name.getText().toString();
        String user_email =  email.getText().toString();
        String user_password =  password.getText().toString();
        String user_repassword =  repassword.getText().toString();
        if (TextUtils.isEmpty(user_email)){
            email.setError("Email cannot be empty");
            email.requestFocus();
        }else if(TextUtils.isEmpty(user_password)){
            password.setError("password cannot be empty");
            password.requestFocus();
        }else if(TextUtils.isEmpty(user_name)){
            name.setError("Username cannot be empty");
            name.requestFocus();
        }else if(TextUtils.isEmpty(user_repassword)){
            name.setError("Username cannot be empty");
            name.requestFocus();
        }

        else {
            mAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Registration.this,"Successful" ,Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Registration.this,Home.class));
                    }else {
                        Toast.makeText(Registration.this,"error" + task.getException().getMessage(),Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
    }
    }

        /* timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Registration.this,Complete_Information.class);
                startActivity(intent);
                finish();
            }
        },5000);*/

