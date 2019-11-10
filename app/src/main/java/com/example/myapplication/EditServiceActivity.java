package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditServiceActivity extends AppCompatActivity {




    List<Service> services = new ArrayList<Service>();
    EditText serviceText;
    String service;
    Spinner spinner;
    TextView spinnerView;
    String role;
    Button addButton;
    ListView listServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service);




        updateListView();

        //set role options
        addButton = (Button) findViewById(R.id.addServiceButton);
        listServices = (ListView) findViewById(R.id.listServices);
        //services = new ArrayList<Service>();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addService();

            }
        });





    }





    public void addService() {

        serviceText = (EditText) findViewById(R.id.serviceNameText);
        service = serviceText.getText().toString();
        spinner = (Spinner) findViewById(R.id.roleOptions);
        spinnerView = (TextView) spinner.getSelectedView();
        role = spinnerView.getText().toString();


        String serviceVar = serviceText.getText().toString();

        //add if statements similar to that in create_account.java to check if the name box is fill out
        if (serviceVar.matches("")) {
            Toast.makeText(this, "You must enter a service name", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Map<String, Object> docData = new HashMap<>();
            //docData.put("role", role);
            docData.put("Name", service);
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            //add on complete lister...see create_account.java code
            db.collection("services").document(service).set(docData);
            updateListView();
        }
    }

        FirebaseFirestore database = FirebaseFirestore.getInstance();
        public void updateListView() {
            //ArrayList<String> accountArray;
            Button edit = (Button)findViewById(R.id.add_btn);


            database.collection("services").get().addOnCompleteListener((@NonNull Task<QuerySnapshot> task) -> {
                if (task.isSuccessful()) {
                    String currentUser;

                    ArrayList<String> accountArray = new ArrayList<>();

                    for (QueryDocumentSnapshot document : task.getResult()){
                        currentUser = document.getId();
                        accountArray.add(currentUser);

                    }


                    //instantiate custom adapter
                    ListViewRowHandler adapter = new ListViewRowHandler(accountArray, this);
                    ListView lView = (ListView)findViewById(R.id.listServices);
                    //handle listview and assign adapter

                    lView.setAdapter(adapter);




                }
            });



        }


    public void deleteService(String id) {


                //Service s = services.get(id);
                database.collection("services").document(String.valueOf(id)).delete();
                updateListView();
                //return true;

    }

    private void updateService(String id, String name, String role) {


        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("services").child(id);
        Service service = new Service (id, name, role);
        dR.setValue(service);
        Toast.makeText(getApplicationContext(), "Service Updated", Toast.LENGTH_LONG).show();
    }

//    public void showUpdate(String serviceName) {
//
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = getLayoutInflater();
//        final View dialogView = inflater.inflate(R.layout.update, null);
//        dialogBuilder.setView(dialogView);
//
//        final EditText editService = (EditText) dialogView.findViewById(R.id.editTextName);
//        final Spinner editRole  = (Spinner) dialogView.findViewById(R.id.roleOptions);
//        final TextView spinnerView = (TextView) editRole.getSelectedView();
//        final Button updateButton = (Button) dialogView.findViewById(R.id.updateBtn);
//        final Button deleteButton = (Button) dialogView.findViewById(R.id.deleteBtn);
//
//        dialogBuilder.setTitle(serviceName);
//        final AlertDialog b = dialogBuilder.create();
//        b.show();
//
//        updateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String name = editService.getText().toString().trim();
//                String role = spinnerView.getText().toString();
//                if (!TextUtils.isEmpty(name)) {
//                    updateService(serviceId, name, role);
//                    b.dismiss();
//                }
//            }
//        });
////        database = FirebaseFirestore.getInstance();
////        listServices.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
////            @Override
////            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
////                Service s = services.get(i);
////                database.collection("services").document(String.valueOf(s)).delete();
////                updateListView();
////                return true;
////            }
////
////        });
//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                deleteService(serviceId);
//                b.dismiss();
//            }
//        });
//    }



}