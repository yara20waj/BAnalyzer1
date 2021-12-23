package com.yarawajeeh.bmianalyzer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Patterns;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Timer;
import java.util.TimerTask;

public class Loginpage extends AppCompatActivity {
    EditText email;
    EditText password;
    Button login;
    TextView singup;

    Timer timer;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        email=findViewById(R.id.UserName);
        password =findViewById(R.id.TextPassword);
        login =findViewById(R.id.button_login);
        singup =findViewById(R.id.text_signup);
        mAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(view ->{
            loginUser();
        });
        singup.setOnClickListener(view -> {

            startActivity(new Intent(Loginpage.this,Registration.class));
        });
    }
    private void checkIfEmailVerified(){
        FirebaseUser users=FirebaseAuth.getInstance().getCurrentUser();
        boolean emailVerified=users.isEmailVerified();
        if(!emailVerified){
            Toast.makeText(this,"Verify the Email Id",Toast.LENGTH_SHORT).show();
            mAuth.signOut();
            finish();
        }
        else {
            email.getText().clear();
            password.getText().clear();
            Intent intent = new Intent(Loginpage.this, MainHome.class);
            // Sending Email to Dashboard Activity using intent.
//            intent.putExtra(userEmail"",email);
            startActivity(intent);
        }
    }
    private void loginUser() {
        String user_email =  email.getText().toString().trim();
        String user_password =  password.getText().toString().trim();

        if (TextUtils.isEmpty(user_email )){
            email.setError("Email cannot be empty");
            email.requestFocus();
        }else if(TextUtils.isEmpty(user_password)){
            password.setError("password cannot be empty");
            password.requestFocus();
        }else {

            mAuth.signInWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        checkIfEmailVerified();
//                        startActivity(new Intent(Loginpage.this, Home.class));
                    }else{
                        Toast.makeText(Loginpage.this, "Log in Error: "+task.getException().getLocalizedMessage() + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    }

                }
            });

        }



    }





}

/*oop



public class Loginpage extends AppCompatActivity {
    EditText username;
    EditText password;
    Button login;
    TextView singup;
    boolean isUserValid, isPasswordValid;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        setupUI();
        setupListeners();



    }

    private void setupUI() {
        username =findViewById(R.id.UserName);
        password =findViewById(R.id.TextPassword);
        login =findViewById(R.id.button_login);
        singup =findViewById(R.id.text_signup);
    }

    private void setupListeners() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 checkUsername();
            }

        });
        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to RegisterActivity
                Intent intent = new Intent(getApplicationContext(), Registration.class);
                startActivity(intent);
            }
        });

    }

    private void checkUsername() {
        boolean isValid = true;
        if (isEmpty (username)){
            username.setError("You must enter username to login!");
            isValid=false;
        }else {
            if(!isEmail(username)){
                username.setError("Enter valid email");
            }
        }


        if (isEmpty (password)){
            password.setError("You must enter password to login!");

        }else {
            if(password.getText() .toString().length() < 8) {
                password.setError("password must be at least 8 chars long!");{
                isValid=false;
            }
        }

        if(isValid){
            String user_name = username.getText().toString();
            String user_pass = password.getText().toString();
            if(user_name.equals("yara@wajeeh") && user_pass.equals("yara2001")){
                Intent i = new Intent(Loginpage.this, Home.class);
                startActivity(i);
                this.finish();
            }else {
                Toast t = Toast.makeText(this ,"wrong email" ,Toast.LENGTH_SHORT);
                 t.show();
            }
        }


    }
    }
    private boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);

        }


    private boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

}






















 */
        /* timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Loginpage.this,Registration.class);
                startActivity(intent);
                finish();
            }
        },5000);*/

