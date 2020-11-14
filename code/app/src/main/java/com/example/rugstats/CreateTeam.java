package com.example.rugstats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateTeam extends AppCompatActivity {

    EditText mName, mCoach, mAge;
    Button mCreatebtn;
    private FirebaseAuth mAuth;
    private Toolbar bar;
    DatabaseReference ref;

    Team team;
    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_team);

        mAuth = FirebaseAuth.getInstance();

        bar = (Toolbar) findViewById(R.id.bar);
        setSupportActionBar(bar);
        getSupportActionBar().setTitle("Rugstat");

        //sets variable names to correct inputs
        mName = (EditText)findViewById(R.id.t_name);
        mCoach = (EditText) findViewById(R.id.t_coach);
        mAge = (EditText) findViewById(R.id.t_age);
        mCreatebtn = (Button) findViewById(R.id.t_button);

        team = new Team();

        ref = FirebaseDatabase.getInstance().getReference().child("Teams").child(currentuser);
        mCreatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //stores the inputs then pushes the data to firebase
                team.setName((mName.getText().toString().trim()));
                team.setCoach((mCoach.getText().toString().trim()));
                team.setAge((mAge.getText().toString().trim()));
                ref.push().setValue(team);
//sends user back to home screen
                Intent intent2 = new Intent(CreateTeam.this, MainActivity.class);
                startActivity(intent2);
            }
        });
    }
}
