package com.example.appds;

import android.graphics.Color;
import android.os.Bundle;
import java.lang.Integer;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import  android.content.Intent;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.lang.String;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;

import android.widget.Button;
import android.widget.Toast;
public class StatEquipesFragment extends Fragment {
    private PieChart pieChart;
    String no;
    int b;
    int fcb;
    int r;
    Button stat;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup voa=(ViewGroup)inflater.inflate(R.layout.fragment_statequipes, container, false);
        pieChart =voa.findViewById(R.id.stat_pieChart);

        setupPieChart();
        loadPieChartData();
        return voa;
    }
    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("L'equipe");
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);
    }
    private void loadPieChartData() {

        ArrayList<PieEntry> entries = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("equipes");
        ref.orderByChild("name").equalTo("Bayern").addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                b=0;
                if (snapshot.exists()) {
                    for (DataSnapshot datas : snapshot.getChildren()) {

                        b=b+1;


                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }});
        ref.orderByChild("name").equalTo("Real Madrid").addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                r=0;
                if (snapshot.exists()) {
                    for (DataSnapshot datas : snapshot.getChildren()) {
                        r=r+1;


                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }});
        ref.orderByChild("name").equalTo("Barccelonne").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                fcb=0;
                if (snapshot.exists()) {
                    for (DataSnapshot datas : snapshot.getChildren()) {
                        fcb=fcb+1;


                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }});

        entries.add(new PieEntry(0.45f, "Barcelonne"));
        entries.add(new PieEntry(0.25f, "Byern"));
        entries.add(new PieEntry(0.30f, "Real Madrid"));

        ArrayList<Integer> colors = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        for (int color: ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "La meilleur equipe");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }
}