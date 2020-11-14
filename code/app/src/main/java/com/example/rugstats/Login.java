package com.example.rugstats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
public class Login extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextInputEditText mEmail;
    private TextInputEditText mPw;
    private Button mLoginbtn;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        mEmail = (TextInputEditText) findViewById(R.id.l_email);
        mPw = (TextInputEditText) findViewById(R.id.l_pw);
        mLoginbtn = (Button) findViewById(R.id.l_button);

        mLoginbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //take in email and password
                String email = mEmail.getText().toString();
                String password = mPw.getText().toString();

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password))
                {
                    loginUser(email,password);
                }
            }
        });
    }

    private void loginUser(String email, String password)

    {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //Check if valid

                if(task.isSuccessful())
                {
                    //confirm login msg
                    Toast.makeText(Login.this, "Login Successful",Toast.LENGTH_SHORT).show();
                    Intent mainIntent = new Intent(Login.this, MainActivity.class);
                    startActivity(mainIntent);
                    finish();

                }
                else {
                    // login error msg
                    Toast.makeText(Login.this, "Invalid Login",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}