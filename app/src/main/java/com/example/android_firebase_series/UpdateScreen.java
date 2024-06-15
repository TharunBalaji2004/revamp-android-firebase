package com.example.android_firebase_series;

import android.content.Intent;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class UpdateScreen extends AppCompatActivity {
    private EditText etName, etCollege, etAge;
    private Button btnUpdate;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        db = FirebaseFirestore.getInstance();

        etName = findViewById(R.id.et_name);
        etCollege = findViewById(R.id.et_college);
        etAge = findViewById(R.id.et_age);
        btnUpdate = findViewById(R.id.btn_update);

        String studentId = getIntent().getStringExtra("id");
        String studentName = getIntent().getStringExtra("name");
        String studentCollege = getIntent().getStringExtra("college");
        String studentAge = getIntent().getStringExtra("age");

        etName.setText(studentName);
        etCollege.setText(studentCollege);
        etAge.setText(studentAge);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String college = etCollege.getText().toString();
                String studentAge = etAge.getText().toString();

                long age = 0;
                if (!studentAge.isEmpty()) age = Long.parseLong(studentAge);

                Map<String, Object> map = new HashMap<>();
                map.put("name", name);
                map.put("college", college);
                map.put("age", age);

                db.collection("students")
                        .document(studentId)
                        .set(map, SetOptions.merge())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d("FIREBASE", "Data updated successfully");
                                    Intent intent = new Intent(UpdateScreen.this, HomeActivity.class);
                                    startActivity(intent);
                                    finishAffinity();
                                } else {
                                    Log.d("FIREBASE", "Data updation unsuccessful");
                                }
                            }
                        });

            }
        });

    }
}
