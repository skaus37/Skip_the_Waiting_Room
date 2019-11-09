package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditServiceActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service);

        updateListView();

        //set role options

    }

    public void addService(View view) {
        EditText serviceText = (EditText) findViewById(R.id.serviceNameText);
        String service = serviceText.getText().toString();
        Spinner spinner = (Spinner) findViewById(R.id.roleOptions);
        TextView spinnerView = (TextView) spinner.getSelectedView();
        String role = spinnerView.getText().toString();


        String serviceVar = serviceText.getText().toString();

        //add if statements similar to that in create_account.java to check if the name box is fill out
        if (serviceVar.matches("")) {
            Toast.makeText(this, "You must enter a service name", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Map<String, Object> docData = new HashMap<>();
            //docData.put("role", role);
            docData.put("Name", service);
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            //add on complete lister...see create_account.java code
            db.collection("services").document(service).set(docData);

        }
    }

        FirebaseFirestore database = FirebaseFirestore.getInstance();
        public void updateListView() {
            //ArrayList<String> accountArray;


            database.collection("services").get().addOnCompleteListener((@NonNull Task<QuerySnapshot> task) -> {
                if (task.isSuccessful()) {
                    String currentUser;

                    ArrayList<String> accountArray = new ArrayList<>();

                    for (QueryDocumentSnapshot document : task.getResult()){
                        currentUser = document.getId();
                        accountArray.add(currentUser);

                    }

                    ListView userListView = (ListView) findViewById(R.id.listServices);
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                            (this, android.R.layout.simple_list_item_1, accountArray);
                    userListView.setAdapter(arrayAdapter);

                }
            });


        }

}