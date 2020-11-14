package com.example.rugstats;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SelectMatch extends AppCompatActivity {
    private static final String TAG = "SelectMatch";


    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    private String userID;
    private ListView mListView;




    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_match);

//Get the selected team
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String curr_team = extras.getString("EXTRA2");

        mListView = (ListView) findViewById(R.id.matchlist);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getInstance().getReference("Stats").child(currentuser).child(curr_team);
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mListView = (ListView) findViewById(R.id.matchlist);

//Navigate to view stats for correct match and send the selected match and team through too
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String val = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(SelectMatch.this, ViewStats.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA1", val);
                extras.putString("EXTRA2", curr_team);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }


    private void showData(DataSnapshot dataSnapshot) {
        ArrayList<String> array = new ArrayList<>();
        for(DataSnapshot ds : dataSnapshot.getChildren()){

            ArrayAdapter adapter1 = new ArrayAdapter(this,android.R.layout.simple_list_item_1, array);

            array.add(String.valueOf(ds.getKey()));
            adapter1.notifyDataSetChanged();
            mListView.setAdapter(adapter1);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }



}
