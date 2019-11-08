package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;

public class AccountManagement extends AppCompatActivity {
    /*
    DatabaseReference databaseAccounts;
    List<User> users;
    ListView listViewUsers;
    Button DeleteButton;
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_management);
        /*
        listViewUsers = (ListView) findViewById(R.id.listViewUsers);
        DeleteButton = (Button)findViewById(R.id.delete_account);
        users = new ArrayList<>();
        databaseAccounts = FirebaseDatabase.getInstance().getReference("users");
        */
    }
    /*
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
                //AccountList usersAdapter = new AccountList(AccountManagement.this, users);
                //listViewUsers.setAdapter(usersAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
   */

}
