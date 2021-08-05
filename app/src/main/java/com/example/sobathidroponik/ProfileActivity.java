package com.example.sobathidroponik;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity {
    private static final int GALLERY_INTENT_CODE = 1023 ;
    private DatabaseReference userRef;
    TextView fullName,email,phone,iduser, pass;
    Button btn_update;
    String userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        phone = findViewById(R.id.profilePhone);
        fullName = findViewById(R.id.profileName);
        email    = findViewById(R.id.profileEmail);
        pass = findViewById(R.id.password);
        btn_update = findViewById(R.id.changeProfile);
        iduser = findViewById(R.id.ID_user);

        iduser.setText(MyPreference.getSharedPreferences()
                .getString(MyPreference.IDUSER, "IDUSER"));

        fullName.setText(MyPreference.getSharedPreferences()
                .getString(MyPreference.FULLNAME, "FULLNAME"));

        phone.setText(MyPreference.getSharedPreferences()
                .getString(MyPreference.NOHP, "NOHP"));

        email.setText(MyPreference.getSharedPreferences()
                .getString(MyPreference.EMAIL, "EMAIL"));

        btn_update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userID);
                String xfullname, xphone, xemail, xpassword;

                //xuserid = iduser.getText().toString();
                xfullname = fullName.getText().toString();
                xphone = phone.getText().toString();
                xemail = email.getText().toString();
                xpassword = pass.getText().toString();

                UserProfile userProfile = new UserProfile(userID, xfullname, xemail, xphone, xpassword);
                databaseReference.setValue(userProfile);
                Toast.makeText(ProfileActivity.this, "Data berhasil diupdate", Toast.LENGTH_SHORT).show();

//                Bundle bundle = new Bundle();
//                bundle.putString("data0", iduser.getText().toString());
//                bundle.putString("data1", fullName.getText().toString());
//                bundle.putString("data2", email.getText().toString());
//                bundle.putString("data3", phone.getText().toString());

            }
         });

    }


}

