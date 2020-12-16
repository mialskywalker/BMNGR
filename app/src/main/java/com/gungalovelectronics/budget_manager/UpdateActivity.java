package com.gungalovelectronics.budget_manager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
        Button deleteButton = findViewById(R.id.delete_btn);

        getAndSetIntentData();

        Intent intent = getIntent();
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper myDB = new DbHelper(UpdateActivity.this);
                myDB.updateData(id, Double.parseDouble(String.valueOf(newValue)));
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

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Изтриване");
        builder.setMessage("Сигурни ли сте, че искате да изтриете този запис ?");
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DbHelper myDB = new DbHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                Intent intent = new Intent(getApplicationContext(), DataVisualisation.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Не", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}