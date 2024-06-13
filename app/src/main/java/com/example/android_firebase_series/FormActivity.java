package com.example.android_firebase_series;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FormActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private EditText etName, etCollege, etAge;
    private Button btnCreate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        db = FirebaseFirestore.getInstance();

        etName = findViewById(R.id.et_name);
        etCollege = findViewById(R.id.et_college);
        etAge = findViewById(R.id.et_age);
        btnCreate = findViewById(R.id.btn_create);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String college = etCollege.getText().toString();
                int age = Integer.parseInt(etAge.getText().toString());

                addData(name, college, age);
            }
        });

    }

    // CREATE
    private void addData (String name, String college, int age) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("college", college);
        map.put("age", age);

        db.collection("students")
                .add(map)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            Log.d("FIREBASE", "Data created successfully");
                            finish();
                        } else {
                            Log.d("FIREBASE", "Data creation failed: " + task.getException());
                        }
                    }
                });

    }
}
