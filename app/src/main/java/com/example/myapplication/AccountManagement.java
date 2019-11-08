package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AccountManagement extends AppCompatActivity {
    DatabaseReference databaseAccounts;
    List<User> users;
    ListView listViewUsers;
    Button DeleteButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_management);
        listViewUsers = (ListView) findViewById(R.id.listOfUsers);
        DeleteButton = (Button)findViewById(R.id.delete_account);
        users = new ArrayList<User>();
        databaseAccounts = FirebaseDatabase.getInstance().getReference("users");
    }
    @Override
    protected  void onStart() {
        super.onStart();
        databaseAccounts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                users.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    User user = postSnapshot.getValue(User.class);
                    users.add(user);
                }
                AccountList usersAdapter = new AccountList(AccountManagement.this, users);
                listViewUsers.setAdapter(usersAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
