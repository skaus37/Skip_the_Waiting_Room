package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditServiceActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service);

        //set role options

    }
    public void addService(View view){
        EditText serviceText = (EditText)findViewById(R.id.serviceNameText);
        String service = serviceText.getText().toString();
        Spinner spinner = (Spinner)findViewById(R.id.roleOptions);
        TextView spinnerView = (TextView)spinner.getSelectedView();
        String role = spinnerView.getText().toString();



        String serviceVar = serviceText.getText().toString();

        //add if statements similar to that in create_account.java to check if the name box is fill out
        if (serviceVar.matches("")) {
            Toast.makeText(this, "You must enter a service name", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            Map<String, Object> docData = new HashMap<>();
            //docData.put("role", role);
            docData.put("Name", service);
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            //add on complete lister...see create_account.java code
            db.collection("services").document(service).set(docData);

        }


    }



}
