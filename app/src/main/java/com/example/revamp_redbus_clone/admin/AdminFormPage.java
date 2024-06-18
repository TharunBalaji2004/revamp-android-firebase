package com.example.revamp_redbus_clone.admin;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.revamp_redbus_clone.R;
import com.example.revamp_redbus_clone.model.Bus;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdminFormPage extends AppCompatActivity {

    private EditText etBusName, etBusType, etStartingTime, etReachingTime, etStartingPoint, etDestinationPoint, etDuration, etPrice;
    private Button btnCreate;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminform);

        firebaseFirestore = FirebaseFirestore.getInstance();

        etBusName = findViewById(R.id.et_busname);
        etBusType = findViewById(R.id.et_bustype);
        etStartingTime = findViewById(R.id.et_startingtime);
        etReachingTime = findViewById(R.id.et_reachingtime);
        etStartingPoint = findViewById(R.id.et_starting);
        etDestinationPoint = findViewById(R.id.et_destination);
        etDuration = findViewById(R.id.et_duration);
        etPrice = findViewById(R.id.et_price);
        btnCreate = findViewById(R.id.btn_create);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String busName = etBusName.getText().toString();
                String busType = etBusType.getText().toString();
                String startingTime = etStartingTime.getText().toString();
                String reachingTime = etReachingTime.getText().toString();
                String startingPoint = etStartingPoint.getText().toString();
                String destination = etDestinationPoint.getText().toString();
                String price = etPrice.getText().toString();
                String duration = etDuration.getText().toString();

                Bus bus = new Bus(busName, busType,startingPoint, destination, startingTime, reachingTime, duration, price);

                firebaseFirestore.collection("bus")
                        .add(bus)
                        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                if (task.isSuccessful()) {
                                    Log.d("FIREBASE", "Bus created successfully");
                                    finish();
                                } else {
                                    Log.d("FIREBASE", "Bus creation unsuccessful");
                                }
                            }
                        });
            }
        });


    }
}
