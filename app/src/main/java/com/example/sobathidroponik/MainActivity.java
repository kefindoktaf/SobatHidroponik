package com.example.sobathidroponik;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import net.colindodd.gradientlayout.GradientRelativeLayout;


public class MainActivity extends AppCompatActivity {

    RelativeLayout btn_monitor;
    GradientRelativeLayout btn_kontrol, btn_logout, btn_info, btn_profile;
    FirebaseAuth mAuth;
    Boolean session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


//      Button Logout
        btn_logout = findViewById(R.id.GRL_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyPreference.getEditorPreferences().clear().commit();
                Intent signagain = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(signagain);
                finish();
            }
        });

//      Button Monitoring
        btn_monitor = findViewById(R.id.RL_monitoring);
        btn_monitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MonitoringActivity.class);
                startActivity(intent);
            }
        });

//      Button Information
        btn_info = findViewById(R.id.GRL_Infromation);
        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InformationActivity.class);
                startActivity(intent);
            }
        });

//      Button Controling
        btn_kontrol = findViewById(R.id.GRL_controling);
        btn_kontrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KontrolActivity.class);
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

    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        //mAuth.signOut();
//    }



//
//    public void onLogoutClick(View view) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setCancelable(false);
//        builder.setMessage("Apakah anda ingin keluar ?");
//        builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                finish();
//            }
//        });
//        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//        AlertDialog alert = builder.create();
//        alert.show();
//    }
}


