package com.example.rugstats;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    //setup of buttons and toolbar
    private Toolbar bar;
    private Button mStart,mView,mTeamCreate;
    //strings to determing which activity is using the selectteam activity.
    String num2 = "2";
    String num1 = "1";

    @Override
    public void onStart() {
        super.onStart();
        // Check if the user is signed in.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //if the current user is not logged in, take them to the launch screen

        if(currentUser == null)
        {
            launch();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the view to the main page
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        bar = (Toolbar) findViewById(R.id.bar);
        setSupportActionBar(bar);
        getSupportActionBar().setTitle("Rugstat");
        //setup of toolbar


        //creating transparent buttons so the background can be seen underneath them
        mTeamCreate = (Button) findViewById(R.id.create_t);
        mTeamCreate.setVisibility(View.VISIBLE);
        mTeamCreate.setBackgroundColor(Color.TRANSPARENT);

        mTeamCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ct_intent = new Intent(MainActivity.this, CreateTeam.class);
                //launch creat team on a click
                startActivity(ct_intent);}}
        );
        //creating transparent buttons so the background can be seen underneath them

        mStart = (Button) findViewById(R.id.start_match);
        mStart.setVisibility(View.VISIBLE);
        mStart.setBackgroundColor(Color.TRANSPARENT);

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SelectTeam.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA1",num1);
                intent.putExtras(extras);
                //launch selectteam on a click using the string to destinguish what what activity to open after
                startActivity(intent);;}}
        );
        //creating transparent buttons so the background can be seen underneath them
        mView = (Button) findViewById(R.id.viewstats);
        mView.setVisibility(View.VISIBLE);
        mView.setBackgroundColor(Color.TRANSPARENT);

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this, SelectTeam.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA2",num2);
                intent2.putExtras(extras);
                //launch selectteam on a click using the string to destinguish what what activity to open after
                startActivity(intent2);;}}
        );
            }

    private void launch()
    {
        Intent launchIntent = new Intent(MainActivity.this, Launcher.class);
        startActivity(launchIntent);
        finish();
        //if this is called, the user is brought back to the launcher page
    }

    //creating an options dropdown
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }
    //add a logout button to the dropdown
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId() == R.id.logoutbtn)
        {
            //logout and return to launcher
            FirebaseAuth.getInstance().signOut();
            launch();
        }

        return true;
    }}
