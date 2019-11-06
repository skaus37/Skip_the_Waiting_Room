package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class LogIn extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);


        setContentView(R.layout.log_in);
        TextView textView = (TextView) findViewById(R.id.welcome);
        //FirebaseUser user = getIntent()("USER");
        FirebaseAuth mAuth;

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        String email = user.getEmail();


        FirebaseFirestore database = FirebaseFirestore.getInstance();


        database.collection("users").document(email).get().addOnCompleteListener((@NonNull Task<DocumentSnapshot> task)->{
            if (task.isSuccessful()) {
                String firstName = task.getResult().get("firstName").toString();
                String lastName = task.getResult().get("lastName").toString();
                String accountType = task.getResult().get("accountType").toString();

                textView.setText("Welcome "+firstName +" "+lastName);
                textView.setText("Account Type: "+ accountType);
            }
        });



        /*
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users"+"/"+email);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //String name = dataSnapshot.getValue(firstName.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }
        */



        Button logOutButton = findViewById(R.id.logOutButton);
        logOutButton.setOnClickListener((View v)->{
            LogOutClick(v);
        });



    }
    public void LogOutClick(View view){
        //insert log out code
        FirebaseAuth.getInstance().signOut();

        //goes back to first page
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }


}