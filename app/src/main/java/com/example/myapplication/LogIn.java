package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import java.util.LinkedList;
import java.util.List;


public class LogIn extends AppCompatActivity {


    private List<? extends UserInfo> providerData.;

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
        String firstName = user.getDisplayName();
        String role;
        List<? extends UserInfo> providerData.= user.getProviderData();


        textView.setText(providerData..get(0));


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