package com.gungalovelectronics.budget_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAddIncome = findViewById(R.id.btnAddIncome);
        Button btnAddExpense = findViewById(R.id.btnAddExpense);
        Button btnViewData = findViewById(R.id.btnViewData);

        // button on click send to income activity
        btnAddIncome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               // System.out.println("Button Clicked");

                Intent incomeActivity = new Intent(getApplicationContext(), IncomeController.class);
                startActivity(incomeActivity);
            }
        });

        // button on click send to expense activity
        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //System.out.println("Button Clicked");

                Intent expenseActivity = new Intent(getApplicationContext(), ExpenseController.class);
        startActivity(expenseActivity);
    }
});

        btnViewData.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //System.out.println("Button Clicked");

                Intent expenseActivity = new Intent(getApplicationContext(), DataVisualisation.class);
                startActivity(expenseActivity);
            }
        });
    }
}