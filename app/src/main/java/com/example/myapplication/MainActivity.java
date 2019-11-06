package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private String email, passWord;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //insure that if you are on this page you are signed out
        FirebaseAuth.getInstance().signOut();

    }




    public void createAccountClick(View view) {
        Intent intent = new Intent (this, create_account.class);
        startActivity(intent);
    }

    public void logInClick(View view){
        EditText emailText = (EditText)findViewById(R.id.email_Login);
        EditText passText = (EditText)findViewById(R.id.password);
        email = emailText.getText().toString();
        passWord = passText.getText().toString();
        if (email.matches("") || passWord.matches("")) {
            Toast.makeText(this, "You need to enter a username and a password", Toast.LENGTH_SHORT).show();
            return; }
        else{
            mAuth.signInWithEmailAndPassword(email, passWord)
                    .addOnCompleteListener((@NonNull Task<AuthResult> task)->{


                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                FirebaseUser user = mAuth.getCurrentUser();

                                Intent intent = new Intent (this, LogIn.class);
                                startActivity(intent);

                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                return ;

                            }

                            // ...

                    });


        }/*
        else{
            Toast.makeText(this, "You need to enter a valid username and a password", Toast.LENGTH_SHORT).show();
        }*/
    }
}
