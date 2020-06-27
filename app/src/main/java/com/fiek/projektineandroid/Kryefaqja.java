package com.fiek.projektineandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

public class Kryefaqja extends Fragment {

    private EditText objlojtari1, objlojtari2;

    private TextView dilni;

    String strlojtari1, strlojtari2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmenti_kryefaqja, container,false);

        objlojtari1 = (EditText) view.findViewById(R.id.lojt1);
        objlojtari2 = (EditText) view.findViewById(R.id.lojt2);

        dilni = (TextView) view.findViewById(R.id.dilni);

        Button btnOpen = (Button) view.findViewById(R.id.btnLuaj);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strlojtari1 = objlojtari1.getText().toString().trim();
                strlojtari2 = objlojtari2.getText().toString().trim();
                Intent in = new Intent(getActivity(), Loja.class);
                in.putExtra("lojtari1",strlojtari1);
                in.putExtra("lojtari2",strlojtari2);
                startActivity(in);
            }
        });

        dilni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
//                finish();
                Intent login = new Intent(getActivity(), LoginActivity.class);
                startActivity(login);
            }
        });

        return view;
    }




}
