package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class PatientActivity extends AppCompatActivity {
    SearchView searchView;
    ListView listView;
    //ArrayList<String> list;
    ArrayAdapter<String > adapter;
    FirebaseAuth mAuth;
    FirebaseUser user;
    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        listView = (ListView) findViewById(R.id.viewClinics);
        searchView = (SearchView) findViewById(R.id.searchView);
        updateList();

//        Button view = (Button) findViewById(R.id.viewClinicButton);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //viewClinic();
//            }
//        });


    }

    private void viewClinic() {
        Intent i = new Intent(this, ClinicDetails.class);
        startActivity(i);
    }

    public void updateList() {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        ArrayList<String> list = new ArrayList<>();


        database.collection("users").whereEqualTo("accountType", "employee").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        list.add(document.get("clinicName").toString());

                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }



            }
        });
                //instantiate custom adapter
                ListClinicHandler adapter = new ListClinicHandler(list, this);
                listView.setAdapter(adapter);





    }


}
