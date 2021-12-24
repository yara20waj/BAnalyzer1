package com.yarawajeeh.bmianalyzer.OOP;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.yarawajeeh.bmianalyzer.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.RestaurantViewHolder> {
    private ArrayList<BMIRecord> mRestaurants = new ArrayList<>();
    private Context mContext;

    public Adapter(Context context, ArrayList<BMIRecord> restaurants) {
        mContext = context;
        mRestaurants = restaurants;
    }

    @Override
    public Adapter.RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_t_element, parent, false);
        RestaurantViewHolder viewHolder = new RestaurantViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Adapter.RestaurantViewHolder holder, int position) {
        holder.bindRestaurant(mRestaurants.get(position));
    }

    @Override
    public int getItemCount() {
        return mRestaurants.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder {
          TextView date;
          TextView status;
          TextView w;
          TextView l;
         Context mContext;

        public RestaurantViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            status =itemView.findViewById(R.id.status);
            w = itemView.findViewById(R.id.w);
            l = itemView.findViewById(R.id.l);

            mContext = itemView.getContext();
        }

        public void bindRestaurant(BMIRecord record) {
            date.setText(record.getDate());
            status.setText(record.getStatus());
            w.setText(String.valueOf(record.getW()));
            l.setText(String.valueOf(record.getL()));
        }
    }
}
