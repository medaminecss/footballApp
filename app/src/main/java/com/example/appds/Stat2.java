package com.example.appds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Stat2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        getIntent();
        getSupportFragmentManager().beginTransaction().replace(R.id.FrameConatiner, new StatEquipesFragment()).commit();
    }
}