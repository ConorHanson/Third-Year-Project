package com.example.rugstats;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Popup2 extends AppCompatActivity {

    //follows the exact same process as popup, on difference is the location of the aevents being pushed.
    //We decide to do our popups as 4 seperat classes as it made error handling alot easier.
    
    //setting up the variables for poup

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
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        // set up the dimensions for popup
        setContentView(R.layout.popup);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int w = dm.widthPixels;
        int h = dm.heightPixels;
        getWindow().setLayout((int)(w*.8),(int)(h *.6));
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        
        //get the intent strings.
        final String enter_team = extras.getString("EXTRA1");
        String curr_team = extras.getString("EXTRA2");
        String start_time = extras.getString("EXTRA3");
        
        
        
        //create a timestamp
        final String timeStamp = new SimpleDateFormat("hh:mm:ss").format(new Date());
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");

        Date d1 = null;
        Date d2 = null;

        try {
            d2 = format.parse(timeStamp);
            d1 = format.parse(start_time);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();
            //get the diff in time
            long diffs = diff / 1000 % 60;
            sec = diffs;
            long diffm = diff / (60 * 1000) % 60;
            min = diffm;
            long diffh = diff / (60 * 60 * 1000) % 24;
            hour = diffh;
        } catch (Exception e) {
            e.printStackTrace();
        }


        
        //take stats for forced turnovers
        tow = (Button) findViewById(R.id.buttonto);
        ref = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(enter_team).child("Our 22").child("Forced Turnover");
        reftl = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(enter_team).child("Timeline").push();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    ref.setValue(total_tow);
                    int total = dataSnapshot.getValue(Integer.class);
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

                reftl.setValue("Our 22    FT" + "    "+ hour + ":" + min + ":" + sec);
                //increment the value
                increment();

                ref.setValue(total_tow);
                //set the db to that value

                finish();


            }

            public void increment() {
                total_tow += 1;
            }


        });
        //set up the unforced turnover stats

        tol = (Button) findViewById(R.id.tol);
        ref2 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(enter_team).child("Our 22").child("Unforced Turnover");
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

                reftl.setValue("Our 22    UT" + "    "+ hour + ":" + min + ":" + sec);
                increment();

                ref2.setValue(total_tol);
                //increment and set the values to the databse
                finish();


            }

            public void increment() {
                total_tol += 1;
            }


        });


        //set up linebreak stat taking
        tm = (Button) findViewById(R.id.tm);
        reftm = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(enter_team).child("Our 22").child("Linebreaks Made");
        reftm.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    reftm.setValue(total_lm);
                    //get the current value in the db
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
                reftl.setValue("Our 22    LM" + "    "+ hour + ":" + min + ":" + sec);


                increment();
                //increment and set the new value to the db

                reftm.setValue(total_lm);

                finish();


            }

            public void increment() {
                total_lm += 1;
            }


        });

        
        //set up stat taking for turnovers won.
        tw = (Button) findViewById(R.id.tw);
        reftw = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(enter_team).child("Our 22").child("Turnover Won");
        reftw.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    reftw.setValue(total_tw);
                    int total = dataSnapshot.getValue(Integer.class);
                    //gert the current data in the db ref
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
                //set the db value for the timeline.
                reftl.setValue("Our 22    FT" + "    "+ hour + ":" + min + ":" + sec);
                increment();
                //increment and set the value to the database.

                reftw.setValue(total_tw);


                finish();


            }

            public void increment() {
                total_tw += 1;
            }


        });
        //set up stat taking for tackles missed
        tmi = (Button) findViewById(R.id.tmi);
        reftmi = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(enter_team).child("Our 22").child("Tackles Missed");
        reftmi.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    reftmi.setValue(total_tmi);
                    //get the values of the current db ref
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
                
                //setup the timeline values.

                reftl.setValue("Our 22    TW" + "    "+ hour + ":" + min + ":" + sec);
                //increment and set these values
                increment();

                reftmi.setValue(total_tmi);

                finish();


            }

            public void increment() {
                total_tmi += 1;
            }


        });



    }
}