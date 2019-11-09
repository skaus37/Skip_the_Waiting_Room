package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountManagement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_management);

        updateListVeiw();
        /*


        ListView attempt = (ListView) findViewById(R.id.listOfUsers);

        String[] fruits = new String[] {
                "Cape Gooseberry",
                "Capuli cherry"
        };
        /*
        List<String> fruits_list = new ArrayList<String>(Arrays.asList(fruits));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, fruits_list);
        attempt.setAdapter(arrayAdapter);*/


    }

    FirebaseFirestore database = FirebaseFirestore.getInstance();


    public void updateListVeiw(){
        //ArrayList<String> accountArray;


        database.collection("users").get().addOnCompleteListener((@NonNull Task<QuerySnapshot> task)->{
            if (task.isSuccessful()){

                String currentUser;
                ArrayList<String> accountArray = new ArrayList<>();
                //make it impossible for the admin to deleate admin account
                for (QueryDocumentSnapshot document : task.getResult()){
                    currentUser = document.getId();
                    accountArray.add(currentUser);

                }
                ListView userListView = (ListView) findViewById(R.id.listOfUsers);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                        (this, android.R.layout.simple_list_item_1, accountArray);
                userListView.setAdapter(arrayAdapter);

            }

        });




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
                AccountList usersAdapter = new AccountList(AccountManagement.this, users);
                listViewUsers.setAdapter(usersAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }*/
}
