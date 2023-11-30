package com.tharunbalaji.androidfirebase;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class FormActivity extends AppCompatActivity {

    private EditText etStudentName, etCollegeName, etStudentAge;
    private MaterialButton btnAdd;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formscreen);

        etStudentName = findViewById(R.id.etStudentName);
        etCollegeName = findViewById(R.id.etCollegeName);
        etStudentAge = findViewById(R.id.etStudentAge);
        btnAdd = findViewById(R.id.btnAdd);

        db = FirebaseFirestore.getInstance();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentName = etStudentName.getText().toString();
                String collegeName = etCollegeName.getText().toString();
                int studentAge = Integer.parseInt(etStudentAge.getText().toString());

                HashMap<String, Object> data = new HashMap<>();
                data.put("name", studentName);
                data.put("college", collegeName);
                data.put("age", studentAge);

                // Add data
                db.collection("students").add(data)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("FIRESTORE", "Data added - documentRef: "+documentReference.getId());
                                Toast.makeText(FormActivity.this, "New Student added successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("FIRESTORE", "Data adding unsuccessful");
                            }
                        });
            }
        });

    }
}
