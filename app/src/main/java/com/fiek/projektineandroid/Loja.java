package com.fiek.projektineandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Loja extends AppCompatActivity implements View.OnClickListener{

    private Button[][] buttons = new Button[3][3];

    private boolean lojtari1Radhen = true;

    private int numeroRundat;

    private int lojtari1Piket;
    private int lojtari2Piket;

    private TextView textViewLojtari1;
    private TextView textViewLojtari2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loja);

        textViewLojtari1 = findViewById(R.id.text_view_l1);
        textViewLojtari2 = findViewById(R.id.text_view_l2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonId = "btn_" + i + j;
                int resID = getResources().getIdentifier(buttonId, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);

            }
        }

        Button buttonReset = findViewById(R.id.btn_reseto);
        buttonReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(!((Button) v).getText().toString().equals("")){
            return;
        }

        if (lojtari1Radhen)
        {
            ((Button) v).setText("X");
        }
        else
            {
                ((Button) v).setText("O");
            }

        numeroRundat++;

        if (kontrolloPerFitues()) {
            if (lojtari1Radhen)
            {
                lojtari1Fiton();
            }
            else {
                lojtari2Fiton();
            }
        } else if (numeroRundat == 9) {
            nukKaFitues();
        } else {
            lojtari1Radhen = !lojtari1Radhen;
        }

        }

    private boolean kontrolloPerFitues()
    {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;

    }

    private void lojtari1Fiton() {
       lojtari1Piket++;
        Toast.makeText(this, "Lojtari i pare fiton!",Toast.LENGTH_SHORT).show();
        nderroNumrinPikeve();
        resetoTabelen();
    }
    private void lojtari2Fiton() {
        lojtari2Piket++;
        Toast.makeText(this, "Lojtari i dyte fiton!",Toast.LENGTH_SHORT).show();
        nderroNumrinPikeve();
        resetoTabelen();
    }
    private void nukKaFitues() {
        Toast.makeText(this,"Nuk ka Fitues!", Toast.LENGTH_SHORT ).show();
        resetoTabelen();
    }
    private void nderroNumrinPikeve(){
        textViewLojtari1.setText("Lojtari 1 : " + lojtari1Piket);
        textViewLojtari2.setText("Lojtari 2 : "+lojtari2Piket);
    }
    private void resetoTabelen(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                buttons[i][j].setText("");
            }
        }
        numeroRundat = 0;
        lojtari1Radhen = true;
    }


}
