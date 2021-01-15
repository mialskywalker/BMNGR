package com.MialAndroid.budget_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.AdView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAddIncome = findViewById(R.id.btnAddIncome);
        Button btnAddExpense = findViewById(R.id.btnAddExpense);
        Button btnViewData = findViewById(R.id.btnViewData);
        //Button btnChart = findViewById(R.id.btnChart);
        TextView AvbMoney = findViewById(R.id.available_money);


        // Ads
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView adView = (AdView)findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();

        adView.loadAd(adRequest);


        double MainBudjetValue = 0;
        DbHelper db = new DbHelper(this);
        Cursor cursor = db.readAllData();
            while(cursor.moveToNext()){
                MainBudjetValue += Double.parseDouble(cursor.getString(2));
            }

        AvbMoney.setText(String.valueOf(MainBudjetValue));

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

       /* btnChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChartActivity.class);
                startActivity(intent);
            }
        }); */
    }
}