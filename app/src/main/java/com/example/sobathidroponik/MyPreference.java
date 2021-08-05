package com.example.sobathidroponik;

import android.content.Context;
import android.content.SharedPreferences;

public class MyPreference {
    private Context context;
    private static android.content.SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private final String ACCOUNT_PREF = "ACCOUNT_PREF";
    public static final String IDUSER = "IDUSER";
    public static final String FULLNAME = "FULLNAME";
    public static final String EMAIL = "EMAIL";
    public static final String NOHP = "NOHP";
    public static final String HOME = "HOME";

    public MyPreference(Context context){
        this.context = context;
        this.sharedPreferences = this.context.getSharedPreferences(ACCOUNT_PREF, Context.MODE_PRIVATE);
        this.editor = this.sharedPreferences.edit();
    }

    public static SharedPreferences getSharedPreferences(){
        return sharedPreferences;
    }
    public static SharedPreferences.Editor getEditorPreferences(){
        return editor;
    }
}
