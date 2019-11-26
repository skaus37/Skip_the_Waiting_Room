package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ClinicDetails extends AppCompatActivity {

    private TextView editMon, editThurs, editWed, editTues, editFri, editSat, editSun, clinicName, address, phone, wait;
    private FirebaseFirestore database;
    private FirebaseAuth mAuth;
    private String name;
    private Button back;
    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_details);
        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        editMon = (TextView) findViewById(R.id.eMon);
        editTues = (TextView) findViewById(R.id.eTues);
        editWed = (TextView) findViewById(R.id.eWednes);
        editThurs = (TextView) findViewById(R.id.eThurs);
        editFri = (TextView) findViewById(R.id.eFri);
        editSat = (TextView) findViewById(R.id.eSat);
        editSun = (TextView) findViewById(R.id.eSun);
        clinicName = (TextView) findViewById(R.id.cName);
        address = (TextView) findViewById(R.id.cAddress);
        phone = (TextView) findViewById(R.id.cNumber);
        wait = (TextView) findViewById(R.id.waitTime);
        back = (Button) findViewById(R.id.back);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        database = FirebaseFirestore.getInstance();
        database.collection("users").whereEqualTo("clinicName", name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());

                        clinicName.setText(name);
                        editSun.setText(document.get("sun").toString());
                        editMon.setText(document.get("mon").toString());
                        editTues.setText(document.get("tues").toString());
                        editWed.setText(document.get("wed").toString());
                        editThurs.setText(document.get("thurs").toString());
                        editFri.setText(document.get("fri").toString());
                        editSat.setText(document.get("sat").toString());
                        address.setText(document.get("address").toString());
                        phone.setText(document.get("phone").toString());
                        wait.setText("0 minutes");
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }



            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }

    private void goBack() {
        Intent i = new Intent(this, PatientActivity.class);
        startActivity(i);
    }
}
