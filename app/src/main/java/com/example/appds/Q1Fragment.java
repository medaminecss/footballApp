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


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Q1Fragment extends Fragment {
    RadioButton radioButton;
    Button Next;
    RadioGroup joueursR;
    DatabaseReference joueursDb;
    BottomNavigationView bottomNavigationView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         ViewGroup voa=(ViewGroup)inflater.inflate(R.layout.fragment_ques1, container, false);
         joueursR=voa.findViewById(R.id.joueurs);
         Next=voa.findViewById(R.id.Next);
        joueursDb=FirebaseDatabase.getInstance().getReference("joueurs");
          Next.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  int radioId=joueursR.getCheckedRadioButtonId();
                  radioButton=voa.findViewById(radioId);

                  String name=radioButton.getText().toString();
                  Toast.makeText(getContext(),"le joueur est"+name,Toast.LENGTH_LONG).show();
                  Joueurs joueur=new Joueurs(name);
                  joueursDb.push().setValue(joueur);


                  Questionnaire2Fragement quest2Fragment=new Questionnaire2Fragement();
                  FragmentTransaction fragmentTransaction=getChildFragmentManager().beginTransaction().replace(R.id.FrameConatiner,new Questionnaire2Fragement());
                  Intent i=new Intent(getActivity(), question2.class);
                  startActivity(i);
              }
          });

         return voa;

    }


}