package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountManagement extends AppCompatActivity {
    ListView userListView;
    ArrayList<String> accountArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_management);
        userListView = (ListView) findViewById(R.id.listOfUsers);

        updateListVeiw();
        deleteAccounts();


    }

    FirebaseFirestore database = FirebaseFirestore.getInstance();

    public void updateListVeiw() {

        database.collection("users").get().addOnCompleteListener((@NonNull Task<QuerySnapshot> task) -> {
            if (task.isSuccessful()) {
                String currentUser;
                accountArray = new ArrayList<>();
                //make it impossible for the admin to deleate admin account
                for (QueryDocumentSnapshot document : task.getResult()) {
                    currentUser = document.getId();
                    if (!(currentUser.equals("admin@gmail.com"))) {
                        accountArray.add(currentUser);

                    }
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                        (this, android.R.layout.simple_list_item_1, accountArray);
                userListView.setAdapter(arrayAdapter);

            }

        });

    }


    public void deleteAccounts() {

        database = FirebaseFirestore.getInstance();
        userListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String email = accountArray.get(i);
                database.collection("users").document(email).delete();
                updateListVeiw();
                return true;
            }

        });
    }
    public void goBack(View view){
        finish();

    }
}