package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class create_account extends AppCompatActivity {

    private String firstName, lastName, userName, passWord, role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }


    public void goBack(View view){
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }

    public void newAccount(View view) {
        EditText userText = (EditText)findViewById(R.id.usernameEnter);
        EditText passText = (EditText)findViewById(R.id.passwordEnter);
        EditText firstText = (EditText)findViewById(R.id.firstName);
        EditText lastText = (EditText)findViewById(R.id.lastName);

        //chooseAcc.setOnCheckedChangeListener(new OnCheckedChangeListener()) {
        //int id=chooseAcc.getCheckedRadioButtonId();
        //RadioButton rb=(RadioButton) findViewById(id);

        userName = userText.getText().toString();
        passWord = passText.getText().toString();
        firstName = firstText.getText().toString();
        lastName = lastText.getText().toString();
        //role = rb.getText().toString();

        if (userName.matches("") || passWord.matches("") || firstName.matches("") || lastName.matches("")) {
            Toast.makeText(this, "You need to fill all of your information in", Toast.LENGTH_SHORT).show();
            return; }
        else {
            Intent intent = new Intent(this, LogIn.class);
            startActivity(intent);
        }
    }
}
