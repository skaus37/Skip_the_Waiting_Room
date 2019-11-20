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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class MainActivity extends AppCompatActivity {

    private String email, passWord;

    private FirebaseAuth mAuth;
    private FirebaseFirestore database;

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
                                //if the admin logs on they get brought to the admin page
                                if(email.equals( "admin@gmail.com")){
                                    Intent intent = new Intent (this, admin_activity.class);
                                    startActivity(intent);
                                }




                                else{
                                    database = FirebaseFirestore.getInstance();
                                    database.collection("users").document(email).get().addOnCompleteListener((@NonNull Task<DocumentSnapshot> task2)->{
                                        if (task.isSuccessful()) {
                                            DocumentSnapshot document = task2.getResult();
                                            if (document.exists()){

                                                //if the user logging in is a employee go to employee activity
                                                String account = task2.getResult().get("accountType").toString();
                                                String profile = task2.getResult().get("updated").toString();

                                                if (account.equals("employee") && profile.equals("no")){
                                                    // Start employee activity
                                                    Intent intent = new Intent(this, ProfileActivity.class);
                                                    startActivity(intent);
                                                } else if(profile.equals("yes")){
                                                    System.out.println(":(");
                                                    Intent intent = new Intent(this, EmployeeActivity.class);
                                                    startActivity(intent);

                                                } else{
                                                    Intent intent = new Intent (this, LogIn.class);
                                                    startActivity(intent);

                                                }


                                                //else go to patient



                                            }
                                            else{
                                                Toast.makeText(this, "The following user was deleted", Toast.LENGTH_SHORT).show();
                                            }


                                        }
                                    });






                                }

                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(this, task.getException().toString(), Toast.LENGTH_SHORT).show();


                            }

                            // ...

                    });


        }/*
        else{
            Toast.makeText(this, "You need to enter a valid username and a password", Toast.LENGTH_SHORT).show();
        }*/
    }
}
