package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class EmployeeEditService extends AppCompatActivity {


    //
    Spinner optionalService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_edit_service);
        updateList();
    }

    public void updateList(){
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        optionalService = (Spinner) findViewById(R.id.spinner);

        database.collection("services").get().addOnCompleteListener((@NonNull Task<QuerySnapshot> task) -> {
            if (task.isSuccessful()) {
                String currentUser;

                ArrayList<String> accountArray = new ArrayList<>();

                for (QueryDocumentSnapshot document : task.getResult()) {
                    currentUser = document.getId();
                    accountArray.add(currentUser);

                }




                //instantiate custom adapter
                //ListServiceHandler adapter = new ListServiceHandler(accountArray, this);
                //ListView lView = (ListView) findViewById(R.id.serviceList);

                //add stuff to spinner

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, accountArray);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                optionalService.setAdapter(adapter);
            }
        });
}   }
