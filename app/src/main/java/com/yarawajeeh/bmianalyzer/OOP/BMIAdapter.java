package com.yarawajeeh.bmianalyzer.OOP;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yarawajeeh.bmianalyzer.R;

import java.util.ArrayList;

public class BMIAdapter extends RecyclerView.Adapter<BMIHolder> {
    private ArrayList<BMIRecord> records;
    private Context context;

    public BMIAdapter(ArrayList<BMIRecord> records,Context context){
        this.context=context;
        this.records=records;
    }


    @NonNull
    @Override
    public BMIHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.elemnt,false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BMIHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
