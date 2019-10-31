package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class create_account extends AppCompatActivity {

    private String firstName, lastName, userName, passWord, role ,email;

    private FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);




        // ...
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        Button createAccountButton = findViewById(R.id.createAccountButton);
        createAccountButton.setOnClickListener((View v)->{
            newAccount(v);
        });

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
        EditText emailText = (EditText)findViewById(R.id.emailEnter);

        //chooseAcc.setOnCheckedChangeListener(new OnCheckedChangeListener()) {
        //int id=chooseAcc.getCheckedRadioButtonId();
        //RadioButton rb=(RadioButton) findViewById(id);

        userName = userText.getText().toString();
        passWord = passText.getText().toString();
        firstName = firstText.getText().toString();
        lastName = lastText.getText().toString();
        email = emailText.getText().toString();
            //role = rb.getText().toString();

        if (userName.matches("") || passWord.matches("") || firstName.matches("") || lastName.matches("") || email.matches( "")) {
            Toast.makeText(this, "You need to fill all of your information in", Toast.LENGTH_SHORT).show();
            return; }
        else {

            //do stuff
            mAuth.createUserWithEmailAndPassword(email, passWord).addOnCompleteListener((@NonNull Task<AuthResult> task)-> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            //if it works a new email should show up in the firebase authentication page

                            //if sign in is success go to next page
                            Intent intent = new Intent(this, LogIn.class);
                            startActivity(intent);


                        } else {
                            // If sign in fails, display a message to the user.
                            //output error msg
                            Toast.makeText(this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            return ;

                        }
                    });


        }
    }
}
