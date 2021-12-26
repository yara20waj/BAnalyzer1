package com.yarawajeeh.bmianalyzer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

public class editfood extends AppCompatActivity {
    Button save ;
    ImageView arrow;
    EditText f_name,f_calory,f_category,i;
    DatabaseReference Ref;
    String name,calory ,category ;
    StorageReference filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editfood);
        save = findViewById(R.id.save);
        arrow = findViewById(R.id.arrow);
        f_name = findViewById(R.id.name);
        f_calory= findViewById(R.id.calory);
        Ref = FirebaseDatabase.getInstance().getReference().child("BMI").child("Food").child(String.valueOf(i));


        save.setOnClickListener(v -> {
            Intent intent= new Intent(editfood.this,Food_List.class);
            startActivity(intent);
        });

    }
    private void SaveProductInfoToDatabase()
    {


        name = f_name.getText().toString();
        calory = f_calory.getText().toString();



        HashMap<String, Object> ChaletMap = new HashMap<>();
        ChaletMap.put("name", name);
        ChaletMap.put("calory", calory);
        ChaletMap.put("category", category);


        Ref.updateChildren(ChaletMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(editfood.this, "Done", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}