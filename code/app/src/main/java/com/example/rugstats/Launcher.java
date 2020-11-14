package com.example.rugstats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Launcher extends AppCompatActivity {
    //set up of buttons
    private Button mLoginbtn;
    private Button mRegbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        //set the view as the launcher activity

        mRegbtn = (Button) findViewById(R.id.reg);

        mRegbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if clicked launch the register class
                Intent reg_intent = new Intent(Launcher.this, Register.class);
                startActivity(reg_intent);
            }
        });


        mLoginbtn = (Button) findViewById(R.id.loginbtn);
        //if clicked launch the login class
        mLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login_intent = new Intent(Launcher.this, Login.class);
                startActivity(login_intent);
            }


        });
    }
}
