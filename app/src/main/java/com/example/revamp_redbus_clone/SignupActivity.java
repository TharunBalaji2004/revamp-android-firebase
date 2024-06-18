package com.example.revamp_redbus_clone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.revamp_redbus_clone.admin.AdminHomePage;
import com.example.revamp_redbus_clone.model.User;
import com.example.revamp_redbus_clone.user.ClientHomePage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private EditText etName, etEmail, etPassword;
    private Button btnLogin, btnSignup;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnSignup = findViewById(R.id.btn_signup);

        if (firebaseAuth.getCurrentUser() != null) {
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            String email = firebaseUser.getEmail();

            if (email != null && email.contains("admin")) {
                Intent intent = new Intent(SignupActivity.this, AdminHomePage.class);
                startActivity(intent);
                finishAffinity();
            } else {
                Intent intent = new Intent(SignupActivity.this, ClientHomePage.class);
                startActivity(intent);
                finishAffinity();
            }
        }

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = etName.getText().toString();
                String userEmail = etEmail.getText().toString();
                String userPassword = etPassword.getText().toString();

                firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d("FIREBASE", "Signup successful");

                                    if (userEmail.contains("admin")) {
                                        Intent intent = new Intent(SignupActivity.this, AdminHomePage.class);
                                        startActivity(intent);
                                        finishAffinity();
                                    } else {
                                        User user = new User(userName, userEmail);

                                        firebaseFirestore.collection("users")
                                                .add(user)
                                                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<DocumentReference> task) {
                                                        if (task.isSuccessful()) {
                                                            Log.d("FIREBASE", "User created successfully");
                                                        } else {
                                                            Log.d("FIREBASE", "User creation unsuccessful");
                                                        }
                                                    }
                                                });

                                        Intent intent = new Intent(SignupActivity.this, ClientHomePage.class);
                                        startActivity(intent);
                                        finishAffinity();
                                    }
                                } else {
                                    Log.d("FIREBASE", "Signup failed");
                                }
                            }
                        });

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}