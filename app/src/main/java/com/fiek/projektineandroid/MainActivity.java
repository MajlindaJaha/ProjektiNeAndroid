package com.fiek.projektineandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openRegjistrimi();
            }
        });

        button1 = (Button) findViewById(R.id.btnLuaj);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLuaj();
            }
        });
    }
    public void openRegjistrimi(){
        Intent intent = new Intent(this, Regjistrimi.class);
        startActivity(intent);
    }

    public void openLuaj(){
        Intent intent = new Intent(this, Regjistrimi.class);
        startActivity(intent);
    }
}
