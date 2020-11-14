package com.example.rugstats;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class PopupMatch extends AppCompatActivity {
    //set up of variables to be used in the class
    int total_tw = 0;
    final String time = new SimpleDateFormat("hh:mm:ss").format(new Date());
    DatabaseReference db5,db1,db2,db3,db6,db7,ref,reflm,reftm,reftw,refft;
    TextView a;
    EditText b;
    Button enter;
    //get current user id
    String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupmatch);
        //set the view to this activity
        Intent intent = getIntent();
        final String curr_team = intent.getStringExtra(Intent.EXTRA_TEXT);
        //get the current team from the select team class

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int w = dm.widthPixels;
        int h = dm.heightPixels;
        getWindow().setLayout((int) (w * .8), (int) (h * .6));
        //set the layout wxh of the popup windon

        mAuth = FirebaseAuth.getInstance();
        b = (EditText)findViewById(R.id.textInputEditText);
        //input for opponent name
        enter = (Button) findViewById(R.id.startmatch);
        //button to confirm entry and start match.
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String curr_date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                //time set for the start match time

                //setting a founation for the database, prevents null errors when the database goes to query these locations

                db7 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(b.getText().toString().trim() + "      " + curr_date).child("Home Score");
                db7.setValue(0);
                db6 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(b.getText().toString().trim() + "      " + curr_date).child("Away Score");
                db6.setValue(0);
                ref = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(b.getText().toString().trim() + "      " + curr_date).child("Opposition 22").child("Unforced Turnover");
                ref.setValue(0);
                reflm = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(b.getText().toString().trim() + "      " + curr_date).child("Opposition 22").child("Linebreaks Made");
                reflm.setValue(0);
                reftm = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(b.getText().toString().trim() + "      " + curr_date).child("Opposition 22").child("Tackles Missed");
                reftm.setValue(0);
                reftw = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(b.getText().toString().trim() + "      " + curr_date).child("Opposition 22").child("Turnover Won");
                reftw.setValue(0);
                refft = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(b.getText().toString().trim() + "      " + curr_date).child("Opposition 22").child("Forced Turnover");
                refft.setValue(0);
                db5 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(b.getText().toString().trim() + "      " + curr_date).child("Our 22").child("Tackles Missed");
                db5.setValue(0);
                db1 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(b.getText().toString().trim() + "      " + curr_date).child("Total TM");
                db1.setValue(0);
                db2 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(b.getText().toString().trim() + "      " + curr_date).child("Total TW");
                db2.setValue(0);
                db3 = FirebaseDatabase.getInstance().getReference().child("Stats").child(currentuser).child(curr_team).child(b.getText().toString().trim() + "      " + curr_date).child("Total LM");
                db3.setValue(0);
                //set all the refs to 0

                Intent intent = new Intent(PopupMatch.this, PitchEvent.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA1",b.getText().toString().trim() + "      " +  curr_date);
                //create an intent with the openents name that you just typed in
                extras.putString("EXTRA2",curr_team);
                //intent current team
                extras.putString("EXTRA3",time);
                //intent the time for start match time
                intent.putExtras(extras);
                startActivity(intent);
                //start the pitch event activity and send all the intent information
                extras.putString("EXTRA1",b.getText().toString().trim() + "      " +  curr_date);
                extras.putString("EXTRA2",curr_team);
                intent.putExtras(extras);
            }
        });
    }
}
