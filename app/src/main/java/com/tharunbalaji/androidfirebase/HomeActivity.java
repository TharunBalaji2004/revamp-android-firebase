package com.tharunbalaji.androidfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.tharunbalaji.androidfirebase.adapter.StudentAdapter;
import com.tharunbalaji.androidfirebase.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView rvStudents;
    private StudentAdapter studentAdapter;
    private Button btnAddStudent;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        rvStudents = findViewById(R.id.rvStudents);
        btnAddStudent = findViewById(R.id.btnAddStudent);

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, FormActivity.class);
                startActivity(intent);
            }
        });

        rvStudents.setLayoutManager(new LinearLayoutManager(this));

        // Creating database instance
        db = FirebaseFirestore.getInstance();

        db.collection("students").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<Student> studentList = new ArrayList<>();
                        Log.d("FIRESTORE", "Successfully fetched data");

                        for (QueryDocumentSnapshot document: queryDocumentSnapshots) {
                            Student student = new Student();
                            student.setId(document.getId());
                            student.setStudentName((String) document.get("name"));
                            student.setCollegeName((String) document.get("college"));
                            student.setAge((Long) document.get("age"));

                            Log.d("FIRESTORE", student.printContents());
                            studentList.add(student);
                        }

//                        studentAdapter.setStudentList(studentList);
//                        rvStudents.setAdapter(studentAdapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(HomeActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                    }
                });


        // Update data
        String documentId = "9XDm2DiMAO3qTUOogKWE";
        //updateData(documentId);

        deleteData(documentId);
    }

    private void updateData(String documentId) {
        HashMap<String, Object> changed = new HashMap<>();
        changed.put("location", "Chennai");

        db.collection("students")
                .document(documentId)
                .update(changed)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("FIRESTORE", "Data Updated successfully!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("FIRESTORE", "Data Updation unsuccessful");
                    }
                });
    }

    private void deleteData(String documentId) {
        db.collection("students")
                .document(documentId)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("FIRESTORE", "Data Deleted successfully!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("FIRESTORE", "Data Deleteion unsuccessful");
                    }
                });

    }
}
