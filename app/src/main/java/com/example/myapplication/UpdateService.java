package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateService extends AppCompatActivity {

    Button update;
    Button delete;
    Button back;
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    int position;
    List<String> list;
    EditText name;

    String role;
    String service;
    EditText roleEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        update = (Button) findViewById(R.id.updateBtn);
        delete = (Button) findViewById(R.id.deleteBtn);
        back = (Button) findViewById(R.id.goBack);

        name = (EditText) findViewById(R.id.editTextName);


        roleEnter = (EditText) findViewById(R.id.editRole);

        service = name.getText().toString();


        Bundle b = getIntent().getExtras();
        if (b != null) {
            list = b.getStringArrayList("KEY");
            position = b.getInt("position");
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String id = list.get(position);
                role = roleEnter.getText().toString();
                service = name.getText().toString();

                if((role.toLowerCase().trim().equals("nurse") ||
                        role.toLowerCase().trim().equals("doctor") ||
                        role.toLowerCase().trim().equals("staff"))&&(!name.equals(null))){
                    Map<String, Object> docData = new HashMap<>();
                    docData.put("role", role);
                    docData.put("Name", service);


                    //add on complete lister...see create_account.java code
                    database.collection("services").document(id).delete();
                    database.collection("services").document(service).set(docData);
                    goBack();
                } else {
                    toast();
                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseFirestore.getInstance();
                String id = list.get(position);
                database.collection("services").document(id).delete();
                list.remove(position);
                goBack();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

    }


    public void goBack(){
        Intent i = new Intent(this, EditServiceActivity.class);
        startActivity(i);
    }

    public void toast(){
        Toast.makeText(this, "You must enter valid service and role", Toast.LENGTH_SHORT).show();
        return;
    }



}
