package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;


public class LogIn extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

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