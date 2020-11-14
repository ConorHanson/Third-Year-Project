package com.example.rugstats;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Popup extends AppCompatActivity {

    //set up of various variables used within the class
    int total_lm = 0;
    int total_tmi = 0;
    int total_tw = 0;
    int total_tol = 0;
    int total_tow = 0;
    DatabaseReference reftm,reftmi,reftl,ref,ref2,refpen,db1,reftry,refcona,refpena,reftrya,reftw,test;
    TextView a;
    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
    Button tow,tol,tm,tmi,tw;
    long min;
    long sec;
    long hour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);
        //display popup activity

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String enter_team = extras.getString("EXTRA1");
        String curr_team = extras.getString("EXTRA2");
        String start_time = extras.getString("EXTRA3");
        //get these 3 intents from pitchevent

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int w = dm.widthPixels;
        int h = dm.heightPixels;
        getWindow().setLayout((int)(w*.8),(int)(h *.6));
        //set up the vidth and height of the popup.

        final String timeStamp = new SimpleDateFormat("hh:mm:ss").format(new Date());
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        //set current time to "timestamp"
        Date d1 = null;
        Date d2 = null;

        try {
            d2 = format.parse(timeStamp);
            d1 = format.parse(start_time);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();
            //subtract start time from curr time


            long diffs = diff / 1000 % 60;
            sec = diffs;
            long diffm = diff / (60 * 1000) % 60;
            min = diffm;
            long diffh = diff / (60 * 60 * 1000) % 24;
            hour = diffh;

            //ms to current match time

        } catch (Exception e) {
            e.printStackTrace();
        }

        //set up button for forced turnvoer in our half
        tow = (Button) findViewById(R.id.buttonto);
        ref = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(enter_team).child("Our Half").child("Forced Turnover");
        reftl = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(enter_team).child("Timeline").push();
        //set the timeline location
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    ref.setValue(total_tow);
                    int total = dataSnapshot.getValue(Integer.class);
                    total_tow = total;
                    ref.setValue(total_tow);
                    //get the current value of the popup and set the locan int total tow to it
                    //set the db to that value
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        tow.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v2) {
                //when the button is pressed
                reftl.setValue("Our Half    FT" + "    "+ hour + ":" + min + ":" + sec);
                //push the value to the timeline
                increment();

                ref.setValue(total_tow);

                finish();
                //call the increment value to increae the value, set that value and close the popup


            }

            public void increment() {
                total_tow += 1;
            }
            //increase count of turnovers by 1


        });


        //same as prev method but with unforced turnvover

        tol = (Button) findViewById(R.id.tol);
        ref2 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(enter_team).child("Our Half").child("Unforced Turnover");
        ref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    ref2.setValue(total_tol);
                    int total = dataSnapshot.getValue(Integer.class);
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
                reftl.setValue("Our Half    UT" + "    "+ hour + ":" + min + ":" + sec);
                increment();
                ref2.setValue(total_tol);
                finish();
            }
            public void increment() {
                total_tol += 1;
            }

            //set new increased value
        });
        //same as prev method but with Linebreaks
        tm = (Button) findViewById(R.id.tm);
        reftm =FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(enter_team).child("Our Half").child("Linebreaks Made");
        reftm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    reftm.setValue(total_lm);
                    int total = dataSnapshot.getValue(Integer.class);
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
                reftl.setValue("Our Half    LM" + "    "+ hour + ":" + min + ":" + sec);
                increment();
                reftm.setValue(total_lm);
                finish();
            }
            public void increment() {
                total_lm += 1;
            }
        });

        //same as prev method but with  turnover won
        tw = (Button) findViewById(R.id.tw);
        reftw = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(enter_team).child("Our Half").child("Turnover Won");
        reftw.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    reftw.setValue(total_tw);
                    int total = dataSnapshot.getValue(Integer.class);
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

                reftl.setValue("Our Half    TW" + "    "+ hour + ":" + min + ":" + sec);


                increment();

                reftw.setValue(total_tw);

                finish();
            }
            public void increment() {
                total_tw += 1;
            }
            //incrase the value and set it
        });
        //same as prev method but with unforced Tackles missed.
        tmi = (Button) findViewById(R.id.tmi);
        reftmi =FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(enter_team).child("Our Half").child("Tackles Missed");
        reftmi.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    reftmi.setValue(total_tmi);
                    int total = dataSnapshot.getValue(Integer.class);
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

                reftl.setValue("Our Half    TM" + "    "+ hour + ":" + min + ":" + sec);


                increment();

                reftmi.setValue(total_tmi);

                finish();


            }

            public void increment() {
                total_tmi += 1;
            }
            //increment the db calue and set it to the db

        });
    }
}