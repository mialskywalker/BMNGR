package com.gungalovelectronics.budget_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import static com.github.mikephil.charting.utils.ColorTemplate.*;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        BarChart barChart = findViewById(R.id.Chart);

        ArrayList<BarEntry> visitors = new ArrayList<>();

        DbHelper db = new DbHelper(this);
        Cursor cursor = db.readAllData();
        int i = 1;
        while(cursor.moveToNext()){
            visitors.add(new BarEntry( i,Integer.parseInt(cursor.getString(1))));
            i++;
        }
//        visitors.add(new BarEntry(2015,420));
//        visitors.add(new BarEntry(2016,-320));
//        visitors.add(new BarEntry(2017,720));
//        visitors.add(new BarEntry(2018,520));
//        visitors.add(new BarEntry(2019,820));

        BarDataSet barDataSet = new BarDataSet(visitors, "Visitors");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Budget Chart");
        barChart.animateY(2000);

    }
}