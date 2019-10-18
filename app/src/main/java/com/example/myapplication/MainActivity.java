package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private String firstName, lastName, username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createAccountClick(View view) {
        Intent intent = new Intent (this, create_account.class);
        startActivity(intent);
    }

    public void logInClick(View view){

    }

}
