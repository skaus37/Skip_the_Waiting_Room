package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class WorkingHours extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String mon, tues, wed, thurs, fri, sat, sun;
    private FirebaseFirestore database;

    private EditText editMon, editTues, editWed, editThurs, editFri, editSat, editSun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_hours);

        editMon = (EditText) findViewById(R.id.editMon);
        editTues = (EditText) findViewById(R.id.editTues);
        editWed = (EditText) findViewById(R.id.editWednes);
        editThurs = (EditText) findViewById(R.id.editThurs);
        editFri = (EditText) findViewById(R.id.editFri);
        editSat = (EditText) findViewById(R.id.editSat);
        editSun = (EditText) findViewById(R.id.editSun);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        database = FirebaseFirestore.getInstance();
        Task<DocumentSnapshot> users = database.collection("users").document(user.getEmail()).get().addOnCompleteListener((@NonNull Task<DocumentSnapshot> task2) -> {
            DocumentSnapshot document = task2.getResult();
            if (document.exists()){
                editMon.setText(task2.getResult().get("mon").toString(), TextView.BufferType.EDITABLE);
                editTues.setText(task2.getResult().get("tues").toString(), TextView.BufferType.EDITABLE);
                editWed.setText(task2.getResult().get("wed").toString(), TextView.BufferType.EDITABLE);
                editThurs.setText(task2.getResult().get("thurs").toString(), TextView.BufferType.EDITABLE);
                editFri.setText(task2.getResult().get("fri").toString(), TextView.BufferType.EDITABLE);
                editSat.setText(task2.getResult().get("sat").toString(), TextView.BufferType.EDITABLE);
                editSun.setText(task2.getResult().get("sun").toString(), TextView.BufferType.EDITABLE);
            }
        });



        Button updateWorkButton = findViewById(R.id.updateWorkHrs);
        updateWorkButton.setOnClickListener((View v)->{
            update(v);
        });
    }

    private void update(View v) {

        mon = editMon.getText().toString();
        tues = editTues.getText().toString();
        wed = editWed.getText().toString();
        thurs = editThurs.getText().toString();
        fri = editFri.getText().toString();
        sat = editSat.getText().toString();
        sun = editSun.getText().toString();

        if(mon.matches("")||tues.matches("")||wed.matches("")||thurs.matches("")||fri.matches("")||sat.matches("")||sun.matches("")){
            Toast.makeText(this, "You must fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        } else{
            FirebaseUser user = mAuth.getCurrentUser();

            Map<String, Object> docData = new HashMap<>();
            docData.put("mon", mon);
            docData.put("tues", tues);
            docData.put("wed", wed);
            docData.put("thurs", thurs);
            docData.put("fri", fri);
            docData.put("sat",sat);
            docData.put("sun",sun);

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("users").document(user.getEmail()).update(docData);

            finish();
            Intent i = new Intent(this, EmployeeActivity.class);
            startActivity(i);
        }
    }
}
