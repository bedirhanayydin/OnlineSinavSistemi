package com.example.sinav_sistemi;

public class soruClass {
    private String ekleyenhocamail,ders,tb_ekle_soru,tb_soru_dogru_cevap,tb_soru_cevap1,tb_soru_cevap2,tb_soru_cevap3,soru_id;
    public soruClass(String ekleyenhocamail,String ders,String tb_ekle_soru, String tb_soru_dogru_cevap, String tb_soru_cevap1, String tb_soru_cevap2, String tb_soru_cevap3,String soru_id) {
        this.tb_ekle_soru = tb_ekle_soru;
        this.tb_soru_dogru_cevap = tb_soru_dogru_cevap;
        this.tb_soru_cevap1 = tb_soru_cevap1;
        this.tb_soru_cevap2 = tb_soru_cevap2;
        this.tb_soru_cevap3 = tb_soru_cevap3;
        this.soru_id=soru_id;
        this.ders=ders;
        this.ekleyenhocamail=ekleyenhocamail;
    }
    public soruClass(){

    }

    public String getEkleyenhocamail() {
        return ekleyenhocamail;
    }
    public void setEkleyenhocamail(String ekleyenhocamail) {this.ekleyenhocamail = ekleyenhocamail; }

    public String getDers() {
        return ders;
    }
    public void setDers(String ders) {
        this.ders = ders;
    }

    public String getTb_ekle_soru() {
        return tb_ekle_soru;
    }
    public void setTb_ekle_soru(String tb_ekle_soru) {
        this.tb_ekle_soru = tb_ekle_soru;
    }

    public String getTb_soru_dogru_cevap() {
        return tb_soru_dogru_cevap;
    }
    public void setTb_soru_dogru_cevap(String tb_soru_dogru_cevap) { this.tb_soru_dogru_cevap = tb_soru_dogru_cevap; }

    public String getTb_soru_cevap1() {
        return tb_soru_cevap1;
    }
    public void setTb_soru_cevap1(String tb_soru_cevap1) {
        this.tb_soru_cevap1 = tb_soru_cevap1;
    }

    public String getTb_soru_cevap2() {
        return tb_soru_cevap2;
    }
    public void setTb_soru_cevap2(String tb_soru_cevap2) {
        this.tb_soru_cevap2 = tb_soru_cevap2;
    }

    public String getTb_soru_cevap3() {
        return tb_soru_cevap3;
    }
    public void setTb_soru_cevap3(String tb_soru_cevap3) { this.tb_soru_cevap3 = tb_soru_cevap3; }

    public String getSoru_id(){
        return soru_id;
    }
    public void setSoru_id(String soru_id){
        this.soru_id=soru_id;
    }
}
