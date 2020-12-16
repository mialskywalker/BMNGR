package com.gungalovelectronics.budget_manager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> column_income, column_date, column_id;

//    int position;

    CustomAdapter(Context context, ArrayList column_income, ArrayList column_date, ArrayList column_id){
        this.context = context;
        this.column_income = column_income;
        this.column_date = column_date;
        this.column_id = column_id;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        this.position = position;
        holder.IncomeValueTV.setText(String.valueOf(column_income.get(position)));
        holder.IncomeDateTV.setText(String.valueOf(column_date.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("income", String.valueOf(column_income.get(position)));
                intent.putExtra("id", String.valueOf(column_id.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return column_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView IncomeValueTV, IncomeDateTV;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            IncomeValueTV = itemView.findViewById(R.id.IncomeValueTV);
            IncomeDateTV = itemView.findViewById(R.id.IncomeDateTV);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
