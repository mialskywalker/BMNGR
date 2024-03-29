package com.MialAndroid.budget_manager;

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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Calendar;

public class ExpenseController extends AppCompatActivity {
    private static final String TAG = "ExpenseController";

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    Button btnAdd;
    EditText expenseET;
    EditText expenseDescET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense_activity);

        mDisplayDate = (TextView) findViewById(R.id.textView_date);
        Button btnBack = findViewById(R.id.button_back);
        btnAdd = findViewById(R.id.button_addExpense);
        expenseET = findViewById(R.id.et_expense);
        expenseDescET = findViewById(R.id.et_expense_text);


        // Ads
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView adView = (AdView)findViewById(R.id.adView2);

        AdRequest adRequest = new AdRequest.Builder().build();

        adView.loadAd(adRequest);

        mDisplayDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        ExpenseController.this,
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

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BudgetModel budgetModel;

                try {
                    budgetModel = new BudgetModel(-1,expenseDescET.getText().toString(), Double.parseDouble(expenseET.getText().toString())*-1, mDisplayDate.getText().toString());
                    Toast.makeText(ExpenseController.this, "Успешно добавяне!", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {

                    Toast.makeText(ExpenseController.this, "Невалидни данни!", Toast.LENGTH_SHORT).show();
                    budgetModel = new BudgetModel(-1,"asd", 0.0, "error");
                }

                DbHelper dbHelper = new DbHelper(ExpenseController.this);

                boolean success = dbHelper.addOne(budgetModel);


            }
        });
    }
}
