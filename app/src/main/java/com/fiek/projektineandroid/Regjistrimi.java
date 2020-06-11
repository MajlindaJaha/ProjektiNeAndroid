package com.fiek.projektineandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Regjistrimi extends AppCompatActivity {

    private Button btnLuaj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regjistrimi);

        btnLuaj = (Button) findViewById(R.id.btnLuaj);
        btnLuaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLuaj();
            }
        });
    }

    public void openLuaj(){
        Intent intent = new Intent(this, Loja.class);
        startActivity(intent);
    }
}
