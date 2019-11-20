package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class EmployeeEditService extends AppCompatActivity {


    //
    Spinner optionalService;
    String selectedService;
    FirebaseAuth mAuth;
    FirebaseUser user = mAuth.getCurrentUser();
    String email = user.getEmail();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_edit_service);
        updateList();
    }

    public void updateList(){
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        optionalService = findViewById(R.id.spinner);

        database.collection("services").get().addOnCompleteListener((@NonNull Task<QuerySnapshot> task) -> {
            if (task.isSuccessful()) {
                String currentUser;

                ArrayList<String> accountArray = new ArrayList<>();

                for (QueryDocumentSnapshot document : task.getResult()) {
                    currentUser = document.getId();
                    accountArray.add(currentUser);

                }



                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, accountArray);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                optionalService.setAdapter(adapter);
            }
        });
    }


/*
    public void addServiceButton(View view){
        optionalService = findViewById(R.id.spinner);//i should probally just only do this once
        selectedService = String.valueOf(optionalService.getSelectedItem());

        //add selectedService to database
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection("users").document(email).get().addOnCompleteListener((@NonNull Task<DocumentSnapshot> task) -> {
            if (task.isSuccessful()) {
                if()

            }
        });
        //call an update listView method
    }*/
}
