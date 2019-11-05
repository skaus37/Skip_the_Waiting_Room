package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


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


        textView.setText("text you want to display "+email);


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