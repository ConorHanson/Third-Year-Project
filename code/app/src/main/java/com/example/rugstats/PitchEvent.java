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
import java.text.SimpleDateFormat;
import java.util.Date;
public class PitchEvent extends AppCompatActivity {

    int home_s;
    long away_s;
    long sec;
    long min;
    long hour;
    //setup on text views,dbrefs,strings and buttons that will be used.
    TextView home,away;
    DatabaseReference refcon,refcona,reftw,reftl,ref,db;
    TextView a;
    Team team;
    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
    Button opp22,op22,conh, ophalf,cona, penh, trya, pena, tm ,tmi, tw, our22,tryhome, viewstats,c_home,p_home,t_home,c_away,p_away,t_away;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pitch_event);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String enter_team = extras.getString("EXTRA1");
        final String curr_team = extras.getString("EXTRA2");
        final String time = extras.getString("EXTRA3");
        final String timepop = time;

        tw = (Button) findViewById(R.id.our_half2);
        tw.setVisibility(View.VISIBLE);
        tw.setBackgroundColor(Color.TRANSPARENT);
        viewstats = (Button) findViewById(R.id.stats);


        //setting up the buttons for the areas of the pitch

        opp22 = (Button) findViewById(R.id.opp22);
        opp22.setVisibility(View.VISIBLE);
        opp22.setBackgroundColor(Color.TRANSPARENT);

        op22 = (Button) findViewById(R.id.op22);
        op22.setVisibility(View.VISIBLE);
        op22.setBackgroundColor(Color.TRANSPARENT);

        our22 = (Button) findViewById(R.id.our_half2);
        our22.setVisibility(View.VISIBLE);
        our22.setBackgroundColor(Color.TRANSPARENT);

        ophalf = (Button) findViewById(R.id.opp_half);
        ophalf.setVisibility(View.VISIBLE);
        home = (TextView) findViewById(R.id.home);
        away = (TextView) findViewById(R.id.away);
        ophalf.setBackgroundColor(Color.TRANSPARENT);

        // setting the text of the current match score (away)

        ref = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(enter_team).child("Away Score");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //if this part of the db exists
                if (dataSnapshot.exists()) {
                    Integer score = dataSnapshot.getValue(Integer.class);
                    away = (TextView) findViewById(R.id.away);
                    String score_a = score.toString();
                    //set the text to the current score
                    away.setText(score_a);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
        // setting the text of the current match score (home)

        db = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(enter_team).child("Home Score");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Integer score = dataSnapshot.getValue(Integer.class);
                    home = (TextView) findViewById(R.id.home);
                    String score_a = score.toString();
                    //set the text to the current score
                    home.setText(score_a);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
        opp22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                //send the following info the popup class upon a click
                Intent intent = new Intent(PitchEvent.this, Popup.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA1",enter_team);
                extras.putString("EXTRA2",curr_team);
                extras.putString("EXTRA3",timepop);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        viewstats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send the following info the viewstats class upon a click
                Intent intent = new Intent(PitchEvent.this, ViewStats.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA1",enter_team);
                extras.putString("EXTRA2",curr_team);
                intent.putExtras(extras);
                startActivity(intent);

            }
        });

        our22.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v2) {

                //send the following info the popup class upon a click

                Intent intent = new Intent(PitchEvent.this, Popup2.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA1",enter_team);
                extras.putString("EXTRA2",curr_team);
                extras.putString("EXTRA3",timepop);
                intent.putExtras(extras);
                startActivity(intent);

            }

        });

        op22.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v2) {
                //send the following info the popup class upon a click
                Intent intent = new Intent(PitchEvent.this, Popup4.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA1",enter_team);
                extras.putString("EXTRA2",curr_team);
                extras.putString("EXTRA3",time);
                intent.putExtras(extras);
                startActivity(intent);

            }


        });


        ophalf.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v2) {
                //send the following info the popup class upon a click
                Intent intent = new Intent(PitchEvent.this, Popup3.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA1",enter_team);
                extras.putString("EXTRA2",curr_team);
                extras.putString("EXTRA3",time);
                intent.putExtras(extras);
                startActivity(intent);


            }

        });
        //setup of all the minus score keeping buttons
        conh = (Button) findViewById(R.id.conhome);
        c_home = (Button)findViewById(R.id.button2h);
        p_home = (Button)findViewById(R.id.button3h);
        t_home = (Button)findViewById(R.id.button5h);
        c_away = (Button)findViewById(R.id.button2a);
        p_away = (Button)findViewById(R.id.button3a);
        t_away = (Button)findViewById(R.id.button5a);
        //setup of all the score keeping buttons
        penh = (Button) findViewById(R.id.penhome);
        cona = (Button) findViewById(R.id.cona);
        trya = (Button) findViewById(R.id.trya);
        tryhome = (Button) findViewById(R.id.tryhome);
        pena = (Button) findViewById(R.id.pena);


        //db ref to store home scores
        refcon = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(enter_team).child("Home Score");
        //db ref to store timline info
        reftl = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(enter_team).child("Timeline");
        //when the 2 button is clicked
        conh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v2) {
                //setting up / formating the timestamps
                final String timeStamp = new SimpleDateFormat("hh:mm:ss").format(new Date());
                //timestamp is the current time

                SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");

                Date d1 = null;
                Date d2 = null;

                try {
                    d2 = format.parse(timeStamp);
                    //"time" is retrieved from an intent and is the time when the match was started
                    d1 = format.parse(time);


                    long diff = d2.getTime() - d1.getTime();
                    //difference of current time and the start time the retrieve the time in game where the match event happened

                    //converting from milliseconds the hrs mins and secs.
                    long diffs = diff / 1000 % 60;
                    sec = diffs;
                    long diffm = diff / (60 * 1000) % 60;
                    min = diffm;
                    long diffh = diff / (60 * 60 * 1000) % 24;
                    hour = diffh;
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //how the info will be stored in the db
                reftl.push().setValue("Our Con      " + "    "+ hour + ":" + min + ":" + sec);
                //call the increment function
                increment();
                //set the value after the increment to the db
                refcon.setValue(home_s);
            }

            public void increment()
            {


                refcon = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(enter_team).child("Home Score");
                reftl = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(enter_team).child("Timeline");
                refcon.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            //incrementing the current value in the db vy 2
                            int total = dataSnapshot.getValue(Integer.class);
                            total = total + 2;
                            home_s = total;
                            //setting the value to the db
                            refcon.setValue(home_s);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

        });

        //follows same procedure as prev method however this time is for home penalties

        penh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                final String timeStamp = new SimpleDateFormat("hh:mm:ss").format(new Date());
                SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
                Date d1 = null;
                Date d2 = null;

                try {
                    d2 = format.parse(timeStamp);
                    d1 = format.parse(time);
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
                reftl.push().setValue("Our Pen      " + "    "+ hour + ":" + min + ":" + sec);
                increment();
                //call increment and set the value
                refcon.setValue(home_s);
            }

            public void increment()
            {
                refcon = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(enter_team).child("Home Score");
                reftl = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(enter_team).child("Timeline");
                refcon.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            int total = dataSnapshot.getValue(Integer.class);
                            total = total + 3;
                            //increase total by 3 for a penalty
                            home_s = total;
                            refcon.setValue(home_s);
                            //set the db to the value
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });
        //same as prev methods only this time with tries button
        tryhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                final String timestamp = new SimpleDateFormat("hh:mm:ss").format(new Date());
                SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
                Date d1 = null;
                Date d2 = null;
                try {
                    d2 = format.parse(timestamp);
                    d1 = format.parse(time);
                    long diff = d2.getTime() - d1.getTime();
                    long diffs = diff / 1000 % 60;
                    sec = diffs;
                    long diffm = diff / (60 * 1000) % 60;
                    min = diffm;
                    long diffh = diff / (60 * 60 * 1000) % 24;
                    hour = diffh;
                    //ms to match time

                } catch (Exception e) {
                    e.printStackTrace();
                }

                reftl.push().setValue("Our Try      " + "    "+ hour + ":" + min + ":" + sec);
                increment();
                //call increment function and set the value in the db.
                refcon.setValue(home_s);
            }

            public void increment()
            {

                refcon = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(enter_team).child("Home Score");
                reftl = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(enter_team).child("Timeline");
                refcon.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            int total = dataSnapshot.getValue(Integer.class);
                            total = total + 5;
                            //increase total vy 5 for tries
                            home_s = total;
                            //set the new value to the db
                            refcon.setValue(home_s);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

        });

        //same as prev methods this time with away scores
        refcona = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(enter_team).child("Away Score");

        cona.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v2) {
                final String timeStamp = new SimpleDateFormat("hh:mm:ss").format(new Date());
                SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");

                Date d1 = null;
                Date d2 = null;
                try {
                    d2 = format.parse(timeStamp);
                    d1 = format.parse(time);
                    long diff = d2.getTime() - d1.getTime();
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

                reftl.push().setValue("Opp Con      " + "    "+ hour + ":" + min + ":" + sec);
                increment();

                refcona.setValue(away_s);
            }
            public void increment()
            {
                refcona.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            int total = dataSnapshot.getValue(Integer.class);
                            total = total + 2;
                            //increase away scores by two
                            away_s = total;
                            refcona.setValue(away_s);
                            //set the new core total to the db
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        //same as prev method but with away tries.
        trya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                final String timeStamp = new SimpleDateFormat("hh:mm:ss").format(new Date());
                SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
                Date d1 = null;
                Date d2 = null;

                try {
                    d2 = format.parse(timeStamp);
                    d1 = format.parse(time);

                    long diff = d2.getTime() - d1.getTime();


                    long diffs = diff / 1000 % 60;
                    sec = diffs;
                    long diffm = diff / (60 * 1000) % 60;
                    min = diffm;
                    long diffh = diff / (60 * 60 * 1000) % 24;
                    hour = diffh;
                    //ms to current time in match
                } catch (Exception e) {
                    e.printStackTrace();
                }
                reftl.push().setValue("Opp Try      " + "    "+ hour + ":" + min + ":" + sec);
                increment();
                //call increment function
                refcona.setValue(away_s);
                //set this new value to the db
            }
            public void increment()
            {
                refcona.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            int total = dataSnapshot.getValue(Integer.class);
                            total = total + 5;
                            //increment by 5 for away tries
                            away_s = total;
                            refcona.setValue(away_s);
                            //set the new value in the db
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        //same as prev methods but with away pens.
        pena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                final String timeStamp = new SimpleDateFormat("hh:mm:ss").format(new Date());
                SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
                Date d1 = null;
                Date d2 = null;

                try {
                    d2 = format.parse(timeStamp);
                    d1 = format.parse(time);
                    long diff = d2.getTime() - d1.getTime();
                    long diffs = diff / 1000 % 60;
                    sec = diffs;
                    long diffm = diff / (60 * 1000) % 60;
                    min = diffm;
                    long diffh = diff / (60 * 60 * 1000) % 24;
                    hour = diffh;
                    //ms to curr match time
                } catch (Exception e) {
                    e.printStackTrace();
                }
                reftl.push().setValue("Opp Pen      " + "    "+ hour + ":" + min + ":" + sec);

                increment();
                //call the increment function
                refcona.setValue(away_s);
                //set the new value
            }

            public void increment()
            {
                refcona.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            int total = dataSnapshot.getValue(Integer.class);
                            total = total + 3;
                            // add the to the total for away tries
                            away_s = total;
                            refcona.setValue(away_s);
                            //set the new updated total value
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }


        });
        //decrement button setup for home tries
        c_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                //call the decrement method
                decrement();


            }

            public void decrement()
            {
                refcon.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            int total = dataSnapshot.getValue(Integer.class);
                            total = total - 2;
                            //get the current db value and subtract 2 (conversion) off it
                            home_s = total;
                            refcon.setValue(home_s);
                            //set the new db value
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        //home pens decrement button
        p_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {

                decrement();
                //when clicked call the decrement function.
            }

            public void decrement()
            {
                refcon.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            int total = dataSnapshot.getValue(Integer.class);
                            total = total - 3;
                            //take 3 off the total in the db
                            home_s = total;
                            refcon.setValue(home_s);
                            //uopdate the new db value
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }


        });
        //button for away conversion decrement
        c_away.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v2) {
                //call the dcrement function
                decrement();



            }

            public void decrement()
            {
                refcona.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            int total = dataSnapshot.getValue(Integer.class);
                            total = total - 2;
                            //get the current total and subtract 2
                            away_s = total;
                            refcona.setValue(away_s);
                            //set the updated db value
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });
        //set up the decrement button for away tries
        t_away.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                //call the decrement function
                decrement();
            }
            public void decrement()
            {
                refcona.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            int total = dataSnapshot.getValue(Integer.class);
                            //take 5 off the total value gotten in the db
                            total = total - 5;
                            away_s = total;
                            refcona.setValue(away_s);
                            //set the updated value to the db
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        //setup a decrement button for home conversions
        t_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                //call the decrement method
                decrement();
            }

            public void decrement()
            {
                refcon.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            int total = dataSnapshot.getValue(Integer.class);
                            total = total - 5;
                            //subtract 5 from the total retieved from the db
                            home_s = total;
                            refcon.setValue(home_s);
                            //set the new value in the db
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }
        });
        //set up the decrement button for away penalties
        p_away.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                //call the decrement method
                decrement();
            }

            public void decrement()
            {
                refcona.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            int total = dataSnapshot.getValue(Integer.class);
                            total = total - 3;
                            //subtract 3 from the value in the db
                            away_s = total;
                            refcona.setValue(away_s);
                            //set the new value in the db
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
    // prevents user from going back, and possibly removing some progress
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                //set up a warning message
                .setTitle("Warning")
                .setMessage("Are you sure you want to leave this page? Progress may be lost.")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                //clicking yes closes the program
                .setNegativeButton("No", null)
                .show();
    }
}
