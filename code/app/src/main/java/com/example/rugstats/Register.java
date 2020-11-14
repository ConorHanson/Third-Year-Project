package com.example.rugstats;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;

public class Register extends AppCompatActivity {
    //set up a all the variables used in this class
    EditText mName, mEmail, mPw;
    Button mCreatebtn;
    private DatabaseReference db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //set the activity view the register

        mAuth = FirebaseAuth.getInstance();
        //where the accound information is inputted
        mName = (EditText) findViewById(R.id.reg_name);
        mEmail = (EditText) findViewById(R.id.reg_email);
        mPw = (EditText) findViewById(R.id.reg_pw);
        mCreatebtn = (Button) findViewById(R.id.t_button);


        mCreatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //when the button is clicked get the edit text values and convert the mto strings
                String name = mName.getText().toString();
                String email = mEmail.getText().toString();
                String password = mPw.getText().toString();
                reg_user(name,email,password);
                //call the reg user method with these strings
            }
        });
    }

    private  void reg_user(final String name,final String email, String password)
            //built in firebase method to create and account the the database
    {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    //log user in
                    FirebaseUser curr_user = FirebaseAuth.getInstance().getCurrentUser();
                    String userid = curr_user.getUid();
                    //store user info and db
                    db = FirebaseDatabase.getInstance().getReference().child("User").child(userid);

                    //Send the users details to firebase
                    HashMap<String, String> uMap = new HashMap<>();
                    uMap.put("name",name);
                    uMap.put("email", email);

                    db.setValue(uMap);

                    Intent mainIntent = new Intent(Register.this, MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                    //start main activity


                }
                else
                {
                    Toast.makeText(Register.this, "Error", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}
