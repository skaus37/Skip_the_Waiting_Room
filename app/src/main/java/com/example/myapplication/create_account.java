package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.print.PrintDocumentAdapter;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class create_account extends AppCompatActivity {

    private String firstName, lastName, passWord, role ,email;

    private FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        //role is by default patient
        role = "patient";




        // ...
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        Button createAccountButton = findViewById(R.id.createAccountButton);
        createAccountButton.setOnClickListener((View v)->{
            newAccount(v);
        });

    }


    public void goBack(View view){
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }

    //Assigns a value to the role
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.patient:
                if (checked)
                    // set role value to patient
                    role = "patient";
                    break;
            case R.id.employee:
                if (checked)
                    // set role value to employee
                    role ="employee";
                    break;
        }
    }



    public void newAccount(View view) {
        EditText passText = (EditText)findViewById(R.id.passwordEnter);
        EditText firstText = (EditText)findViewById(R.id.firstName);
        EditText lastText = (EditText)findViewById(R.id.lastName);
        EditText emailText = (EditText)findViewById(R.id.emailEnter);
        //RadioButton patientBox = (RadioButton)findViewById(R.id.patient);
        //RadioButton employeeBox = (RadioButton)findViewById((R.id.employee));

        //chooseAcc.setOnCheckedChangeListener(new OnCheckedChangeListener()) {
        //int id=chooseAcc.getCheckedRadioButtonId();
        //RadioButton rb=(RadioButton) findViewById(id);

        passWord = passText.getText().toString();
        firstName = firstText.getText().toString();
        lastName = lastText.getText().toString();
        email = emailText.getText().toString();
        //role = rb.getText().toString();
        //i took this out and added method... might not be the right decision

        if (firstName.matches("")) {
            Toast.makeText(this, "You must enter a first name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (firstName.matches("")){
            Toast.makeText(this, "You must enter a first name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (lastName.matches("")) {
            Toast.makeText(this, "You must enter a last name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email.matches("")){
            Toast.makeText(this, "You must enter an email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (passWord.matches("")) {
            Toast.makeText(this, "You must enter a password", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            //do stuff
            mAuth.createUserWithEmailAndPassword(email, passWord).addOnCompleteListener((@NonNull Task<AuthResult> task) -> {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    FirebaseUser user = mAuth.getCurrentUser();
                    //if it works a new email should show up in the firebase authentication page

                    //add user information to firebase


                    // Create a Map to store the data we want to set
                    Map<String, Object> docData = new HashMap<>();
                    docData.put("accountType", role);
                    docData.put("firstName", firstName);
                    docData.put("lastName", lastName);
                    //i do not think we need to store this....will confire later
                    //docData.put("password", passWord);
// Add a new document (asynchronously) in collection "cities" with id "LA"
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("users").document(email).set(docData);
// ...
// future.get() blocks on response
                    //System.out.println("Update time : " + future.get().getUpdateTime());
                    //dont think we need this


                    //if sign in is success go to next page
                    finish();

                    if(role.equals("employee")) {
                        Intent intent = new Intent(this, ProfileActivity.class);
                        startActivity(intent);
                    } else {

                        Intent intent = new Intent(this, LogIn.class);
                        startActivity(intent);
                    }

                } else {
                    // If sign in fails, display a message to the user.
                    //output error msg
                    Toast.makeText(this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                    return;

                }
            });


        }
    }
}
