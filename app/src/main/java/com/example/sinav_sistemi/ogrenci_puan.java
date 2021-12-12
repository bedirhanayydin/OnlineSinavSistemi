package com.example.sinav_sistemi;

public class ogrenci_puan {
    private String ogradsoyad;
    private String numara,ders;
    private Integer puan;

    public ogrenci_puan(String ogradsoyad, String numara, Integer puan, String ders) {
        this.ogradsoyad = ogradsoyad;
        this.numara = numara;
        this.puan=puan;
        this.ders=ders;
    }

    public ogrenci_puan() {

    }

    public String getDers() {
        return ders;
    }

    public void setDers(String ders) {
        this.ders = ders;
    }

    public Integer getPuan() {
        return puan;
    }

    public void setPuan(Integer puan) {
        this.puan = puan;
    }

    public String getOgradsoyad() {
        return ogradsoyad;
    }

    public void setOgradsoyad(String ogradsoyad) {
        this.ogradsoyad = ogradsoyad;
    }

    public String getNumara() {
        return numara;
    }

    public void setNumara(String numara) {
        this.numara = numara;
    }
}
