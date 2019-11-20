package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class WorkingHours extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private FirebaseAuth mAuth;
    private String mon, tues, wed, thurs, fri, sat, sun;
    private int clock;
    private boolean openOrClose;
    private String monOpen, tuesOpen, wedOpen, thursOpen, friOpen, satOpen, sunOpen, monClose, tuesClose, wedClose, thursClose, friClose, satClose, sunClose;
    private FirebaseFirestore database;

    private EditText editMon, editTues, editWed, editThurs, editFri, editSat, editSun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_hours);

        // Buttons to add opening and closing hours
        Button open1 = (Button) findViewById(R.id.open1);
        Button close1 = (Button) findViewById(R.id.close1);
        Button open2 = (Button) findViewById(R.id.open2);
        Button close2 = (Button) findViewById(R.id.close2);
        Button open3 = (Button) findViewById(R.id.open3);
        Button close3 = (Button) findViewById(R.id.close3);
        Button open4 = (Button) findViewById(R.id.open4);
        Button close4 = (Button) findViewById(R.id.close4);
        Button open5 = (Button) findViewById(R.id.open5);
        Button close5 = (Button) findViewById(R.id.close5);
        Button open6 = (Button) findViewById(R.id.open6);
        Button close6 = (Button) findViewById(R.id.close6);
        Button open7 = (Button) findViewById(R.id.open7);
        Button close7 = (Button) findViewById(R.id.close7);

        // Opening the clock on button click
        open1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
                clock = 0;
                openOrClose = true;
            }
        });

        close1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
                clock = 0;
                openOrClose = false;
            }
        });

        open2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
                clock = 1;
                openOrClose = true;
            }
        });

        close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
                clock = 1;
                openOrClose = false;
            }
        });

        open3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
                clock = 2;
                openOrClose = true;
            }
        });

        close3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
                clock = 2;
                openOrClose = false;
            }
        });

        open4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
                clock = 3;
                openOrClose = true;
            }
        });

        close4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
                clock = 3;
                openOrClose = false;
            }
        });

        open5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
                clock = 4;
                openOrClose = true;
            }
        });

        close5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
                clock = 4;
                openOrClose = false;
            }
        });

        open6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
                clock = 5;
                openOrClose = true;
            }
        });

        close6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
                clock = 5;
                openOrClose = false;
            }
        });

        open7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
                clock = 6;
                openOrClose = true;
            }
        });

        close7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
                clock = 6;
                openOrClose = false;
            }
        });


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

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Storing database values into strings
        String current =  editMon.getText().toString();
        if (current.contains(" - ")) {
            String[] parts = current.split(" - ", 2);
            monOpen = parts[0];
            monClose = parts[1];
        }

        current =  editTues.getText().toString();
        if (current.contains(" - ")) {
            String[] parts = current.split(" - ", 2);
            tuesOpen = parts[0];
            tuesClose = parts[1];
        }

        current =  editWed.getText().toString();
        if (current.contains(" - ")) {
            String[] parts = current.split(" - ", 2);
            wedOpen = parts[0];
            wedClose = parts[1];
        }

        current =  editThurs.getText().toString();
        if (current.contains(" - ")) {
            String[] parts = current.split(" - ", 2);
            thursOpen = parts[0];
            thursClose = parts[1];
        }

        current =  editFri.getText().toString();
        if (current.contains(" - ")) {
            String[] parts = current.split(" - ", 2);
            friOpen = parts[0];
            friClose = parts[1];
        }

        current =  editSat.getText().toString();
        if (current.contains(" - ")) {
            String[] parts = current.split(" - ", 2);
            satOpen = parts[0];
            satClose = parts[1];
        }

        current =  editSun.getText().toString();
        if (current.contains(" - ")) {
            String[] parts = current.split(" - ", 2);
            sunOpen = parts[0];
            sunClose = parts[1];
        }

        // Writing new values into EditText
        if ((clock == 0) && (openOrClose == true)){
            editMon = (EditText) findViewById(R.id.editMon);
            monOpen = (hourOfDay + ":" + minute);
            editMon.setText(monOpen + " - " + monClose);
        }

        if ((clock == 0) && (openOrClose == false)){
            editMon = (EditText) findViewById(R.id.editMon);
            monClose = (hourOfDay + ":" + minute);
            editMon.setText(monOpen + " - " + monClose);
        }

        if ((clock == 1) && (openOrClose == true)){
            editTues = (EditText) findViewById(R.id.editTues);
            tuesOpen = (hourOfDay + ":" + minute);
            editTues.setText(tuesOpen + " - " + tuesClose);
        }

        if ((clock == 1) && (openOrClose == false)){
            editTues = (EditText) findViewById(R.id.editTues);
            tuesClose = (hourOfDay + ":" + minute);
            editTues.setText(tuesOpen + " - " + tuesClose);
        }

        if ((clock == 2) && (openOrClose == true)){
            editWed = (EditText) findViewById(R.id.editWednes);
            wedOpen = (hourOfDay + ":" + minute);
            editWed.setText(wedOpen + " - " + wedClose);
        }

        if ((clock == 2) && (openOrClose == false)){
            editWed = (EditText) findViewById(R.id.editWednes);
            wedClose = (hourOfDay + ":" + minute);
            editWed.setText(wedOpen + " - " + wedClose);
        }

        if ((clock == 3) && (openOrClose == true)){
            editThurs = (EditText) findViewById(R.id.editThurs);
            thursOpen = (hourOfDay + ":" + minute);
            editThurs.setText(thursOpen + " - " + thursClose);
        }

        if ((clock == 3) && (openOrClose == false)){
            editThurs = (EditText) findViewById(R.id.editThurs);
            thursClose = (hourOfDay + ":" + minute);
            editThurs.setText(thursOpen + " - " + thursClose);
        }

        if ((clock == 4) && (openOrClose == true)){
            editFri = (EditText) findViewById(R.id.editFri);
            friOpen = (hourOfDay + ":" + minute);
            editFri.setText(friOpen + " - " + friClose);
        }

        if ((clock == 4) && (openOrClose == false)){
            editFri = (EditText) findViewById(R.id.editFri);
            friClose = (hourOfDay + ":" + minute);
            editFri.setText(friOpen + " - " + friClose);
        }

        if ((clock == 5) && (openOrClose == true)){
            editSat = (EditText) findViewById(R.id.editSat);
            satOpen = (hourOfDay + ":" + minute);
            editSat.setText(satOpen + " - " + satClose);
        }

        if ((clock == 5) && (openOrClose == false)){
            editSat = (EditText) findViewById(R.id.editSat);
            satClose = (hourOfDay + ":" + minute);
            editSat.setText(satOpen + " - " + satClose);
        }

        if ((clock == 6) && (openOrClose == true)){
            editSun = (EditText) findViewById(R.id.editSun);
            sunOpen = (hourOfDay + ":" + minute);
            editSun.setText(sunOpen + " - " + sunClose);
        }

        if ((clock == 6) && (openOrClose == false)){
            editSun = (EditText) findViewById(R.id.editSun);
            sunClose = (hourOfDay + ":" + minute);
            editSun.setText(sunOpen + " - " + sunClose);
        }

    }
}
