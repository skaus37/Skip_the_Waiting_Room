package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ListView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.ArrayList;

public class AccountManagement extends AppCompatActivity {
    DatabaseReference databaseAccounts;
    List<User> users;
    ListView listViewUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listViewUsers = (ListView) findViewById(R.id.listViewUsers);
        setContentView(R.layout.activity_account_management);
        databaseAccounts = FirebaseDatabase.getInstance().getReference("users");
        databaseAccounts.addValueEventListener (new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                users.clear();
            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                User user = postSnapshot.getValue(User.class);
                users.add(user);
            }
            AccountList usersAdapter = new AccountList (AccountManagement.this, users );
            listViewUsers.setAdapter ( usersAdapter);
            }
            @Override
            public void onCancelled (DatabaseError databaseError){

            }
        });

    }
}
