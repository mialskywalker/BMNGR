package com.gungalovelectronics.budget_manager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class DataVisualisation extends AppCompatActivity {

    RecyclerView recyclerView;

    DbHelper myDB;
    ArrayList<String> column_income, column_date, column_id;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_visualisation);

        Button btnBack = findViewById(R.id.button_back);
        recyclerView = findViewById(R.id.RecV);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main);
            }
        });

        myDB = new DbHelper(DataVisualisation.this);
        column_income = new ArrayList<>();
        column_date = new ArrayList<>();
        column_id = new ArrayList<>();

        storeDatainArrays();

        customAdapter = new CustomAdapter(DataVisualisation.this, column_income, column_date, column_id);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(DataVisualisation.this));
    }

    void storeDatainArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No data", Toast.LENGTH_SHORT).show();
        }   else{
            while(cursor.moveToNext()){
                column_income.add(cursor.getString(1));
                column_date.add(cursor.getString(2));
                column_id.add(cursor.getString(0));
            }
        }
    }
}