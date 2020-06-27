package com.fiek.projektineandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegjistrohuActivity extends AppCompatActivity {

    private TextView loginLink;
    private Button regjistroButon;
    private EditText emailEditText, passwordEditText;
    private String  email, password;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regjistrohu);
        regjistroButon = findViewById(R.id.regjistrohuButton);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        loginLink = findViewById(R.id.loginlink);

        firebaseAuth  = FirebaseAuth.getInstance();

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(RegjistrohuActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        regjistroButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Snackbar.make(emailEditText, "Shkruani email-in", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Snackbar.make(passwordEditText, "Shkruani password-in", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Snackbar.make(passwordEditText, "Plotesoni te gjitha te dhenat!", Snackbar.LENGTH_LONG).show();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegjistrohuActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegjistrohuActivity.this, "U regjistruat me sukses!", Toast.LENGTH_SHORT).show();
                            Intent goToLogin = new Intent(RegjistrohuActivity.this, LoginActivity.class);
                            startActivity(goToLogin);
                        } else {
                            Toast.makeText(RegjistrohuActivity.this, "Regjistrimi nuk u krye me sukses, provoni perseri!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
            }
        });
    }
}
