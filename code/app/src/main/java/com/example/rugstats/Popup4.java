package com.example.rugstats;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Popup4 extends AppCompatActivity {
    //follows the exact same process as popup, on difference is the location of the aevents being pushed.
    //We decided to do our popups as 4 seperat classes as it made error handling alot easier.
    int total_lm = 0;
    int total_tmi = 0;
    int total_tw = 0;
    int total_tol = 0;
    int total_tow = 0;
    long sec;
    long min;
    long hour;

    DatabaseReference reftm;
    DatabaseReference reftmi;
    DatabaseReference reftl;
    DatabaseReference ref;
    DatabaseReference ref2;
    DatabaseReference reftw;
    TextView a;
    Team team;
    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
    Button tow;
    Button tol;
    Button tm;
    Button tmi;
    Button tw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);
        //set the content view to popup
        
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int w = dm.widthPixels;
        int h = dm.heightPixels;
        getWindow().setLayout((int)(w*.8),(int)(h *.6));
        //set up the display of the vindow

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String enter_team = extras.getString("EXTRA1");
        String curr_team = extras.getString("EXTRA2");
        String start_time = extras.getString("EXTRA3");
        //get the intent values

        final String timeStamp = new SimpleDateFormat("hh:mm:ss").format(new Date());
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        Date d1 = null;
        Date d2 = null;

        try {
            d2 = format.parse(timeStamp);
            d1 = format.parse(start_time);
            long diff = d2.getTime() - d1.getTime();
            long diffs = diff / 1000 % 60;
            sec = diffs;
            long diffm = diff / (60 * 1000) % 60;
            min = diffm;
            long diffh = diff / (60 * 60 * 1000) % 24;
            hour = diffh;
            //ms to time
        } catch (Exception e) {
            e.printStackTrace();
        }
        tow = (Button) findViewById(R.id.buttonto);
        //set up stat taking for forced turnovers
        reftl = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(enter_team).child("Timeline").push();
        ref = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(enter_team).child("Opposition 22").child("Forced Turnover");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    ref.setValue(total_tow);
                    int total = dataSnapshot.getValue(Integer.class);
                    //get the current value in the db
                    total_tow = total;
                    ref.setValue(total_tow);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        tow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                reftl.setValue("Opp 22    FT" + "    "+ hour + ":" + min + ":" + sec);
                increment();
                ref.setValue(total_tow);
                //increment and set the new value
                finish();
            }
            public void increment() {
                total_tow += 1;
            }
        });

        tol = (Button) findViewById(R.id.tol);
        //set up the stats taking for unforced turnovers
        ref2 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(enter_team).child("Opposition 22").child("Unforced Turnover");
        ref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    ref2.setValue(total_tol);
                    int total = dataSnapshot.getValue(Integer.class);
                    //get the current value in the db
                    total_tol = total;
                    ref2.setValue(total_tol);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        tol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                reftl.setValue("Opp 22    FT" + "    "+ hour + ":" + min + ":" + sec);
                //set the value on the limeline
                increment();
                ref2.setValue(total_tol);
                //increment and set the new value
                
                finish();
            }
            public void increment() {
                total_tol += 1;
            }
        });
        
        //set up stat taking for Linebreaks made.
        tm = (Button) findViewById(R.id.tm);
        reftm = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(enter_team).child("Opposition 22").child("Linebreaks Made");
        reftm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    reftm.setValue(total_lm);
                    int total = dataSnapshot.getValue(Integer.class);
                    //get the current value in the db
                    total_lm = total;
                    reftm.setValue(total_lm);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        tm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                reftl.setValue("Opp 22    FT" + "    "+ hour + ":" + min + ":" + sec);
                //set the timeline the the value
                increment();
                reftm.setValue(total_lm);
                //increment and set the new value
            
                finish();
            }
            public void increment() {
                total_lm += 1;
            }
        });
        //setup stat taking for turnovers won
        tw = (Button) findViewById(R.id.tw);
        reftw = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(enter_team).child("Opposition 22").child("Turnover Won");
        reftw.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    reftw.setValue(total_tw);
                    int total = dataSnapshot.getValue(Integer.class);
                    //get the current db value
                    total_tw = total;
                    reftw.setValue(total_tw);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        tw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                reftl.setValue("Opp 22    FT" + "    "+ hour + ":" + min + ":" + sec);
                //set the value in the timeline
                increment();
                reftw.setValue(total_tw);
                //increment and set the new value
                finish();
            }
            public void increment() {
                total_tw += 1;
            }
        });

        //set up stat taking for tackles missed
        tmi = (Button) findViewById(R.id.tmi);
        reftmi = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(enter_team).child("Opposition 22").child("Tackles Missed");
        reftmi.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    reftmi.setValue(total_tmi);
                    int total = dataSnapshot.getValue(Integer.class);
                    //get the current value of the db
                    total_tmi = total;
                    reftmi.setValue(total_tmi);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        tmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                reftl.setValue("Opp 22    FT" + "    "+ hour + ":" + min + ":" + sec);
                increment();
                reftmi.setValue(total_tmi);
                //increment and set the new value
                finish();
            }
            public void increment() {
                total_tmi += 1;
            }
        });
    }
}