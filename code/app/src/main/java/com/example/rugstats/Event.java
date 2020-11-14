package com.example.rugstats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;





public class Event extends AppCompatActivity {

    int home_s = 0;
    int away_s = 0;


    DatabaseReference reftm;
    DatabaseReference reftmi;
    DatabaseReference reft;
    DatabaseReference ref;
    DatabaseReference ref2;
    DatabaseReference refcon;
    DatabaseReference refpen;
    DatabaseReference reftry;
    DatabaseReference refcona;
    DatabaseReference refpena;
    DatabaseReference reftrya;
    DatabaseReference reftw;

    TextView a;




    Team team;

    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();



    Button tow;
    Button tol;
    Button conh;
    Button tryh;
    Button penh;
    Button cona;
    Button trya;
    Button pena;
    Button tm;
    Button tmi;
    Button tw;
    Toolbar bar;


    private FirebaseAuth mAuth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event);

        bar = (Toolbar) findViewById(R.id.bar);
        setSupportActionBar(bar);
        getSupportActionBar().setTitle("Rugstat");




        tow = (Button) findViewById(R.id.buttonto);
        team = new Team();

        reft = FirebaseDatabase.getInstance().getReference("Users").child(currentuser).child("Teams");
        reft.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot d : dataSnapshot.getChildren()){

                    String name = d.child("name").getValue(String.class);
                    a = (TextView)findViewById(R.id.team_n);


                    a.setText(name);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }


        });



        ref = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child("Match").child("Forced Turnover");
        ref.setValue(0);
        tow.setOnClickListener(new View.OnClickListener() {
            int total_tow =+ 0;
            @Override
            public void onClick(View v2) {

                ref.setValue(total_tow);

                increment();

                ref.setValue(total_tow);




            }

            public void increment()
            {
                total_tow ++;
            }




        });

        tol = (Button) findViewById(R.id.tol);
        ref2 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child("Match").child("Unforced Turnover");
        ref2.setValue(0);

        tol.setOnClickListener(new View.OnClickListener() {
            int total_tol =+ 0;
            @Override
            public void onClick(View v) {


                increment2();



                ref2.setValue(total_tol);







            }

            public void increment2()
            {
                total_tol ++;
            }




        });


        tm = (Button) findViewById(R.id.tm);
        reftm = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child("Match").child("Linebreaks Made");
        reftm.setValue(0);
        tm.setOnClickListener(new View.OnClickListener() {
            int total_tm = 0;
            @Override
            public void onClick(View v2) {

                reftm.setValue(total_tm);

                increment();

                reftm.setValue(total_tm);




            }

            public void increment()
            {
                total_tm ++;
            }




        });

        tw = (Button) findViewById(R.id.tw);
        reftw = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child("Match").child("Turnovers Won");
        reftw.setValue(0);
        tw.setOnClickListener(new View.OnClickListener() {
            int total_tw = 0;
            @Override
            public void onClick(View v2) {

                reftw.setValue(total_tw);

                increment();

                reftw.setValue(total_tw);




            }

            public void increment()
            {
                total_tw ++;
            }




        });

        tmi = (Button) findViewById(R.id.tmi);
        reftmi = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child("Match").child("Tackle Missed");
        reftmi.setValue(0);

        tmi.setOnClickListener(new View.OnClickListener() {
            int total_tmi = 0;
            @Override
            public void onClick(View v) {


                increment2();



                reftmi.setValue(total_tmi);







            }

            public void increment2()
            {
                total_tmi ++;
            }




        });

        conh = (Button) findViewById(R.id.conhome);
        tryh = (Button) findViewById(R.id.tryhome);
        penh = (Button) findViewById(R.id.penhome);
        cona = (Button) findViewById(R.id.cona);
        trya = (Button) findViewById(R.id.trya);
        pena = (Button) findViewById(R.id.pena);


        refcon = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child("Match").child("Home Score");
        refcon.setValue(0);
        conh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v2) {

                increment();

                refcon.setValue(home_s);




            }

            public void increment()
            {
                home_s = home_s + 2 ;
            }




        });

        team = new Team();



        reftry = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child("Match").child("Home Score");
        reftry.setValue(0);
        tryh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {

                increment();

                reftry.setValue(home_s);




            }

            public void increment()
            {
                home_s = home_s + 5 ;
            }




        });

        refpen = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child("Match").child("Home Score");
        refpen.setValue(0);
        penh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {

                increment();

                refpen.setValue(home_s);




            }

            public void increment()
            {
                home_s = home_s + 3 ;
            }




        });

        refcona = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child("Match").child("Away Score");
        refcona.setValue(0);
        cona.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v2) {

                increment();

                refcona.setValue(away_s);




            }

            public void increment()
            {
                away_s = away_s + 2 ;
            }




        });




        reftrya = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child("Match").child("Away Score");
        reftrya.setValue(0);
        trya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {

                increment();

                reftrya.setValue(away_s);




            }

            public void increment()
            {
                away_s = away_s + 5 ;
            }




        });

        refpena = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child("Match").child("Away Score");
        refpena.setValue(0);
        pena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {

                increment();

                refpena.setValue(away_s);




            }

            public void increment()
            {
                away_s = away_s + 3 ;
            }




        });





    }

}


