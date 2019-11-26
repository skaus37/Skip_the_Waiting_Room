package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

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
    ArrayAdapter<String > a, b;
    ArrayList<String> listName;
    ArrayList<String> listAddress;
    FirebaseAuth mAuth;
    FirebaseUser user;

    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        Context context = this;


        listView = (ListView) findViewById(R.id.viewClinics);
        searchView = (SearchView) findViewById(R.id.searchView);
        updateList();

        a=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listAddress);
        //b=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listName);
        listView.setAdapter(a);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                if(listAddress.contains(text) ) {

                    a.getFilter().filter(text);

                }else{
                    Toast.makeText(PatientActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }
            public boolean onQueryTextChange(String newText) {

                a.getFilter().filter(newText);

                //b.getFilter().filter(newText);
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(context, ClinicDetails.class);
                Bundle bu = new Bundle();
                bu.putString("name", listAddress.get(position).substring(0, listAddress.get(position).indexOf("\n")).trim());
                i.putExtras(bu);
                context.startActivity(i);
            }
        });



            }



    public void updateList() {
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        listName = new ArrayList<>();
        listAddress = new ArrayList<>();


        database.collection("users").whereEqualTo("accountType", "employee").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        listName.add(document.get("clinicName").toString());
                        listAddress.add(" "+document.get("clinicName").toString()+" \n "+document.get("address").toString());

                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }



            }
        });
                //instantiate custom adapter
//                ListClinicHandler adapter = new ListClinicHandler(listName, this);
//                listView.setAdapter(adapter);





    }


}
