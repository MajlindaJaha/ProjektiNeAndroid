package com.fiek.projektineandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;


public class RegjistrohuActivity extends AppCompatActivity {

    ImageView mimagewiew;
    Button chooseBtn;

    private TextView loginLink;
    private Button regjistroButon;
    private Button ruajNeDatabasze;
    private EditText emailEditText, passwordEditText;
    private String email, password;
    private FirebaseAuth firebaseAuth;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regjistrohu);

        mimagewiew = findViewById(R.id.image_view);
        chooseBtn = findViewById(R.id.choose_photo);



        chooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                            == PackageManager.PERMISSION_DENIED){
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions,PERMISSION_CODE);
                    }
                    else{

                        pickImageFromGallery();
                    }
                }
                else{
                    pickImageFromGallery();
                }
            }


        });

        regjistroButon = findViewById(R.id.regjistrohuButton);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        loginLink = findViewById(R.id.loginlink);

        ruajNeDatabasze = findViewById(R.id.ruajNeDatabaze);
        db = new DatabaseHelper(this);

        ruajNeDatabasze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = emailEditText.getText().toString();
                String s2 = passwordEditText.getText().toString();

                if(s1.equals("") || s2.equals("")){
                    Toast.makeText(getApplicationContext(), "Mbush te dhenat",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean chkemail = db.chkemail(s1);
                    if(chkemail==true){
                        Boolean insert = db.insert(s1,s2);
                        if(insert==true){
                                Toast.makeText(getApplicationContext(),"Te dhenat u ruajten me sukses", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Emaili tashme ekziston",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

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
    private void pickImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length >0 && grantResults[0]==
                PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery();
                }
                else {
                    Toast.makeText(this,"Permission denied..!",Toast.LENGTH_SHORT).show();
                }
            }

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            mimagewiew.setImageURI(data.getData());
        }
    }
}

