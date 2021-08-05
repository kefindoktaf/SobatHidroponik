package com.example.sobathidroponik;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class LoginActivity extends AppCompatActivity {
    private String TAG = LoginActivity.class.getSimpleName();
    private TextView btn_register,btnReset;
    private EditText txt_email,txt_pw;
    //private FirebaseAuth mAuth;
    private CircularProgressButton btn_login;
    private UserProfile userProfile;
    private String name,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        txt_email = findViewById(R.id.editTextEmail);
        txt_pw = findViewById(R.id.editTextPassword);
        btn_login = findViewById(R.id.cirLoginButton);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sign();
            }
        });
////  Button Reset Password
//        btnReset = findViewById(R.id.btn_reset_password);
//        btnReset.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
//                    }
//                });
    }


    private void Sign(){
        name = txt_email.getText().toString().trim();
        password = txt_pw.getText().toString().trim();

        Query query = FirebaseUtils.getReference(FirebaseUtils.ACCOUNT_PATH).orderByKey();
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (name.isEmpty()) {
//                    txt_email.setError("Email tidak boleh kosong");
//                    return;
//                }
//                // jika email not valid
//                else if (!Patterns.EMAIL_ADDRESS.matcher(name).matches()) {
//                    //txt_email.setError("Email tidak valid");
//                    return;
//                }
//                // jika password kosong
//                else if (password.isEmpty()) {
//                    //txt_pw.setError("Password tidak boleh kosong");
//                    return;
//                }
//                //jika password kurang dari 6 karakter
//                else if (password.length() < 6) {
//                    //txt_pw.setError("Password minimal terdiri dari 6 karakter");
//                    return;
//                }
//                else {
//
//                }
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        UserProfile userProfile = snapshot.getValue(UserProfile.class);
                        if (userProfile.getName().equals(name) &&
                                userProfile.getPassword().equals(password)) {
                            MyPreference.getEditorPreferences()
                                    .putBoolean(MyPreference.HOME, true);

                            MyPreference.getEditorPreferences()
                                    .putString(MyPreference.EMAIL,userProfile.getEmail());

                            MyPreference.getEditorPreferences()
                                    .putString(MyPreference.FULLNAME,userProfile.getName());

                            MyPreference.getEditorPreferences()
                                    .putString(MyPreference.NOHP,userProfile.getPhone());

                        }
                        MyPreference.getEditorPreferences().commit();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(LoginActivity.this, "Selamat Datang", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                   //Toast.makeText(LoginActivity.this, "NIK dan Password belum terdaftar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            Log.d(TAG,error.getDetails()+"|"+error.getMessage());
            }
        });
    }

//    private void initView() {
//
//        //btn_login = findViewById(R.id.cirLoginButton);
//        btn_register= findViewById(R.id.btn_register);
//        mAuth = FirebaseAuth.getInstance();
//    }
//
    public void onLoginClick(View View){
        startActivity(new Intent(this,RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);

    }


}
