package com.example.android_firebase_series;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private Button btnAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        db = FirebaseFirestore.getInstance();
        btnAdd = findViewById(R.id.btn_add);

        readData();
        updateData("Mmceaf0pMj2AxrFtWYMX");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, FormActivity.class);
                startActivity(intent);
            }
        });
    }

    // READ
    private void readData() {
        db.collection("students")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Log.d("FIREBASE", "Data fetched successfully");

                            for (DocumentSnapshot documentSnapshot: task.getResult()) {
                                Log.d("FIREBASE", documentSnapshot.get("name").toString());
                            }

                        } else {
                            Log.d("FIREBASE", "Data fetching unsuccessful");
                        }
                    }
                });
    }

    // UPDATE
    private void updateData(String documentId) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Tharun Balaji");
        map.put("college", "Anna University");

        db.collection("students")
                .document(documentId)
                .set(map, SetOptions.merge())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("FIREBASE", "Data updated successfully");
                        } else {
                            Log.d("FIREBASE", "Data updation unsuccessful");
                        }
                    }
                });

    }

    // DELETE
    private void deleteData(String documentId) {

    }
}