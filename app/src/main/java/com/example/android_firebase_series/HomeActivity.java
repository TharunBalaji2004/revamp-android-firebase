package com.example.android_firebase_series;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private Button btnAdd;
    private RecyclerView rvStudentList;
    private StudentAdapter studentAdapter;

    @Override
    protected void onStart() {
        super.onStart();
        readData();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        db = FirebaseFirestore.getInstance();
        btnAdd = findViewById(R.id.btn_add);

        rvStudentList = findViewById(R.id.rv_studentlist);
        rvStudentList.setLayoutManager(new LinearLayoutManager(this));

        studentAdapter = new StudentAdapter();
        studentAdapter.setStudentList(new ArrayList<>());
        rvStudentList.setAdapter(studentAdapter);

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

                            List<Student> studentList = new ArrayList<>();

                            for (DocumentSnapshot documentSnapshot: task.getResult()) {
                                Student student = new Student();

                                student.setId(documentSnapshot.getId());
                                if (documentSnapshot.get("name") != null)
                                    student.setName(documentSnapshot.getString("name"));
                                if (documentSnapshot.get("college") != null)
                                    student.setCollege(documentSnapshot.getString("college"));
                                if (documentSnapshot.get("age") != null)
                                    student.setAge((long) documentSnapshot.get("age"));

                                studentList.add(student);
                            }

                            studentAdapter.setStudentList(studentList);
                            studentAdapter.notifyDataSetChanged();
                            rvStudentList.setAdapter(studentAdapter);
                        } else {
                            Log.d("FIREBASE", "Data fetching unsuccessful");
                        }
                    }
                });
    }
}