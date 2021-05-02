package com.example.appds;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Questionnaire2Fragement extends Fragment {
    RadioButton radioButton;
    Button btnvote;
    RadioGroup equipes;
    DatabaseReference equipesDb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup voa = (ViewGroup) inflater.inflate(R.layout.fragment_questionnaire2, container, false);
        equipes = voa.findViewById(R.id.Equipes);
        btnvote = voa.findViewById(R.id.btnvote);
        equipesDb = FirebaseDatabase.getInstance().getReference("equipes");
        btnvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int radioId = equipes.getCheckedRadioButtonId();
                radioButton = voa.findViewById(radioId);

                String name = radioButton.getText().toString();
                Toast.makeText(getContext(), "l'equipe est" + name, Toast.LENGTH_LONG).show();
                Joueurs joueur = new Joueurs(name);
                equipesDb.push().setValue(joueur);
                StatJoueursFragment searchFragment=new StatJoueursFragment();
                FragmentTransaction fragmentTransaction=getChildFragmentManager().beginTransaction();
                Intent x=new Intent(getActivity(),Stat1.class);
                startActivity(x);
            }
        });
        return voa;

    }
}


