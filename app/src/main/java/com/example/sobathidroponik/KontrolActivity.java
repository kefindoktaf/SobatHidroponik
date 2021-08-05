package com.example.sobathidroponik;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class KontrolActivity extends AppCompatActivity {
    private DatabaseReference dref;
    private CircularProgressButton btSubmit;
    private EditText setppm;
    String sppem;
    String valuePPM;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontrol);

        dref = FirebaseDatabase.getInstance().getReference();
        final SetPPM sPPM = (SetPPM) getIntent().getSerializableExtra("data");
        setppm = (EditText) findViewById(R.id.setPPM);
        TextView ppm = (TextView) findViewById(R.id.nilaippm);


        dref = FirebaseDatabase.getInstance().getReference(); //Tampil data nutrisi
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                valuePPM = snapshot.child("ppm").getValue().toString();
                ppm.setText(valuePPM);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        ItemAdapter itemAdapter = new ItemAdapter();

        btSubmit = (CircularProgressButton) findViewById(R.id.btn_submit); //Update data PPm
        btSubmit.setOnClickListener(v-> {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("set_ppm", Integer.parseInt(setppm.getText().toString()));
            itemAdapter.update("set_ppm", hashMap).addOnSuccessListener(suc ->
            {
                Toast.makeText(this, "PPM is updated", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er ->
            {
                Toast.makeText(this, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });

    }
    private boolean isEmpty(String s) {
        // Cek apakah ada fields yang kosong, sebelum disubmit
        return TextUtils.isEmpty(s);
    }

    private void updatePPM(SetPPM sPPM) {
        dref.child("Set_PPM") //akses parent index, ibaratnya seperti nama tabel
                .child(sPPM.getKey()) //select barang berdasarkan key
                .setValue(sPPM) //set value barang yang baru
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        /**
                         * Baris kode yang akan dipanggil apabila proses update barang sukses
                         */
                        Snackbar.make(findViewById(R.id.btn_submit), "Data berhasil diupdatekan", Snackbar.LENGTH_LONG).setAction("Oke", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        }).show();
                    }
                });
    }



    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, KontrolActivity.class);
    }

}
