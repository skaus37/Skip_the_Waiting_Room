package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class RateClinicUserView extends AppCompatActivity {
    public static final String clinicEmailIdentfier = "retrieveClinic";
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    String clinicEmail;
    String email;
    int rating;
    String comment="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_clinic_user_view);

        //initialize email
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        email = user.getEmail();

        clinicEmail = getIntent().getStringExtra(clinicEmailIdentfier);

        //initialize clinicName,
        /*add the following when creating this activity
        * Intent switchActivity = new Intent(this, RateClinicUserVeiw.class);
          switchActivity.putExtra( clinicEmailIdentfier, insert clinic email);
        *
        *
        * */



    }





    //making comment match what the user entered is done in another method
    public void submitReview (View view){
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
            database.collection("review").document(clinicEmail)
                    .collection(email).document("Review").set(docData).
                    addOnCompleteListener((@NonNull Task<Void> task) -> {

                        if (!task.isSuccessful()){
                            Toast.makeText(this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else{
                            //take doc snapshot to get values of reviewNumber, sumScore
                            database.collection("review").document(email).get()
                                    .addOnCompleteListener((@NonNull Task<DocumentSnapshot> task2)-> {
                                if (task.isSuccessful()) {
                                    int revNum = Integer.parseInt(task2.getResult().get("reviewNumber").toString());
                                    int sumNum = Integer.parseInt(task2.getResult().get("sumScore").toString());
                                    Map<String, Object> reviewTotal = new HashMap<>();
                                    reviewTotal.put("reviewNumber", revNum);
                                    reviewTotal.put("sumScore", sumNum);

                                    database.collection("review").document(clinicEmail)
                                            .set(reviewTotal, SetOptions.merge()).addOnCompleteListener((@NonNull Task<Void> task3) -> {
                                        if (!task3.isSuccessful()){
                                            Toast.makeText(this, task3.getException().toString(), Toast.LENGTH_SHORT).show();
                                            return;

                                        }
                                    });
                                }
                                else{
                                    Toast.makeText(this, task2.getException().toString(), Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            });






                        }
                    });

            database.collection("review").document(clinicEmail)
                    .collection("review").document("Review").set(docData, SetOptions.merge()).
                    addOnCompleteListener((@NonNull Task<Void> task2) -> {

                        if (!task2.isSuccessful()){
                            Toast.makeText(this, task2.getException().toString(), Toast.LENGTH_SHORT).show();
                            return;

                        }
                    });
        }
    }
}
