package com.example.sobathidroponik;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    private Button Register;
    private FirebaseAuth mAuth;
    private EditText nama_regis, email_regis, no_hp, pw_regis;
    private TextView btn_sign;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    DatabaseReference databaseReference;

    UserProfile userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nama_regis = findViewById(R.id.nama_regis);
        email_regis = findViewById(R.id.email_regis);
        no_hp = findViewById(R.id.nohp_regis);
        pw_regis = findViewById(R.id.pw_regis);

        btn_sign = findViewById(R.id.btn_sign1);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
//        progressBar = findViewById(R.id.progressBar);

//        initView();
//        registerUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        Register = findViewById(R.id.cirRegisterButton);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId,  xfullname, xemail, xphone, xpassword;

                userId = databaseReference.push().getKey();
                xfullname = nama_regis.getText().toString();
                xemail = email_regis.getText().toString().trim();
                xphone    = no_hp.getText().toString();
                xpassword = pw_regis.getText().toString().trim();


                UserProfile userProfile = new UserProfile(userId, xfullname, xemail, xphone,xpassword);
                databaseReference.child(userId).setValue(userProfile);
                Toast.makeText(RegisterActivity.this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();

                if(TextUtils.isEmpty(xfullname)){
                    nama_regis.setError("Nama Lengkap Tidak Boleh Kosong");
                    return;
                }
                if(TextUtils.isEmpty(xemail)){
                    email_regis.setError("Email is Required.");
                    return;
                }
                if(TextUtils.isEmpty(xphone)){
                    no_hp.setError("Phone is Required.");
                    return;
                }
                if(xphone.length() < 11){
                    no_hp.setError("No. HP Must be >= 11 Characters");
                    return;
                }
                if(TextUtils.isEmpty(xpassword)){
                    pw_regis.setError("Password is Required.");
                    return;
                }
                if(xpassword.length() < 6){
                    pw_regis.setError("Password Must be >= 6 Characters");
                    return;
                }


//                progressBar.setVisibility(View.VISIBLE);

                // register the user in firebase
//                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//
//                        if (task.isSuccessful()) {
//
//                            UserProfile user = new UserProfile(
//                                    userID,
//                                    fullName,
//                                    email,
//                                    phone,
//                                    password
//                            );
//
//                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    //progressBar.setVisibility(View.GONE);
//                                    if (task.isSuccessful()) {
//                                        Toast.makeText(RegisterActivity.this, "Registrasi Akun Berhasil", Toast.LENGTH_SHORT).show();
//                                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
//
//                                    } else {
//                                        Toast.makeText(RegisterActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });
//
//                        } else {
//                            Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
            }
        });
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }

//    public void registerUser() {
//        Register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            //menampung imputan user
//                String namaUser = nama_regis.getText().toString().trim();
//                String emailUser = email_regis.getText().toString().trim();
//                String passwordUser = pw_regis.getText().toString().trim();
////                String pw = pw_confrim.getText().toString().trim();
//                String nohp = no_hp.getText().toString().trim();
//
//            //validasi email dan password
//            // jika email kosong
//                if (namaUser.isEmpty()){
//                    nama_regis.setError("Nama tidak boleh kosong");
//                }
//                else if (emailUser.isEmpty()){
//                    email_regis.setError("Email tidak boleh kosong");
//                }
//            // jika email not valid
//                else if (!Patterns.EMAIL_ADDRESS.matcher(emailUser).matches()){
//                    email_regis.setError("Email tidak valid");
//                }
//            // jika hp kosong
//                else if (nohp.isEmpty()){
//                    no_hp.setError("Password tidak boleh kosong");
//                }
//            // jika password kosong
//                else if (passwordUser.isEmpty()){
//                    pw_regis.setError("Password tidak boleh kosong");
//                }
////            // jika password kosong
////                else if (passwordUser.isEmpty()){
////                    pw_confrim.setError("Confirm Password tidak boleh kosong");
////                }
//            //jika password kurang dari 6 karakter
//                else if (passwordUser.length() < 6){
//                    pw_regis.setError("Password minimal terdiri dari 6 karakter");
//                }
//                else {
//                //create user dengan firebase auth
//                    mAuth.createUserWithEmailAndPassword(emailUser,passwordUser).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
//                                @Override
//                                public void onComplete(@NonNull Task<AuthResult> task) {
//                                //jika gagal register do something
//                                    if (!task.isSuccessful()){
//                                        Toast.makeText(RegisterActivity.this,
//                                                "Register gagal karena "+ task.getException().getMessage(),
//                                                Toast.LENGTH_LONG).show();
//                                    }else {
//                                    //jika sukses akan menuju ke login activity
//                                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
//                                    }
//                                    Save.save(getApplicationContext(), "session", "true");
//                                }
//                            });
//                }
//            }
//        });
//    }

    private void initView() {
        nama_regis = findViewById(R.id.nama_regis);
        email_regis = findViewById(R.id.email_regis);
        no_hp = findViewById(R.id.nohp_regis);
        pw_regis = findViewById(R.id.pw_regis);
        mAuth = FirebaseAuth.getInstance();
    }
}
