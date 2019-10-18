package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String userName, passWord;

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
        EditText userText = (EditText)findViewById(R.id.username);
        EditText passText = (EditText)findViewById(R.id.password);
        userName = userText.getText().toString();
        passWord = passText.getText().toString();
        if (userName.matches("") || passWord.matches("")) {
            Toast.makeText(this, "You need to enter a username and a password", Toast.LENGTH_SHORT).show();
            return; }

    }

}
