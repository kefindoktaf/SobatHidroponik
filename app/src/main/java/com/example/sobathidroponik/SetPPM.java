package com.example.sobathidroponik;

import java.io.Serializable;

public class SetPPM implements Serializable {
    private String setppm, NoPPM;
    private String key;

    public SetPPM(String s){

    }
    public SetPPM(String key, String setppm){
        this.key=key;
        this.setppm=setppm;
    }

    public String getKey() {
        return key;
    }
    public void setKey(String key){
        this.key = key;
    }

    public String getNomorKK() {
        return NoPPM;
    }

    public void setNomorKK(String noppm) {
        this.NoPPM = noppm;
    }
    public String getPpm() {
        return setppm;
    }
    public void setPpm(String setppm) {
        this.setppm = setppm;
    }

//    @Override
//    public String toString() {
//        return " "+noppm+"\n";
//    }
//    public SetPPM(String sppm){
//        setppm = sppm;
//    }
}
