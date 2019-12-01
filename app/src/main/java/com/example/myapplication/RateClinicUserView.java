package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RateClinicUserView extends AppCompatActivity {
    public static final String clinicEmailIdentfier = "retrieveClinic";
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    String clinicEmail;
    String email;
    int rating;
    String comment="";
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_clinic_user_view);
        TextView text = (TextView) findViewById(R.id.textView7);

        //initialize email
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        email = user.getEmail();
        Bundle extras = getIntent().getExtras();
        name = extras.getString("clinicName");

        database.collection("users").whereEqualTo("clinicName", name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        clinicEmail = document.getId();



                    }
                }
            }
        });




    }




    //making comment match what the user entered is done in another method
    public void submitReview (View view){
        Toast.makeText(this, "Review Submitted", Toast.LENGTH_SHORT).show();

        RatingBar simpleRatingBar = (RatingBar) findViewById(R.id.ratingBar); // initiate a rating bar
        rating = simpleRatingBar.getNumStars(); // get total number of stars of rating bar
        EditText textComment = findViewById(R.id.comment);
        comment = textComment.getText().toString();



        if(comment.matches("")){
            Toast.makeText(this, "You must fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            //add review
            Map<String, Object> docData = new HashMap<>();

            docData.put("Comment", comment);
            docData.put("Rating", rating);
            docData.put("UserEmail",email);
            DateFormat df = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
            String date = df.format(Calendar.getInstance().getTime());
            database.collection("review").document(clinicEmail)
                    .collection("Reviews").document(date).set(docData);//.




        }

        finish();
    }

}
