package com.gungalovelectronics.budget_manager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;

public class IncomeController extends AppCompatActivity {


    private static final String TAG = "IncomeController";

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private String income, expense, timestamp, budget;
    private DbHelper dbHelper;
    EditText incomeET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.income_activity);



        mDisplayDate = (TextView) findViewById(R.id.textView_date);
        Button btnBack = findViewById(R.id.button_back);
        Button btnAdd = findViewById(R.id.button_addIncome);

        incomeET = findViewById(R.id.editText_income);

        //database object
        dbHelper = new DbHelper(this);


        mDisplayDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        IncomeController.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }


        });

        // back button to --> (MainActivity)
        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // System.out.println("Button Clicked");

                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivity);
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/yyyy: " + day + "/" + month + "/" + year);

                String date = day + "/" + month + "/" + year;
                mDisplayDate.setText(date);
            }
        };

        // add button
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get info
                getData();
            }
        });

    }

    private void getData() {

        income = ""+incomeET.getText().toString().trim();
        timestamp = ""+mDisplayDate.getText().toString().trim();


        Toast.makeText(this, "Успешно добавяне!", Toast.LENGTH_SHORT).show();
    }

}
