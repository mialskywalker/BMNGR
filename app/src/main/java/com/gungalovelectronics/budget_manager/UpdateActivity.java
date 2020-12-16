package com.gungalovelectronics.budget_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText newValue;
    String id, income, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        newValue = findViewById(R.id.et_input);
        Button updateButton = findViewById(R.id.et_button);

        getAndSetIntentData();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper myDB = new DbHelper(UpdateActivity.this);
                myDB.updateData(id, Double.parseDouble(income), date);
                Intent intent = new Intent(getApplicationContext(), DataVisualisation.class);
                startActivity(intent);
            }
        });
        getAndSetIntentData();
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("income")){
            //Getting Data
            date = getIntent().getStringExtra("date");
            income = getIntent().getStringExtra("income");
            id = getIntent().getStringExtra("id");

            //Setting Data
            newValue.setText(income);

        }
        else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
}