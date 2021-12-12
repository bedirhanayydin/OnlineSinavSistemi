package com.example.sinav_sistemi;

public class hocaClass {
    private String adsoyad,email,sifre,hoca_id,brans;

    //constructor ürettik.
    public hocaClass(String adsoyad,String email,String brans,String sifre,String hoca_id) {
        this.adsoyad = adsoyad;
        this.email = email;
        this.brans=brans;
        this.sifre = sifre;
        this.hoca_id = hoca_id;

    }
    public hocaClass(){

    }

    //get set methotları ürettik

    public String getAdsoyad() {
        return adsoyad;
    }
    public void setAdsoyad(String adsoyad) {
        this.adsoyad = adsoyad;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getBrans(){
        return brans;
    }
    public void setBrans(String brans){
        this.brans=brans;
    }
    public String getSifre(){
        return sifre;
    }
    public void setSifre(String sifre){
        this.sifre=sifre;
    }

    public String getHoca_id(){
        return hoca_id;
    }
    public void setHoca_id(String hoca_id){
        this.hoca_id=hoca_id;
    }


}
