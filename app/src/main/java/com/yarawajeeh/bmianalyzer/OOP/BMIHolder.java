package com.yarawajeeh.bmianalyzer.OOP;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yarawajeeh.bmianalyzer.R;

public class BMIHolder extends RecyclerView.ViewHolder {
    private final TextView date;
    private final TextView status;private final TextView w;private final TextView l;private BMIRecord record;
    public BMIHolder(@NonNull View itemView, TextView date, TextView status, TextView w, TextView l) {
        super(itemView);
        this.date = itemView.findViewById(R.id.date);
        this.status =itemView.findViewById(R.id.status);
        this.w = itemView.findViewById(R.id.w);
        this.l = itemView.findViewById(R.id.l);
    }
public void setBMIRecord(BMIRecord record){
        this.record=record;
        date.setText(record.getDate());
        status.setText(record.getStatus());
        w.setText(String.valueOf(record.getW()));
        l.setText(String.valueOf(record.getL()));
}

}
