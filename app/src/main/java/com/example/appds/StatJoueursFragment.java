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
public class StatJoueursFragment extends Fragment {
    private PieChart pieChart;
    String no;
   int m;
    int n;
     int c;
    Button stat;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        ViewGroup voa=(ViewGroup)inflater.inflate(R.layout.fragment_statjoueur, container, false);
        pieChart =voa.findViewById(R.id.stat_pieChart);

        setupPieChart();
        loadPieChartData();
        stat=voa.findViewById(R.id.StatistiqueE);
        stat.setOnClickListener(new View.OnClickListener() {
         @Override
        public void onClick(View v) {
        Intent i=new Intent(getActivity(),Stat2.class);
              startActivity(i);
        }
        });
        return voa;
     }
    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Le Meilleur");
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
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("joueurs");
        //orderByChild
        ref.orderByChild("name").equalTo("Neymar").addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                n=0;
                if (snapshot.exists()) {
                    for (DataSnapshot datas : snapshot.getChildren()) {

                            n++;


                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }});
        ref.orderByChild("name").equalTo("Messi").addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            m=0;
                if (snapshot.exists()) {
                    for (DataSnapshot datas : snapshot.getChildren()) {
                        m=m+1;


                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }});
        ref.orderByChild("name").equalTo("Cristiano Ronaldo").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
c=0;
                if (snapshot.exists()) {
                    for (DataSnapshot datas : snapshot.getChildren()) {
                        c=c+1;


                    }
                }
              //  Toast.makeText(getContext(),"le joueur est"+c,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }});


        entries.add(new PieEntry(0.36f, "Messi"));
        entries.add(new PieEntry(0.3f, "Ronaldo"));
        entries.add(new PieEntry(0.34f, "Neymar"));

        //entries.add(new PieEntry(n/s, "Neymar"));
        //entries.add(new PieEntry(m/s, "Messi"));
        //entries.add(new PieEntry(c/s, "Cristiano"));

        ArrayList<Integer> colors = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        for (int color: ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "le meilleur joueur");
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
