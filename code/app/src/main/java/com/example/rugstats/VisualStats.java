package com.example.rugstats;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.os.Bundle;
import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
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
import java.util.List;

public class VisualStats extends AppCompatActivity {
    //all db refs for retrieving data to diplay on visual stats
    DatabaseReference db1,db2,db3,db4,db5,db6,db7,db8,db9,db10,db11,db12,db13,db14,db15,db16,db17,db18,db19,db20,db21;
    int total_to,total_uto,total_lm,total_tm,total_tw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.visual_stats);
        //set the current view to visual stats
        final String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String team = extras.getString("EXTRA1");
        final String curr_team = extras.getString("EXTRA2");
        //get the strings from the previous class

        db1 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Our 22").child("Forced Turnover");
        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    total_to = total;
                    //getting the total forced turnovers for a match
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db2 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Our Half").child("Forced Turnover");
        db2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    total_to = total_to + total;
                    //getting the total forced turnovers for a match

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }

        });

        db3 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Opposition Half").child("Forced Turnover");
        db3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    total_to = total_to + total;
                    //getting the total forced turnovers for a match

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db4 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Opposition 22").child("Forced Turnover");
        db4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    total_to = total_to + total;
                    //getting the total forced turnovers for a match

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db5 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Our 22").child("Unforced Turnover");
        db5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    total_uto = total_uto + total;
                    //getting the total unforced turnovers for a match

                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db6 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Our Half").child("Unforced Turnover");
        db6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    total_uto = total_uto + total;
                    //getting the total unforced turnovers for a match

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }

        });

        db7 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Opposition Half").child("Unforced Turnover");
        db7.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    total_uto = total_uto + total;
                    //getting the total unforced turnovers for a match

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db8 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Opposition 22").child("Unforced Turnover");
        db8.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                //method that sets up the chart view of all the gathered turnovers


                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    total_uto = total_uto + total;
                    Pie pie = AnyChart.pie();
                    AnyChartView anyChartView = (AnyChartView) findViewById(R.id.piechart);
                    //uses anychart library to create the piechart
                    APIlib.getInstance().setActiveAnyChartView(anyChartView);
                    List<DataEntry> data = new ArrayList<>();
                    data.add(new ValueDataEntry("Forced Turnover", total_to));
                    data.add(new ValueDataEntry("Unforced Turnover", total_uto));
                    //sets the piechart to the total turnvovers vs unforced turnovers
                    pie.data(data);
                    anyChartView.setChart(pie);
                }
                else {
                    //if there are no unforced turnovers in the opposition 22
                    db8.setValue(0);
                    //set value to 0
                    Integer total = dataSnapshot.getValue(Integer.class);
                    total_uto = total_uto + total;
                    //getting the total unforced turnovers for a match (0)
                    Pie pie = AnyChart.pie();
                    List<DataEntry> data = new ArrayList<>();
                    data.add(new ValueDataEntry("Forced Turnover", total_to) );
                    data.add(new ValueDataEntry("Unforced Turnover", total_uto) );
                    pie.data(data);
                    //display the totals in a puechart
                    AnyChartView anyChartView = (AnyChartView)findViewById(R.id.piechart);
                    anyChartView.setChart(pie);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db9 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Our 22").child("Linebreaks Made");
        db9.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    total_lm = total_lm + total;
                    //getting the total Linebreaks for our 22
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }


        });

        db10 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Our Half").child("Linebreaks Made");
        db10.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    total_lm = total_lm + total;
                    //getting the total Linebreaks for our half

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }

        });

        db11 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Opposition Half").child("Linebreaks Made");
        db11.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    total_lm = total_lm + total;
                    //getting the total Linebreaks for opp half


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }

        });

        db12 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Opposition 22").child("Linebreaks Made");
        db12.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    total_lm = total_lm + total;
                    //getting the total Linebreaks for opp 22

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }

        });

        db13 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Our 22").child("Tackles Missed");
        db13.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    total_tm = total_tm + total;
                    //getting the total Tackles missed for our 22

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db14 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Our Half").child("Tackles Missed");
        db14.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    total_tm = total_tm + total;
                    //getting the total Tackles missed for our half
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db15 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Opposition Half").child("Tackles Missed");
        db15.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    total_tm = total_tm + total;
                    //getting the total Tackles missed for opp half
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db16 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Opposition 22").child("Tackles Missed");
        db16.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    total_tm = total_tm + total;
                    //getting the total Tackles missed for opp 22
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db17 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Our 22").child("Turnover Won");
        db17.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    total_tw = total_tw + total;
                    //getting the total  Turnover won for our 22
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db18 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Our Half").child("Turnover Won");
        db18.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    total_tw = total_tw + total;
                    //getting the total  Turnover won for our half

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

        db19 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(team).child("Opposition Half").child("Turnover Won");
        db19.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer total = dataSnapshot.getValue(Integer.class);
                    total_tw = total_tw + total;
                    //getting the total  Turnover won for opp half

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
                    total_tw = total_tw + total;}
                //getting the total  Turnover won for opp 22

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
        //db ref to gather the average totals of each stats
        db21 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team);
        db21.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    //setting up the local variables
                    int tm_total = 0;
                    int tw_total = 0;
                    int lm_total = 0;
                    int i = 0;

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    //get all instance in the database of each total per team's game
                    tm_total = tm_total + (Integer.valueOf(ds.child("Total TM").getValue(int.class)));
                    tw_total = tw_total + (Integer.valueOf(ds.child("Total TW").getValue(int.class)));
                    lm_total = lm_total + (Integer.valueOf(ds.child("Total LM").getValue(int.class)));
                    //i count to divide for average
                    i = i + 1;

                }
                    //clalculating averarge
                    tm_total = tm_total / i;
                    tw_total = tw_total / i;
                    lm_total = lm_total / i;
                    //creating the barchart using MPAndroidChart
                    BarChart barChart = (BarChart) findViewById(R.id.barchart);
                    ArrayList<Integer> colors = new ArrayList<Integer>();
                    colors.add(ContextCompat.getColor(VisualStats.this, R.color.Blue));;
                    colors.add(ContextCompat.getColor(VisualStats.this, R.color.Bblue));
                    colors.add(ContextCompat.getColor(VisualStats.this, R.color.Blue));
                    colors.add(ContextCompat.getColor(VisualStats.this, R.color.Bblue));
                    colors.add(ContextCompat.getColor(VisualStats.this, R.color.Blue));
                    colors.add(ContextCompat.getColor(VisualStats.this, R.color.Bblue));
                    //adding all the totals and averages to the chart
                    ArrayList<BarEntry> entries = new ArrayList<>();
                    entries.add(new BarEntry(total_lm, 0));
                    entries.add(new BarEntry(lm_total, 1));
                    entries.add(new BarEntry(total_tm, 2));
                    entries.add(new BarEntry(tm_total, 3));
                    entries.add(new BarEntry(total_tw, 4));
                    entries.add(new BarEntry(tw_total, 5));

                    BarDataSet bardataset = new BarDataSet(entries, "Average Per Game");
                    ArrayList<String> labels = new ArrayList<String>();
                    //labels for the bar chart
                    labels.add("Linebreaks Made");
                    labels.add("APM");
                    labels.add("Tackles Missed");
                    labels.add("APM");
                    labels.add("Turnovers Won");
                    labels.add("APM");

                    BarData data = new BarData(labels, bardataset);
                    barChart.setData(data);
                    barChart.setDescription(" ");
                    bardataset.setColors(colors);
                    bardataset.setHighlightEnabled(false);
                    barChart.animateY(5000);
                    //set visual aspects, color, animation.
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });

    }
}




