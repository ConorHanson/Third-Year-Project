package com.example.rugstats;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class ViewStats extends AppCompatActivity {

    DatabaseReference ref,db,db2,db3,db4,db5,db6,db7,db8,db9,db10,db11,db12,db13,db14,db15,db16,db17,db18,db19,db20,db21,db22,db23,db24;

    TextView a,b,name,our22ft, our22ut, our22lm, our22tm, our22tw, ourhft, ourhut, ourhlm, ourhtm, ourhtw, opphft, opphut, opphlm, opphtm, opphtw, opp22ft, opp22ut, opp22lm, opp22tm, opp22tw;

    Button viewour22,viewourhalf,viewophalf,viewop22,tl,full_time,visual;

    int totaltm,totaltw,totallm;

    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_stat);
        visual = findViewById(R.id.buttonvisual);
        tl = findViewById(R.id.timeline);
        full_time = findViewById(R.id.fulltime);

        viewour22 = (Button) findViewById(R.id.viewour22);
        viewour22.setVisibility(View.VISIBLE);
        viewour22.setBackgroundColor(Color.TRANSPARENT);

        viewourhalf = (Button) findViewById(R.id.button2);
        viewourhalf.setVisibility(View.VISIBLE);
        viewourhalf.setBackgroundColor(Color.TRANSPARENT);

        viewophalf = (Button) findViewById(R.id.viewoph);
        viewophalf.setVisibility(View.VISIBLE);
        viewophalf.setBackgroundColor(Color.TRANSPARENT);

        viewop22 = (Button) findViewById(R.id.viewop22);
        viewop22.setVisibility(View.VISIBLE);
        viewop22.setBackgroundColor(Color.TRANSPARENT);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        final String team = extras.getString("EXTRA1");
        final String curr_team = extras.getString("EXTRA2");
