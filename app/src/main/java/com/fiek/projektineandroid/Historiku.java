package com.fiek.projektineandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Historiku extends Fragment {

    private Button startAnimation;
    private TextView txtAnimacioni;

    private Button Toastbutoni;
    String tekstiToast = "Faleminderit qe keni zgjedhur lojen tonë!";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmenti_historiku, container,false);

        startAnimation = (Button) view.findViewById(R.id.btn_zmadho);
        txtAnimacioni = view.findViewById(R.id.paragrafi);

        startAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation();
            }
        });

        Toastbutoni = (Button) view.findViewById(R.id.btn_toast);

        Toastbutoni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), tekstiToast, Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
    private void startAnimation(){
        Animation animation = AnimationUtils.loadAnimation(getContext() ,R.anim.anim);
        txtAnimacioni.startAnimation(animation);
    }
}
