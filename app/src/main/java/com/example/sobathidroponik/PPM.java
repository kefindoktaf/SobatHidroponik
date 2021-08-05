package com.example.sobathidroponik;

import java.io.Serializable;

public class PPM implements Serializable {
    //private String key;
    private String ppm;

    public PPM(){

    }

    public PPM(String key, String ppm){
//        this.key=key;
        this.ppm=ppm;
    }

//    public String getKey() {
//        return key;
//    }
//
//    public void setKey(String key) {
//        this.key = key;
//    }

    public String getppm() {
        return ppm;
    }

    public void setppm(String ppm) {
        this.ppm = ppm;
    }




}
