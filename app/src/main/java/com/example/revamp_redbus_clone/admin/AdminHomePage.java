package com.example.revamp_redbus_clone.admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.revamp_redbus_clone.R;
import com.example.revamp_redbus_clone.adapter.BusAdapter;
import com.example.revamp_redbus_clone.model.Bus;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AdminHomePage extends AppCompatActivity {

    private Button btnAdd;
    private RecyclerView rvBusList;
    private BusAdapter busAdapter;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminhome);

        firebaseFirestore = FirebaseFirestore.getInstance();

        btnAdd = findViewById(R.id.btn_add);
        rvBusList = findViewById(R.id.rv_buslist);
        rvBusList.setLayoutManager(new LinearLayoutManager(this));

        busAdapter = new BusAdapter();
        busAdapter.setBusList(new ArrayList<>());
        rvBusList.setAdapter(busAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomePage.this, AdminFormPage.class);
                startActivity(intent);
            }
        });

        firebaseFirestore.collection("bus")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            List<Bus> busList = new ArrayList<>();
                            for (DocumentSnapshot documentSnapshot: task.getResult()) {
                                Bus bus = new Bus();

                                bus.setBusName(documentSnapshot.getString("busName"));
                                bus.setBusType(documentSnapshot.getString("busType"));
                                bus.setDestination(documentSnapshot.getString("destination"));
                                bus.setDuration(documentSnapshot.getString("duration"));
                                bus.setPrice(documentSnapshot.getString("price"));
                                bus.setReachingTime(documentSnapshot.getString("reachingTime"));
                                bus.setStartingPoint(documentSnapshot.getString("startingPoint"));
                                bus.setStartingTime(documentSnapshot.getString("startingTime"));

                                busList.add(bus);
                            }

                            busAdapter.setBusList(busList);
                            rvBusList.setAdapter(busAdapter);
                            busAdapter.notifyDataSetChanged();

                        } else {
                            Log.d("FIREBASE","Bus fetching unsuccessful");
                        }
                    }
                });

    }
}
