package com.example.rugstats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SelectTeam extends AppCompatActivity {
    private static final String TAG = "SelectTeam";


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
        setContentView(R.layout.select_team);

        mListView = (ListView) findViewById(R.id.listview);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getInstance().getReference("Teams").child(currentuser);
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String one = extras.getString("EXTRA1");
        final String two = extras.getString("EXTRA2");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mListView = (ListView) findViewById(R.id.listview);

        if (one != null) {

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent ct_intent = new Intent(SelectTeam.this, PopupMatch.class);
                    String curr_team = (String) parent.getItemAtPosition(position);
                    Intent intent = new Intent(SelectTeam.this, PopupMatch.class);
                    intent.putExtra(Intent.EXTRA_TEXT, curr_team);
                    startActivity(intent);
                }
            });
//Navigate to select match and send name of selected team
        } else {
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String curr_team = (String) parent.getItemAtPosition(position);
                    Intent intent = new Intent(SelectTeam.this, SelectMatch.class);
                    Bundle extras = new Bundle();
                    extras.putString("EXTRA2", curr_team);
                    intent.putExtras(extras);
                    startActivity(intent);
                }
            });
        }
    }
//populate list
    private void showData(DataSnapshot dataSnapshot) {
        ArrayList<String> array = new ArrayList<>();
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            Team mTeam = new Team();
            mTeam.setName(ds.child("name").getValue(String.class));

            Log.d(TAG, "showData: name: " + mTeam.getName());


            array.add(mTeam.getName());

            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, array);
            mListView.setAdapter(adapter);
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
