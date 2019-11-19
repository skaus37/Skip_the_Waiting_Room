package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    private String address, phone, clinicName, insurance, payment;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        mAuth = FirebaseAuth.getInstance();

        Button updateProfileButton = findViewById(R.id.updateProfile);
        updateProfileButton.setOnClickListener((View v)->{
            update(v);
        });
    }

    private void update(View v) {
        EditText addressText = (EditText) findViewById(R.id.editAddress);
        EditText phoneText = (EditText) findViewById(R.id.phoneText);
        EditText clinicNameText = (EditText) findViewById(R.id.editClinicName);
        EditText insuranceText = (EditText) findViewById(R.id.editInsurance);
        EditText paymentText = (EditText) findViewById(R.id.editPayment);

        address = addressText.getText().toString();
        phone = phoneText.getText().toString();
        clinicName = clinicNameText.getText().toString();
        insurance = insuranceText.getText().toString();
        payment = paymentText.getText().toString();


        if(address.matches("") || phone.matches("") || clinicName.matches("") || insurance.matches("") || payment.matches("")){
            Toast.makeText(this, "You must fill out all fields", Toast.LENGTH_SHORT).show();
            return;

        } else {

            FirebaseUser user = mAuth.getCurrentUser();

            Map<String, Object> docData = new HashMap<>();
            docData.put("address", address);
            docData.put("phone", phone);
            docData.put("clinicName", clinicName);
            docData.put("payment", payment);
            docData.put("insurance", insurance);
            docData.put("updated","yes");

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("users").document(user.getEmail()).set(docData);

            finish();
        }
    }

}
