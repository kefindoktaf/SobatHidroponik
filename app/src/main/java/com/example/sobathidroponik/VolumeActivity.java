package com.example.sobathidroponik;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VolumeActivity extends AppCompatActivity{

    TextView nutA, nutB, Distri, Mixing, lembab, lampu;
    Firebase mRef;
    String nutrisia, nutrisib, distribusi, mixing;

    DatabaseReference dref;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);



        nutA = (TextView) findViewById(R.id.nutrisiA);
        nutB = (TextView) findViewById(R.id.nutrisiB);
        Distri = (TextView) findViewById(R.id.distribusi);
        Mixing = (TextView) findViewById(R.id.mixing);


        dref = FirebaseDatabase.getInstance().getReference();
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nutrisia = snapshot.child("volume_a").getValue().toString();
                nutA.setText(nutrisia);

                nutrisib= snapshot.child("volume_b").getValue().toString();
                nutB.setText(nutrisib);

                distribusi = snapshot.child("volume_distribusi").getValue().toString();
                Distri.setText(distribusi);

                mixing = snapshot.child("volume_mixing").getValue().toString();
                Mixing.setText(mixing);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
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
