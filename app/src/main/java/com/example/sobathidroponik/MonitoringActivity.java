package com.example.sobathidroponik;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.colindodd.gradientlayout.GradientRelativeLayout;

public class MonitoringActivity extends AppCompatActivity{

    TextView volume,ppm, ph, suhu, lembab, lampu, nutA, nutB, Distri, Mixing;
    Firebase mRef;
    GradientRelativeLayout btn_volume;
    String valuePPM, valuePH, valueSuhu, valueKelembaban, valueLampu, nutrisia, nutrisib, distribusi, mixing;;
    RelativeLayout btn_nutisi;
    DatabaseReference dref;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring);

        ppm = (TextView) findViewById(R.id.nilaippm);
        ph = (TextView) findViewById(R.id.nilaiPh);
        suhu = (TextView) findViewById(R.id.nilaiSuhu);
        lembab = (TextView) findViewById(R.id.nilaiKelembaban);
        lampu = (TextView) findViewById(R.id.nilaiLampu);
//        nutA = (TextView) findViewById(R.id.nutrisiA);
//        nutB = (TextView) findViewById(R.id.nutrisiB);
//        Distri = (TextView) findViewById(R.id.distribusi);
//        Mixing = (TextView) findViewById(R.id.mixing);

        dref = FirebaseDatabase.getInstance().getReference();
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                valuePPM = snapshot.child("ppm").getValue().toString();
                ppm.setText(valuePPM);

                valueLampu= snapshot.child("lampu").getValue().toString();
                lampu.setText(valueLampu);

                valueSuhu = snapshot.child("suhu").getValue().toString();
                suhu.setText(valueSuhu);

                valueKelembaban = snapshot.child("kelembaban").getValue().toString();
                lembab.setText(valueKelembaban);

                valuePH = snapshot.child("pH").getValue().toString();
                ph.setText(valuePH);

//                nutrisia = snapshot.child("volume_a").getValue().toString();
//                nutA.setText(nutrisia);
//
//                nutrisib= snapshot.child("volume_b").getValue().toString();
//                nutB.setText(nutrisib);

//                distribusi = snapshot.child("volume_distribusi").getValue().toString();
//                Distri.setText(distribusi);

//                mixing = snapshot.child("volume_mixing").getValue().toString();
//                Mixing.setText(mixing);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

//        Button cotrol
        btn_nutisi = findViewById(R.id.monitor);
        btn_nutisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KontrolActivity.class);
                startActivity(intent);
            }
        });

        //        Button volume
        btn_volume = findViewById(R.id.GRL_volume);
        btn_volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VolumeActivity.class);
                startActivity(intent);
            }
        });

//        mRef = new Firebase("https://hydrotech-52c36-default-rtdb.firebaseio.com/");

//        mRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String value = dataSnapshot.getValue(String.class);
//                jarak1.setText(value);
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });
    }
}
