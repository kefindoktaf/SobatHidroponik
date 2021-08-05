package com.example.sobathidroponik;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import net.colindodd.gradientlayout.GradientRelativeLayout;

public class SharedPreferences extends AppCompatActivity {
    Boolean session;
    RelativeLayout RL_monitor;
    GradientRelativeLayout btn_kontrol, btn_info, btn_volume, btn_profile;
    private FirebaseAuth mAuth;
    BottomNavigationView botnav;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceStatic) {
        super.onCreate(savedInstanceStatic);
        setContentView(R.layout.activity_home);
        SESSION();

        //      Button monitor
        RL_monitor = findViewById(R.id.RL_monitoring);
        RL_monitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MonitoringActivity.class);
                startActivity(intent);
            }
        });
        //      Button Kontrol
        btn_kontrol = findViewById(R.id.GRL_controling);
        btn_kontrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KontrolActivity.class);
                startActivity(intent);
            }
        });

        //      Button Info
        btn_info = findViewById(R.id.GRL_Infromation);
        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InformationActivity.class);
                startActivity(intent);
            }
        });

        //      Button Profile
        btn_profile = findViewById(R.id.GRL_profile);
        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });

//        //      Button volume
//        btn_volume = findViewById(R.id.GRL_volume);
//        btn_volume.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), VolumeActivity.class);
//                startActivity(intent);
//            }
//        });


        findViewById(R.id.GRL_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Save.save(getApplicationContext(), "session", "false");

                Intent signupagain = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(signupagain);
                finish();
            }
        });

        //Season Username
        Bundle bundle = getIntent().getBundleExtra("emailpass");
//        String email_ = bundle.getString("email");
//        String password = bundle.getString("pass");

        mAuth = FirebaseAuth.getInstance();
//        TextView welcome = findViewById(R.id.welcome);
//        welcome.setText(email_+" ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAuth.signOut();
    }

    public void SESSION(){
        session = Boolean.valueOf(Save.read(getApplicationContext(),"session", "false"));
        if (!session){
            Intent daftar = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(daftar);
            finish();

        } else {
            Toast.makeText(this, "Selamat Datang", Toast.LENGTH_SHORT).show();
        }
    }
}
