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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class EmployeeActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        TextView welcomeEmployee = (TextView) findViewById(R.id.welcomeEmployee);
        FirebaseAuth mAuth;

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();
        String email = user.getEmail();


        FirebaseFirestore database = FirebaseFirestore.getInstance();


        database.collection("users").document(email).get().addOnCompleteListener((@NonNull Task<DocumentSnapshot> task)-> {
            if (task.isSuccessful()) {
                String firstName = task.getResult().get("firstName").toString();
                String account = task.getResult().get("accountType").toString();
                welcomeEmployee.setText("Welcome  " + firstName +  "You are a " + account);
            }
        });

        Button updateWorkButton = (Button) findViewById(R.id.workHrs);
        updateWorkButton.setOnClickListener((View v)->{
            updateWork(v);
        });
    }

    private void updateWork(View v) {
        Intent i = new Intent(this, WorkingHours.class);
        startActivity(i);
    }


    //edit Service Onclick
    public void openServiceActivity (View view){
        Intent intent = new Intent(this, EmployeeEditService.class);
        startActivity(intent);

    }






}
