package com.example.android_firebase_series;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class DataScreen extends AppCompatActivity {
    private TextView tvName, tvCollege, tvAge;
    private Button btnEdit, btnDelete;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        db = FirebaseFirestore.getInstance();

        tvName = findViewById(R.id.tv_name);
        tvCollege = findViewById(R.id.tv_college);
        tvAge = findViewById(R.id.tv_age);

        btnEdit = findViewById(R.id.btn_edit);
        btnDelete = findViewById(R.id.btn_delete);

        String studentId = getIntent().getStringExtra("id");
        String studentName = getIntent().getStringExtra("name");
        String studentCollege = getIntent().getStringExtra("college");
        String studentAge = getIntent().getStringExtra("age");

        tvName.setText(studentName);
        tvCollege.setText(studentCollege);
        tvAge.setText(studentAge);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataScreen.this, UpdateScreen.class);
                intent.putExtra("id", studentId);
                intent.putExtra("name", studentName);
                intent.putExtra("college", studentCollege);
                intent.putExtra("age", studentAge);

                startActivity(intent);
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("students")
                        .document(studentId)
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d("FIREBASE", "Data deleted successfully");
                                    Intent intent = new Intent(DataScreen.this, HomeActivity.class);
                                    startActivity(intent);
                                    finishAffinity();
                                } else {
                                    Log.d("FIREBASE", "Data deletion unsuccesful");
                                }
                            }
                        });
            }
        });
    }
}
