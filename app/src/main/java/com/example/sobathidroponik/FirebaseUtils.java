package com.example.sobathidroponik;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtils {
    public static final  String ACCOUNT_PATH = "Users";
    //public static final  String ACCOUNT_EMAIL = "Email";

    private static final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    public static DatabaseReference getReference(String path){
        return firebaseDatabase.getReference(path);
    }

}
