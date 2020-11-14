package com.example.rugstats;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
public class ViewPopup3 extends AppCompatActivity {

    int total_lm = 0;
    int total_tm = 0;
    int total_tw = 0;
    int total_ut = 0;
    int total_ft = 0;

    DatabaseReference db2;
    DatabaseReference db3;
    DatabaseReference db4;
    DatabaseReference db5;
    DatabaseReference db6;
    Team team;

    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupview);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int w = dm.widthPixels;
        int h = dm.heightPixels;
        getWindow().setLayout((int) (w * .8), (int) (h * .4));

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String team = extras.getString("EXTRA1");
        String curr_team = extras.getString("EXTRA2");

        db6 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Opposition Half").child("Forced Turnover");
        db6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer ft = dataSnapshot.getValue(Integer.class);
                    total_ft = ft;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db2 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Opposition Half").child("Unforced Turnover");
        db2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer ut = dataSnapshot.getValue(Integer.class);
                    total_ut = ut;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db3 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Opposition Half").child("Linebreaks Made");
        db3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer lm = dataSnapshot.getValue(Integer.class);
                    total_lm = lm;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db4 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Opposition Half").child("Turnover Won");
        db4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer tw = dataSnapshot.getValue(Integer.class);
                    total_tw = tw;
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }

        });

        db5 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Opposition Half").child("Tackles Missed");
        db5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer tm = dataSnapshot.getValue(Integer.class);
                    total_tm = tm;}
                BarChart barChart = (BarChart) findViewById(R.id.our22bc);

                ArrayList<BarEntry> entries = new ArrayList<>();
                entries.add(new BarEntry(total_ft, 0));
                entries.add(new BarEntry(total_ut, 1));
                entries.add(new BarEntry(total_lm, 2));
                entries.add(new BarEntry(total_tw, 3));
                entries.add(new BarEntry(total_tm, 4));

                ArrayList<Integer> colors = new ArrayList<Integer>();

                colors.add(ContextCompat.getColor(ViewPopup3.this, R.color.Red));;
                colors.add(ContextCompat.getColor(ViewPopup3.this, R.color.Red));
                colors.add(ContextCompat.getColor(ViewPopup3.this, R.color.Green));
                colors.add(ContextCompat.getColor(ViewPopup3.this, R.color.Green));
                colors.add(ContextCompat.getColor(ViewPopup3.this, R.color.Red));
                BarDataSet bardataset = new BarDataSet(entries, "Cells");

                ArrayList<String> labels = new ArrayList<String>();
                labels.add("FT");
                labels.add("UT");
                labels.add("LM");
                labels.add("TW");
                labels.add("TM");

                BarData data = new BarData(labels, bardataset);
                barChart.setData(data); // set the data and list of lables into chart
                barChart.setDescription(" ");  // set the description
                bardataset.setColors(colors);
                barChart.animateY(1000);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }

        });


    }



}

