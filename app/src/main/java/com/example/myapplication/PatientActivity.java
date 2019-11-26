package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
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

        searchView = (SearchView) findViewById(R.id.searchView);
        listView = (ListView) findViewById(R.id.viewClinics);

        //list = new ArrayList<>();
        updateList();


    }

    public void updateList() {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        ArrayList<String> list = new ArrayList<>();
        //String clinicName;
        database.collection("users").whereEqualTo("accountType", "employee").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            //ArrayList<String> list = new ArrayList<>();
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        list.add(document.get("clinicName").toString());
                        System.out.println(document.get("clinicName").toString());
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
                // }

//            if (task.isSuccessful()) {
//                String currentUser;
//
//
//                //ArrayList<String> list = new ArrayList<>();
//
//                for (QueryDocumentSnapshot document : task.getResult()) {
//                    currentUser = document.getId();
//                    list.add(currentUser);
//
//                }
            }
        });
                //instantiate custom adapter
                ListClinicHandler adapter = new ListClinicHandler(list, this);
                listView.setAdapter(adapter);





    }


}