//Sumoning the correct pop up graphs
        viewour22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {

                Intent intent = new Intent(ViewStats.this, ViewPopup.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA1", team);
                extras.putString("EXTRA2", curr_team);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        viewourhalf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Intent intent = new Intent(ViewStats.this, ViewPopup2.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA1", team);
                extras.putString("EXTRA2", curr_team);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        viewophalf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {

                Intent intent = new Intent(ViewStats.this, ViewPopup3.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA1", team);
                extras.putString("EXTRA2", curr_team);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        viewop22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {

                Intent intent = new Intent(ViewStats.this, ViewPopup4.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA1", team);
                extras.putString("EXTRA2", curr_team);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
        
//Put the match name at top of page
        name = findViewById(R.id.teamname);
        name.setText(team);
        ref = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Away Score");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Show away score
                if (dataSnapshot.exists()) {
                    Integer score = dataSnapshot.getValue(Integer.class);
                    a = (TextView) findViewById(R.id.away);
                    String score_a = score.toString();
                    a.setText(score_a);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Home Score");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Show home score
                if (dataSnapshot.exists()) {
                    Integer score = dataSnapshot.getValue(Integer.class);
                    b = (TextView) findViewById(R.id.home);
                    String score_a = score.toString();
                    b.setText(score_a);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
//Get the stat values for each stat
        db2 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Our 22").child("Forced Turnover");
        db2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    our22ft = findViewById(R.id.our22ft);
                    String ft = total.toString();
                    our22ft.setText(ft);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });


        db3 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Our 22").child("Unforced Turnover");
        db3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    our22ut = (TextView) findViewById(R.id.our22ut);
                    String ut = total.toString();
                    our22ut.setText(ut);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db4 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Our 22").child("Linebreaks Made");
        db4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    totallm = totallm + total;
                    our22lm = (TextView) findViewById(R.id.our22lm);
                    String lm = total.toString();
                    our22lm.setText(lm);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });


        db5 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Our 22").child("Turnover Won");
        db5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    totaltw = totaltw + total;
                    our22tw = (TextView) findViewById(R.id.our22tw);
                    String tw = total.toString();
                    our22tw.setText(tw);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db6 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Our 22").child("Tackles Missed");
        db6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    totaltm = totaltm + total;
                    our22tm = (TextView) findViewById(R.id.our22tm);
                    String tm = total.toString();
                    our22tm.setText(tm);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db7 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Our Half").child("Forced Turnover");
        db7.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    ourhft = (TextView) findViewById(R.id.ourhft);
                    String ft = total.toString();
                    ourhft.setText(ft);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db8 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Our Half").child("Unforced Turnover");
        db8.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    ourhut = (TextView) findViewById(R.id.ourhut);
                    String ut = total.toString();
                    ourhut.setText(ut);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db9 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Our Half").child("Linebreaks Made");
        db9.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    totallm = totallm + total;
                    ourhlm = (TextView) findViewById(R.id.ourhlm);
                    String lm = total.toString();
                    ourhlm.setText(lm);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db10 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Our Half").child("Turnover Won");
        db10.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    totaltw = totaltw + total;
                    ourhtw = (TextView) findViewById(R.id.ourhtw);
                    String tw = total.toString();
                    ourhtw.setText(tw);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db11 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Our Half").child("Tackles Missed");
        db11.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    totaltm = totaltm + total;
                    ourhtm = (TextView) findViewById(R.id.ourhtm);
                    String tm = total.toString();
                    ourhtm.setText(tm);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db12 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Opposition Half").child("Forced Turnover");
        db12.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    opphft = (TextView) findViewById(R.id.ophft);
                    String ft = total.toString();
                    opphft.setText(ft);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db13 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Opposition Half").child("Unforced Turnover");
        db13.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    opphut = (TextView) findViewById(R.id.ophut);
                    String ut = total.toString();
                    opphut.setText(ut);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db14 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Opposition Half").child("Linebreaks Made");
        db14.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    totallm = totallm + total;
                    opphlm = (TextView) findViewById(R.id.ophlm);
                    String lm = total.toString();
                    opphlm.setText(lm);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db15 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Opposition Half").child("Turnover Won");
        db15.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    totaltw = totaltw + total;
                    opphtw = (TextView) findViewById(R.id.ophtw);
                    String tw = total.toString();
                    opphtw.setText(tw);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db16 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Opposition Half").child("Tackles Missed");
        db16.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    totaltm = totaltm + total;
                    opphtm = (TextView) findViewById(R.id.ophtm);
                    String tm = total.toString();
                    opphtm.setText(tm);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db17 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Opposition 22").child("Forced Turnover");
        db17.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    opp22ft = (TextView) findViewById(R.id.op22ft);
                    String ft = total.toString();
                    opp22ft.setText(ft);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db18 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Opposition 22").child("Unforced Turnover");
        db18.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    opp22ut = (TextView) findViewById(R.id.op22ut);
                    String ut = total.toString();
                    opp22ut.setText(ut);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db19 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Opposition 22").child("Linebreaks Made");
        db19.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    totallm = totallm + total;
                    opp22lm = (TextView) findViewById(R.id.op22lm);
                    String lm = total.toString();
                    opp22lm.setText(lm);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db20 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Opposition 22").child("Turnover Won");
        db20.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    totaltw = totaltw + total;
                    opp22tw = (TextView) findViewById(R.id.op22tw);
                    String tw = total.toString();
                    opp22tw.setText(tw);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db21 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Opposition 22").child("Tackles Missed");
        db21.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    totaltm = totaltm + total;
                    opp22tm = (TextView) findViewById(R.id.op22tm);
                    String tm = total.toString();
                    opp22tm.setText(tm);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

//Get the total values for stats acroos all areas of pitch
        db22 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Total TM");
        db23 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Total TW");
        db24 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Total LM");
        visual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewStats.this, VisualStats.class);
                Bundle extras = new Bundle();
                db22.setValue(totaltm);
                db23.setValue(totaltw);
                db24.setValue(totallm);
                extras.putString("EXTRA1", team);
                extras.putString("EXTRA2", curr_team);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        tl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewStats.this, Timeline.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA1", team);
                extras.putString("EXTRA2", curr_team);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        full_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db22.setValue(totaltm);
                db23.setValue(totaltw);
                db24.setValue(totallm);
                Intent intent = new Intent(ViewStats.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }


}


