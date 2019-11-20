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
    FirebaseUser user;
    ArrayList<String> serviceArray;
    ListView serviceListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_edit_service);
        serviceListView = (ListView) findViewById(R.id.clincList);

        updateList();
        employeeList();
    }

    public void updateList() {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        database.collection("services").get().addOnCompleteListener((@NonNull Task<QuerySnapshot> task) -> {
            if (task.isSuccessful()) {
                String currentUser;

                ArrayList<String> accountArray = new ArrayList<>();

                for (QueryDocumentSnapshot document : task.getResult()) {
                    currentUser = document.getId();
                    accountArray.add(currentUser);

                }


                //instantiate custom adapter
                ListServiceHandler adapter = new ListServiceHandler(accountArray, this);
                ListView lView = (ListView) findViewById(R.id.serviceList);
                //handle listview and assign adapter

                lView.setAdapter(adapter);
            }
        });
    }
    public void employeeList () {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String email = user.getEmail();
        database.collection("users").document(email).collection("services").get().addOnCompleteListener((@NonNull Task<QuerySnapshot> task1) -> {
            if (task1.isSuccessful()) {
                String currentUser1;
                serviceArray = new ArrayList<>();
                //make it impossible for the admin to deleate admin account
                for (QueryDocumentSnapshot document : task1.getResult()) {
                    currentUser1 = document.getId();
                    serviceArray.add(currentUser1);


                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, serviceArray);
                serviceListView.setAdapter(arrayAdapter);

                    }
                });
            }



}
